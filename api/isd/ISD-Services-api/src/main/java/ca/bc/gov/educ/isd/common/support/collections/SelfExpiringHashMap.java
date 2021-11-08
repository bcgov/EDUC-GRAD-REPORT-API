/*
 * Copyright (c) 2017 Pierantonio Cangianiello
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ca.bc.gov.educ.isd.common.support.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * A thread-safe implementation of a HashMap which entries expires after the
 * specified life time. The life-time can be defined on a per-key basis or using
 * a default one passed to the constructor.
 *
 * @author Pierantonio Cangianiello
 * @author CGI Information Management Consultants Inc.
 *
 * @param <K> the Key type
 * @param <V> the Value type
 */
public class SelfExpiringHashMap<K, V> implements SelfExpiringMap<K, V> {

    private final Map<K, V> internalMap;

    private final Map<K, ExpiringKey<K>> expiringKeys;

    /**
     * Holds the map keys using the given life time for expiration.
     */
    private final DelayQueue<ExpiringKey> delayQueue = new DelayQueue<>();

    /**
     * The default max life time in milliseconds.
     */
    private final long lifetime;

    public SelfExpiringHashMap() {
        this(Long.MAX_VALUE);
    }

    public SelfExpiringHashMap(final long lifetime) {
        this(lifetime, 1);
    }

    public SelfExpiringHashMap(final long lifetime, final int capacity) {
        this(lifetime, capacity, 0.75f);
    }

    public SelfExpiringHashMap(final long lifetime, final int capacity, final float loadFactor) {
        this.internalMap = new ConcurrentHashMap<>(capacity, loadFactor);
        this.expiringKeys = new WeakHashMap<>(capacity, loadFactor);
        this.lifetime = lifetime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        purge();
        return internalMap.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        purge();
        return internalMap.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(final Object key) {
        purge();
        return internalMap.containsKey((K) key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(final Object value) {
        purge();
        return internalMap.containsValue((V) value);
    }

    @Override
    public V get(final Object key) {
        purge();
        renew((K) key);
        return internalMap.get((K) key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(final K key, final V value) {
        return put(key, value, lifetime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(final K key, final V value, final long lifetime) {
        purge();

        final ExpiringKey delayedKey = new ExpiringKey(key, lifetime);
        final ExpiringKey oldKey = expiringKeys.put((K) key, delayedKey);

        if (oldKey != null) {
            expireKey(oldKey);
            expiringKeys.put((K) key, delayedKey);
        }

        delayQueue.offer(delayedKey);
        return internalMap.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(final Object key) {
        final V removedValue = internalMap.remove((K) key);
        expireKey(expiringKeys.remove((K) key));
        return removedValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean renew(final K key) {
        final ExpiringKey<K> delayedKey = expiringKeys.get((K) key);
        boolean result = false;

        if (delayedKey != null) {
            delayedKey.renew();
            result = true;
        }

        return result;
    }

    private void expireKey(final ExpiringKey<K> delayedKey) {
        if (delayedKey != null) {
            delayedKey.expire();
            purge();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        delayQueue.clear();
        expiringKeys.clear();
        internalMap.clear();
    }

    private void purge() {
        ExpiringKey<K> delayedKey = delayQueue.poll();

        while (delayedKey != null) {
            internalMap.remove(delayedKey.getKey());
            expiringKeys.remove(delayedKey.getKey());
            delayedKey = delayQueue.poll();
        }
    }

    private class ExpiringKey<K> implements Delayed {

        private long started = now();
        private final long lifetime;
        private final K key;

        public ExpiringKey(final K key, final long lifetime) {
            this.lifetime = lifetime;
            this.key = key;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public long getDelay(final TimeUnit unit) {
            return unit.convert(getDelayMillis(), TimeUnit.MILLISECONDS);
        }

        private long now() {
            return System.currentTimeMillis();
        }

        private K getKey() {
            return key;
        }

        private long getDelayMillis() {
            return (started + lifetime) - now();
        }

        public void renew() {
            started = now();
        }

        public void expire() {
            started = now() - lifetime - 1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int compareTo(final Delayed that) {
            return Long.compare(getDelayMillis(), ((ExpiringKey) that).getDelayMillis());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(final Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ExpiringKey<K> other = (ExpiringKey<K>) obj;
            return !(this.key != other.key && (this.key == null || !this.key.equals(other.key)));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            int hash = 7;
            hash = 31 * hash + (this.key != null ? this.key.hashCode() : 0);
            return hash;
        }
    }

    /**
     * Not supported.
     */
    @Override
    public void putAll(final Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    /**
     * Not supported.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Not supported.
     */
    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    /**
     * Not supported.
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

}

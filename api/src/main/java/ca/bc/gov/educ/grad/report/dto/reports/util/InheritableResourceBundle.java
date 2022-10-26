/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: InheritableResourceBundle.java 10550 2018-06-13#$
 *  Date of Last Commit: $Date:: 2018-06-13 09:12:10 -0700 (Wed, 13 Jun 2018)  $
 *  Revision Number:     $Rev:: 10550                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.util;

import org.apache.commons.collections.iterators.IteratorEnumeration;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.model.common.support.VerifyUtils.nullSafe;
import static java.lang.String.format;
import static java.util.regex.Pattern.quote;

/**
 * Responsible for resource bundle inheritance.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class InheritableResourceBundle extends ResourceBundle {

    private static final String CLASSNAME = InheritableResourceBundle.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private final Map<String, Object> properties = new HashMap<>(64);

    /**
     * Character to use in filenames to signify inheritance.
     */
    public static final String BASE_NAME_SEPARATOR = "_";

    private InheritableResourceBundle(
            final String directory,
            final String baseName,
            final Locale locale) {

        // Split using a literal character, rather than a regex.
        final String literal = quote(BASE_NAME_SEPARATOR);
        final String[] baseNames = baseName.split(literal);
        final StringBuilder bundleName = new StringBuilder(baseName.length());

        for (final String name : baseNames) {
            bundleName.append(name);
            final String path = getPath(directory, bundleName.toString());

            try {
                final ResourceBundle bundle = getBundle(path, locale);
                inherit(bundle);
            } catch (final MissingResourceException mre) {
                // This doesn't mean the resource is missing, rather that
                // there's no resource for the given locale. Load the resource
                // without a locale, instead.
                LOG.log(Level.FINEST, "Cannot find resource bundle", mre);

                try {
                    final ResourceBundle bundle = getBundle(path);
                    inherit(bundle);
                } catch (final MissingResourceException ex) {
                    // This doesn't always mean that the resource is missing,
                    // rather it means that a potential bundle in the named
                    // bundle hierarchy is missing.
                    LOG.log(Level.FINEST, "Cannot find resource bundle", ex);
                }
            }

            // The next iteration needs to rebuild the filename using the
            // filename inheritance indicator character.
            bundleName.append(BASE_NAME_SEPARATOR);
        }

        validateResourceBundle(directory, baseName, locale);
    }

    /**
     * Ensures that the resource bundle has valid data.
     */
    private void validateResourceBundle(
            final String directory,
            final String baseName,
            final Locale locale) {
        final Map<String, Object> map = getProperties();

        if (map.isEmpty()) {
            final String path = getPath(directory, baseName) + "_" + locale.toString();

            LOG.log(Level.WARNING, "No resource bundle properites for {0}", path);
        }
    }

    /**
     * Creates a new ResourceBundle that is aware of baseNames that contain
     * underscores to separate inherited properties.
     *
     * @param path Directory name that contains the resource bundle by the given
     * name without a trailing slash.
     * @param baseName Name of the properties file to load (without the
     * properties extension).
     * @param locale Language to use for the resource bundle.
     * @return A non-null instance with all properties loaded.
     */
    public static ResourceBundle getBundle(
            final String path, final String baseName, final Locale locale) {
        final InheritableResourceBundle result
                = new InheritableResourceBundle(path, baseName, locale);

        return result;
    }

    @Override
    protected Object handleGetObject(final String key) {
        final Map<String, Object> bundle = getProperties();

        final Object value = bundle.get(nullSafe(key));
        final Object result = value == null ? key : value;

        return result;
    }

    @Override
    public Enumeration<String> getKeys() {
        final Set<String> keySet = getProperties().keySet();
        final Iterator<String> iterator = keySet.iterator();
        final Enumeration<String> keys = new IteratorEnumeration(iterator);

        return keys;
    }

    /**
     * Puts the key/value pairs from the given resource into the internal
     * properties for this bundle. This will overwrite existing properties,
     * thereby emulating inheritance.
     *
     * @param resourceBundle The key/value pairs to add into the internal
     * properties.
     */
    private void inherit(final ResourceBundle resourceBundle) {
        final Map<String, Object> bundle = getProperties();

        for (final String key : resourceBundle.keySet()) {
            final Object value = resourceBundle.getObject(key);
            bundle.put(key, value);
        }
    }

    /**
     * Returns the key/value pair map that constitutes the properties loaded
     * from multiple resource bundles.
     *
     * @return A non-null instance.
     */
    private Map<String, Object> getProperties() {
        return this.properties;
    }

    /**
     * Returns a path suitable for use within JAR files to find a resource. In
     * this case, the resource is a resource bundle properties file.
     *
     * @param directory The
     * @param bundleName
     * @return
     */
    private String getPath(final String directory, final String bundleName) {
        final String path = format("%s/%s", directory, bundleName);

        return path;
    }
}

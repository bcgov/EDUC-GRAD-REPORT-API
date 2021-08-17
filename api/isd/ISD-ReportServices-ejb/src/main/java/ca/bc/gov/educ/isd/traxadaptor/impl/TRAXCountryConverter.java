/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for centralized conversion to and from TRAX and ISO alpha-2 character
 * codes.
 *
 * Note that this read properties files that are written by another portion of
 * the system.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TRAXCountryConverter {

    private static final String CLASSNAME = TRAXCountryConverter.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    /**
     * location of TRAX to ISO properties file
     */
    public static final String TRAX_ISO_PROPERTIES_NAME = "traxisomap.properties";

    /**
     * location of ISO to TRAX properties file
     */
    public static final String ISO_TRAX_PROPERTIES_NAME = "isotraxmap.properties";

    public static final String SYSTEM_PROPERTY_DIR = "user.dir";

    public static final String META_INF = "/";

    private static final TRAXCountryConverter INSTANCE = new TRAXCountryConverter();

    private final Properties isoTraxMap;
    private final Properties traxIsoMap;

    private TRAXCountryConverter() {
        isoTraxMap = new Properties();
        traxIsoMap = new Properties();
    }

    /**
     * Clears the mappings so that they will be re-read on next conversion.
     */
    public void clear() {
        isoTraxMap.clear();
        traxIsoMap.clear();
    }

    public String traxToISO(final String traxCountryCode) {
        if (traxIsoMap.isEmpty()) {
            loadPropertiesFile(traxIsoMap, TRAX_ISO_PROPERTIES_NAME);
        }

        // TODO: review if TRAX code better default than empty string (don't want to return null)
        final String code = traxIsoMap.getProperty(traxCountryCode, "");

        if (code == null || code.isEmpty()) {
            LOG.log(Level.WARNING, "No ISO mapping for TRAX code: {0}.", traxCountryCode);
        }

        return code;
    }

    public String isoToTRAX(final String isoCountryCode) {
        if (isoTraxMap.isEmpty()) {
            loadPropertiesFile(isoTraxMap, ISO_TRAX_PROPERTIES_NAME);
        }

        // TODO: review if TRAX code better default than empty string (don't want to return null)
        final String code = isoTraxMap.getProperty(isoCountryCode, "");

        if (code == null || code.isEmpty()) {
            LOG.log(Level.WARNING, "No TRAX mapping for ISO code: {0}.", isoCountryCode);
        }

        return code;
    }

    private void loadPropertiesFile(final Properties props, final String name) {
        final File file = new File(System.getProperty(SYSTEM_PROPERTY_DIR), name);

        if (file.exists()) {
            try (final InputStream mapStream = new FileInputStream(file)) {
                props.load(mapStream);
            } catch (final IOException ex) {
                LOG.log(Level.SEVERE, "Could not load ISO codes", ex);
            }
        }

        if (props.isEmpty()) {
            try {
                Enumeration<URL> resources = getClass().getClassLoader().getResources(META_INF + name);
                while (resources.hasMoreElements()) {
                    try (final InputStream mapStream = resources.nextElement().openStream()) {
                        props.load(mapStream);
                    } catch (final IOException ex) {
                        LOG.log(Level.SEVERE, "Could not load ISO codes", ex);
                    }
                }
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, "Could not load ISO codes", ex);
            }
        }
    }

    public static TRAXCountryConverter getInstance() {
        return INSTANCE;
    }
}

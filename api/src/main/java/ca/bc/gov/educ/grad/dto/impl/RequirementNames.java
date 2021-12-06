/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                RequirementNames.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Map of academic requirements. TODO move to code set services.
 *
 * @author CGI Information Management Consultants
 */
public class RequirementNames {

    final static Map<String, Map<String, String>> gratuationProgram = new HashMap<>();

    static {
        final Map<String, String> tmpMap = new HashMap<>();
        tmpMap.put("1", "Language Arts 11 (4 credits)");
        tmpMap.put("2", "Language Arts 12 (4 credits)");
        tmpMap.put("3", "Social Studies 11 or BC First Nations Studies 12 (4 credits)");
        tmpMap.put("4", "Math 11 or 12 (4 credits)");
        tmpMap.put("5", "Science 11 or 12 (4 credits)");
        tmpMap.put("6", "Career and Personal Planning 11 (2 credits)");
        tmpMap.put("7", "Career and Personal Planning 12 (2 credits)");
        tmpMap.put("8", "Fine Arts 11 (2 credits)");
        tmpMap.put("9", "Applied Skills 11 (2 credits)");
        tmpMap.put("10", "Both Fine Arts 11 and Applied Skills 11 (4 credits)");

        gratuationProgram.put("1995", Collections.unmodifiableMap(tmpMap));
    }

    static {
        final Map<String, String> tmpMap = new HashMap<>();
        tmpMap.put("1", "Language Arts 10 (4 credits)");
        tmpMap.put("2", "Language Arts 11 (4 credits)");
        tmpMap.put("3", "Language Arts 12 (4 credits)");
        tmpMap.put("4", "Social Studies 10 (4 credits)");
        /**
         * This entry is truncated due to the 60 character limit for the PESC
         * field this is used for. The original is "Social Studies 11, Civic
         * Studies 11 or BC First Nations Studies 12 (4 credits)"
         */
        tmpMap.put("5", "Social Studies 11, Civic Studies 11 or BC First Nations Stud");
        tmpMap.put("6", "Math 10 (4 credits)");
        tmpMap.put("7", "Math 11 or 12 (4 credits)");
        tmpMap.put("8", "Science 10 (4 credits)");
        tmpMap.put("9", "Science 11 or 12 (4 credits)");
        tmpMap.put("10", "Planning 10 (4 credits)");
        tmpMap.put("11", "Physical Education 10 (4 credits)");
        tmpMap.put("12", "Fine Arts and /or Applied Skills 10, 11 or 12 (4 credits)");
        tmpMap.put("13", "Graduation Portfolio Assessment (4 credits)");
        tmpMap.put("15", "Elective Course Credits (28 credits)");

        gratuationProgram.put("2004", Collections.unmodifiableMap(tmpMap));

    }

    static {
        final Map<String, String> tmpMap = new HashMap<>();
        tmpMap.put("1", "Language Arts 10 (4 credits)");
        tmpMap.put("2", "Language Arts 11 (4 credits)");
        tmpMap.put("3", "Language Arts 12 (4 credits)");
        tmpMap.put("4", "Social Studies 10 (4 credits)");
        tmpMap.put("5", "Social Studies 11 or 12 (4 credits)");
        tmpMap.put("6", "Mathematics 10 (4 credits)");
        tmpMap.put("7", "Mathematics 11 or 12 (4 credits)");
        tmpMap.put("8", "Science 10 (4 credits)");
        tmpMap.put("9", "Science 11 or 12 (4 credits)");
        tmpMap.put("10", "Physical and Health Education 10 (4 credits)");

        //Data differs from TRAX as some values are truncated at 60 characters
        tmpMap.put("11", "Arts Educ/Applied Design, Skills and Technologies (4 credits");
        tmpMap.put("12", "Career Life Education (4 credits)");
        tmpMap.put("13", "Career Life Connections (4 credits)");
        tmpMap.put("14", "Elective Course Credits (28 credits)");
        tmpMap.put("15", "Literacy Graduation Assessment");
        tmpMap.put("16", "Numeracy Graduation Assessment");

        gratuationProgram.put("2018", Collections.unmodifiableMap(tmpMap));
    }

    public static String getName(final String requirementMetCode, final String year) {
        String requirementName = "";
        Map<String, String> yearMap = gratuationProgram.get(year);
        if (yearMap != null) {
            requirementName = yearMap.get(requirementMetCode);
        }
        return requirementName;
    }

}

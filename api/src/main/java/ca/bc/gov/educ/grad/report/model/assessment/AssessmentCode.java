/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education, BC.
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
package ca.bc.gov.educ.grad.report.model.assessment;

/**
 * This is a "temporary" enumeration that exposes hard-coded values based on
 * hard-coded names. Eventually this class will be replaced with a dynamic
 * look-up. Update: GLA uses hard coded column headings, so this this enum is
 * required. Note the duplicate enum in ISD-Service-EIS-api
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum AssessmentCode {

    BLANK("BLANK", "No Response", "", ""),
    NO_RESPONSE("No Response", "No Response", "", ""),
    PLAN_AND_DESIGN("Plan and Design", "Plan and Design \u2014", "Students make a recommendation or design a product while working within constraints such as time, space, cost, or people.", "Example: Design a plan to reduce a family\u2019s monthly water consumption."),
    REASONED_ESTIMATES("Reasoned Estimates", "Reasoned Estimates \u2014", "Students propose a logical solution based on estimation, while considering multiple factors.", "Example: Estimate the population of a historical village based on discovered artifacts."),
    FAIR_SHARE("Fair Share", "Fair Share \u2014", "Students decide how best to share something fairly.", "Example: Determine a procedure to distribute a group prize."),
    MODEL("Model", "Model \u2014", "Students analyze data for patterns and then apply their model to another situation, making any necessary refinements.", "Example: Predict the likelihood of an event based on a data trend."),
    
    /** GLA English */
    COMPREHEND("C", "Comprehend:", "Analyzing and making meaning from texts", "Selected-response questions (machine-scored)"),
    COMMUNICATE("W", "Communicate:", "Understanding of Texts/Making Personal Connections", "Written Response Questions (teacher-scored)"),
    SELECTED_RESPONSE_A("SRA", "    ", "Selected-response questions (machine-scored) ", ""),
    WRITTEN_RESPONSE_A("WRA", "    Written Response:","“What do the texts have to say?”" , ""),
    GRAPHIC_ORGANIZER("GRO", "    Written Response:", "Graphic Organizer  ", ""),
    SELECTED_RESPONSE_B("SRB", "    ", "Selected-response questions (machine-scored)", ""),
    WRITTEN_RESPONSE_B("WRB", "    Written Response:", "“What do you have to say?”", ""),
    
    /** Sub-parts */
    PART_A("SUB_A", "Part A", "", ""),
    PART_B_E("E", "Part B", "", "You chose: <b>Literacy for Expression</b>"),
    PART_B_I("I", "Part B", "", "You chose: <b>Literacy for Information</b>"),
    
    /** GLA French */
    C_FR("C_FR", "Compréhension :", "saisir le sens des textes et les analyser de manière critique", "Questions à réponses choisies (corrigées informatiquement)"),
    W_FR("W_FR", "Communication à l’écrit :", "s’exprimer à l’écrit", "Questions à réponse construite (corrigées par des enseignants)"),
    O_FR("O_FR", "Communication à l’oral :", "s’exprimer à l’oral", "Questions à réponse construite (corrigées par des enseignants)"),
    
    /** Partie A */
    SRA_FR("SRA_FR", "Questions à réponses choisies", "(corrigées informatiquement)", ""),
    WRS_FR("WRS_FR", "Réponse écrite :", "question à réponse construite courte de la partie A", ""),
    GRO_FR("GRO_FR", "Réponse écrite :", "organisateur graphique", ""),
    WRA_FR("WRA_FR", "Réponse écrite :", "question à réponse construite longue", ""),
    
    /** Partie B */
    SRB_FR("SRB_FR", "Questions à réponses choisies", "(corrigées informatiquement)", ""),
    WRB_FR("WRB_FR", "Réponse écrite :", "question à réponse construite courte de la partie B", ""),
    
    /** Composante orale (Part C)*/
    CO_1("CO_1", "Partie 1 :", "faire une description", ""),
    CO_2("CO_2", "Partie 2 :", "animer un podcaste", ""),
    CO_3("CO_3", "Partie 3 :", "répondre à une question en lien avec la question essentielle", ""),
    
    /** Sub-parts */
    PART_A_FR("SUB_AF", "Partie A", "", ""),
    PART_B_E_FR("E_FR", "Partie B", "", "Vous avez choisi : <b>Le monde de l’expression</b>"),
    PART_B_I_FR("I_FR", "Partie B", "", "Vous avez choisi : <b>Le monde de l’information</b>"),
    PART_C_FR("SUB_CF", "Composante orale", "", "");

    /**
     * Associated with student's assessment score result.
     */
    private final String code;

    /**
     * Assessment score category.
     */
    private final String label;

    /**
     * Assessment category description.
     */
    private final String description;

    /**
     * Additional details to display about the category.
     */
    private final String detail;

    private AssessmentCode(
            final String code,
            final String label,
            final String description,
            final String detail) {
        this.code = code;
        this.label = label;
        this.description = description;
        this.detail = detail;
    }

    /**
     * Returns the code associated with student's assessment score result.
     *
     * @return A non-null, non-empty String.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the assessment score category.
     *
     * @return A non-null, non-empty String.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Returns the assessment category description.
     *
     * @return A non-null, non-empty String.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns additional details to display about the category.
     *
     * @return A non-null, non-empty String.
     */
    public String getDetail() {
        return this.detail;
    }

    /**
     * Answers whether the given code matches this enumerated instance's code.
     *
     * @param code The code to compare.
     * @return false The code does not match.
     */
    private boolean isCode(final String code) {
        return getCode().equalsIgnoreCase(code);
    }

    /**
     * Determines the enumerated instance that matches the given code.
     *
     * @param code The enumerated code to look up.
     * @return The enumerated instance that matches the given code.
     * @throws IllegalArgumentException The code could not be found.
     */
    public static AssessmentCode fromValue(final String code) {
        for (final AssessmentCode ac : values()) {
            if (ac.isCode(code)) {
                return ac;
            }
        }

        throw new IllegalArgumentException(code);
    }

}

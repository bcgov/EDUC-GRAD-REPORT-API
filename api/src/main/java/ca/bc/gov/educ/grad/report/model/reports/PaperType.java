/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: PaperType.java 5479 2016-12-01 23:43:31Z DAJARV#$
 *  Date of Last Commit: $Date:: 2016-12-01 15:43:31 -0800 (Thu, 01 Dec 2016)  $
 *  Revision Number:     $Rev:: 5479                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.reports;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the type of paper to use for printing transcripts and
 * certificates.
 *
 * @author CGI Information Management Consultants Inc.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)

public enum PaperType {

    CERTIFICATE_SC("YED2"),
    CERTIFICATE_SCI("YED2"),
    CERTIFICATE_A("YEDB"),
    CERTIFICATE_AI("YEDB"),
    CERTIFICATE_EI("YEDR"),
    CERTIFICATE_E("YEDR"),
    CERTIFICATE_S("YEDR"),
    CERTIFICATE_SCF("YED2"),
    CERTIFICATE_F("YEDR"),
    CERTIFICATE_O("YEDR"),
    CERTIFICATE_FN("YEDR"),
    CERTIFICATE_FNA("YEDB"),
    CERTIFICATE_SCFN("YED2"),
    ACHIEVEMENT("YED4"),
    TRANSCRIPT("YED4"),
    SCHOOL("YED4");

    private final String code;

    /**
     * Sets the code.
     *
     * @param code The paper type code used for media type and media colour.
     */
    private PaperType(final String code) {
        this.code = code;
    }

    /**
     * Returns the media colour used for printing this paper type.
     *
     * @return The paper type code.
     */
    public String getMediaColour() {
        return getCode();
    }

    /**
     * Returns the media type used for printing this paper type.
     *
     * @return The paper type code.
     */
    public String getMediaType() {
        return getCode();
    }

    /**
     * Returns the paper type code, which can be used as the media type and
     * media colour.
     *
     * @return The paper type code that should correspond to either a specific
     * certificate type or a transcript.
     */
    @Override
    public String toString() {
        return getCode();
    }

    /**
     * Returns the code used for printing on this paper type.
     *
     * @return The media and print type code.
     */
    private String getCode() {
        return this.code;
    }

    @JsonCreator
    public static PaperType forValue(@JsonProperty("code") final String code) {
        for (PaperType paperType : PaperType.values()) {
            if (paperType.code.equals(code)) {
                return paperType;
            }
        }
        return null;
    }
}

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
 *  File:                ReportFormat.java
 *  Date of Last Commit: $Date:: 2017-03-29 10:57:18 -0700 (Wed, 29 Mar 2017)  $
 *  Revision Number:     $Rev:: 6672                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.reports;

/**
 * Represents a report format that controls the output format for reports. TODO:
 * Rename to MediaType.
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum ReportFormat {

    /**
     * Adobe Acrobat Portable Document Format.
     *
     * @see https://tools.ietf.org/html/rfc3778
     */
    PDF("application/pdf", "pdf", true),
    /**
     * Extensible Mark-up Language.
     *
     * @see https://tools.ietf.org/html/rfc7303
     */
    XML("text/xml", "xml"),
    /**
     * Hypertext Mark-up Language.
     *
     * @see https://www.ietf.org/rfc/rfc2854.txt
     */
    HTML("text/html", "html"),
    /**
     * Comma-separated Values.
     *
     * @see https://tools.ietf.org/html/rfc4180
     */
    CSV("text/csv", "csv"),
    /**
     * Plain Text File.
     *
     * @see https://tools.ietf.org/html/rfc5147
     */
    TEXT("text/plain", "txt"),
    /**
     * Proprietary Microsoft Excel Format (see also: CSV).
     *
     * @see http://www.iana.org/assignments/media-types/application/vnd.ms-excel
     */
    XLS("application/vnd.ms-excel", "xls"),
    /**
     * Proprietary Microsoft Excel Format (see also: CSV).
     *
     * @see http://www.iana.org/assignments/media-types/application/vnd.ms-excel
     */
    XLSX("application/vnd.ms-excel", "xlsx");

    /**
     * The media type as defined by IANA and IETF.
     *
     * @see http://www.iana.org/assignments/media-types/media-types.xhtml
     */
    private final String mediaType;

    /**
     * The filename extension typically used for this format's media type.
     */
    private final String extension;

    /**
     * Paginate the report?
     */
    private final Boolean paginate;

    /**
     * Creates a new enumerated item with a particular media type and typical
     * filename extension. By default the report format is not paginated.
     *
     * @param mediaType The IETF-defined media type.
     * @param extension The typical filename extension for the media type.
     */
    private ReportFormat(final String mediaType, final String extension) {
        this(mediaType, extension, false);
    }

    /**
     * Creates a new enumerated item with a particular media type and typical
     * filename extension.
     *
     * @param mediaType The IETF-defined media type.
     * @param extension The typical filename extension for the media type.
     * @param paginate Set true when the output should be paginated.
     */
    private ReportFormat(
            final String mediaType,
            final String extension,
            final boolean paginate) {
        this.mediaType = mediaType;
        this.extension = extension;
        this.paginate = paginate;
    }

    /**
     * Returns the typical filename extension for this media type report format,
     * without the period separator.
     *
     * @return A string suitable for use as a filename extension.
     */
    public String getFilenameExtension() {
        return this.extension;
    }

    /**
     * Returns the media type (formerly MIME type) for this report format
     * suitable for inclusion in the content-header of an HTTP response.
     *
     * @return The report format media type.
     * @see http://www.iana.org/assignments/media-types/media-types.xhtml
     */
    public String getMediaType() {
        return this.mediaType;
    }

    /**
     * Answers whether the report format requires pagination. For most formats,
     * this will return false.
     *
     * @return true Paginate the report.
     */
    public Boolean isPaginated() {
        return this.paginate;
    }
}

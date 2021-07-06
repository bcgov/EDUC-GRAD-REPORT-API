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
 *  File:                $Id:: NumberLabel.java 9373 2018-02-03 00:33:12Z DAJA#$
 *  Date of Last Commit: $Date:: 2018-02-02 16:33:12 -0800 (Fri, 02 Feb 2018)  $
 *  Revision Number:     $Rev:: 9373                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.bundle.decorator;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

import java.awt.geom.Point2D;
import java.io.IOException;

import static com.lowagie.text.Element.ALIGN_RIGHT;
import static com.lowagie.text.pdf.ColumnText.showTextAligned;
import static java.lang.String.format;

/**
 * Represents information about a page label.
 *
 * @author CGI Information Management Consultants Inc.
 */
public abstract class NumberLabel {

    private static final float LABEL_FONT_SIZE = 8f;

    /**
     * Location on the PDF to write the number.
     */
    private Point2D coordinate;

    /**
     * The number to write on the PDF.
     */
    private int number;

    /**
     * Constructs a new number label used to write page and image numbers to
     * PDFs.
     *
     * @param coordinate The location on the PDF to write the number.
     * @param number The number to write on the PDF.
     */
    public NumberLabel(final Point2D coordinate, final int number) {
        setCoordinate(coordinate);
        getNumber(number);
    }

    /**
     * Return 'P' or 'I', depending on the type of number.
     *
     * @return The image or page number prefix.
     */
    protected abstract char getLabelPrefix();

    /**
     * Creates a number such as "I0000001" or "P0000001" based on the label
     * prefix.
     *
     * @return Formatted number.
     */
    private String getNumberText() {
        return format("%c%07d", getLabelPrefix(), getNumber());
    }

    /**
     * Writes this page number label to the given PDF content.
     *
     * @param pageContent The page to adorn with a page number.
     * @throws DocumentException Could not create phrase.
     * @throws IOException Could not read the font.
     */
    public void write(final PdfContentByte pageContent)
            throws DocumentException, IOException {
        showTextAligned(
                pageContent,
                getAlignment(),
                getPhrase(),
                getX(),
                getY(),
                getRotation());
    }

    /**
     * Converts the number label into a text string phrase with a specific font.
     *
     * @return @throws DocumentException Could not create phrase.
     * @throws IOException Could not read the font.
     */
    private Phrase getPhrase() throws DocumentException, IOException {
        return new Phrase(getNumberText(), getFont());
    }

    private float getFontSize() {
        return LABEL_FONT_SIZE;
    }

    private int getAlignment() {
        return ALIGN_RIGHT;
    }

    /**
     * Returns the X location (horizontal) of where to write the page number.
     *
     * @return A positive float.
     */
    private float getX() {
        return (float) getCoordinate().getX();
    }

    /**
     * Returns the Y location (vertical) of where to write the page number.
     *
     * @return A positive float.
     */
    private float getY() {
        return (float) getCoordinate().getY();
    }

    /**
     * Returns the number of degrees to rotate the text.
     *
     * @return 90
     */
    private int getRotation() {
        return 90;
    }

    private Point2D getCoordinate() {
        return this.coordinate;
    }

    private void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }

    private int getNumber() {
        return this.number;
    }

    private void getNumber(int text) {
        this.number = text;
    }

    /**
     * Returns the font to use for writing number labels.
     *
     * @return The font to use for writing the labels.
     * @throws DocumentException Could not write the document.
     * @throws IOException Could not read the font.
     */
    private Font getFont() throws DocumentException, IOException {
        final Font font = new Font(
                BaseFont.createFont(
                        getFontName(),
                        getFontEncoding(),
                        BaseFont.EMBEDDED)
        );

        font.setSize(getFontSize());

        return font;
    }

    /**
     * Returns the font name used for the number label.
     *
     * @return "Helvetica" by default.
     */
    private String getFontName() {
        return "Helvetica";
    }

    /**
     * Returns the font encoding used for the number label.
     *
     * @return "UTF-8" by default.
     */
    private String getFontEncoding() {
        return "UTF-8";
    }
}

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
 *  File:                $Id:: NumberLabel.java 9373 2018-02-03 00:33:12Z DAJA#$
 *  Date of Last Commit: $Date:: 2018-02-02 16:33:12 -0800 (Fri, 02 Feb 2018)  $
 *  Revision Number:     $Rev:: 9373                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;

import java.awt.geom.Point2D;
import java.io.IOException;

import static com.itextpdf.text.Element.ALIGN_RIGHT;
import static com.itextpdf.text.pdf.ColumnText.showTextAligned;
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
     * The rotation degree.
     */
    private int rotate = 90;

    private String numberFormat = "%s%07d";

    private String labelPrefix = "";

    /**
     * Constructs a new number label used to write page and image numbers to
     * PDFs.
     *
     * @param coordinate The location on the PDF to write the number.
     * @param number The number to write on the PDF.
     */
    public NumberLabel(final Point2D coordinate, final int number) {
        setCoordinate(coordinate);
        setNumber(number);
    }

    /**
     * Constructs a new number label used to write page and image numbers to
     * PDFs.
     *
     * @param coordinate The location on the PDF to write the number.
     * @param number The number to write on the PDF.
     */
    public NumberLabel(final Point2D coordinate, final int number, final int degree) {
        setCoordinate(coordinate);
        setNumber(number);
        setRotate(degree);
    }

    public void setLabelPrefix(String labelPrefix) {
        this.labelPrefix = labelPrefix;
    }

    public String getLabelPrefix() {
        return labelPrefix;
    }

    /**
     * Creates a number such as "I0000001" or "P0000001" based on the label
     * prefix.
     *
     * @return Formatted number.
     */
    protected String getNumberText() {
        return format(numberFormat, getLabelPrefix(), getNumber());
    }

    public void setNumberFormat(String numberFormat) {
        this.numberFormat = numberFormat;
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
     * @return rotateDegree
     */
    private int getRotation() {
        return this.rotate;
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

    private void setNumber(int text) {
        this.number = text;
    }

    private void setRotate(int degree) {
        this.rotate = degree;
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
        font.setStyle(Font.BOLD);

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

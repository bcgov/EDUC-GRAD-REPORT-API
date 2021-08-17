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
 *  File:                $Id:: EmbeddedImageTranscoder.java 11094 2018-08-08 1#$
 *  Date of Last Commit: $Date:: 2018-08-08 11:27:21 -0700 (Wed, 08 Aug 2018)  $
 *  Revision Number:     $Rev:: 11094                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.jasper;

import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.batik.gvt.renderer.ImageRenderer;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static ca.bc.gov.educ.isd.reports.jasper.impl.JasperReportImpl.HTML_SCALE_FACTOR;
import static java.awt.Color.white;
import static java.awt.RenderingHints.*;
import static java.lang.Boolean.TRUE;
import static org.apache.batik.transcoder.SVGAbstractTranscoder.KEY_WIDTH;
import static org.apache.batik.transcoder.image.ImageTranscoder.KEY_BACKGROUND_COLOR;
import static org.apache.batik.transcoder.image.ImageTranscoder.KEY_FORCE_TRANSPARENT_WHITE;

/**
 * Uses a third-party library to convert an image from SVG format into PNG
 * format. The PNG data returned base-64 encoded.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class EmbeddedImageTranscoder {

    // Report signature image sizes.
    private final static int BUFFER_SIZE = 2048;

    /**
     * Opens the named resource and returns its content as a stream of bytes.
     *
     * @param file The resource containing SVG data.
     * @return The contents of an SVG file.
     * @throws IOException Could not read the file.
     */
    public static InputStream asSVG(final String file) throws IOException {
        final byte[] in = load(file);
        return new ByteArrayInputStream(in);
    }

    /**
     * Converts the contents of the given file from SVG format to PNG format.
     *
     * @param file SVG file resource.
     * @return An input stream containing a PNG image.
     * @throws TranscoderException Could not convert SVG to PNG.
     * @throws IOException Could not find the SVG resource.
     */
    public static InputStream asPNG(final String file)
            throws TranscoderException, IOException {
        final byte[] in = load(file);
        return asPNG(in);
    }

    /**
     * Convenience method used for testing the transcoding process.
     *
     * @param svg The SVG content to change PNG format.
     * @return A PNG image.
     * @throws TranscoderException Could not transform from SVG to PNG.
     */
    public static InputStream asPNG(final byte[] svg)
            throws TranscoderException {
        final ByteArrayOutputStream outBytes = new ByteArrayOutputStream(BUFFER_SIZE);
        final ByteArrayInputStream inBytes = new ByteArrayInputStream(svg);

        final TranscoderInput input = new TranscoderInput(inBytes);
        final TranscoderOutput output = new TranscoderOutput(outBytes);
        final PNGTranscoder transcoder = new PNGTranscoder();

        // Some browsers don't support transparent PNGs, opting for gray or
        // black instead of substituting transparent pixels with white.
        // This setting changes background transparency to white and any
        // other transparent colour to white.
        transcoder.addTranscodingHint(KEY_BACKGROUND_COLOR, white);
        transcoder.addTranscodingHint(KEY_FORCE_TRANSPARENT_WHITE, TRUE);

        // 88 is the PNG width (in px) after transcoding from SVG format.
        // 88px is equivalent to 66pt.
        transcoder.addTranscodingHint(KEY_WIDTH, 88f * HTML_SCALE_FACTOR);
        transcoder.transcode(input, output);

        final byte[] bytes = outBytes.toByteArray();
        return new ByteArrayInputStream(bytes);
    }

    /**
     * Loads a resource into a byte sequence as specified by the given file.
     *
     * @param file The file to load as bytes.
     * @return The contents of the specified file as a byte sequence.
     * @throws IOException Could not load the bytes from the file.
     */
    private static byte[] load(final String file) throws IOException {
        byte results[];

        try {
            results = JRLoader.loadBytesFromResource(file);
        } catch (final Exception ex) {
            throw new IOException(ex);
        }

        return results;
    }

    /**
     * Creates a new high-quality PNG transcoder.
     *
     * For some reason, this doesn't create a valid PNGTranscoder instance that
     * can produce high-quality output. This method can be removed or
     * investigated and fixed (and used around line 90 above).
     *
     * @see https://stackoverflow.com/a/13550493/59087
     * @return A transcoder for transforming SVG sources to PNG images.
     */
    private static PNGTranscoder createPNGTranscoder() {
        return new PNGTranscoder() {
            @Override
            protected ImageRenderer createRenderer() {
                final ImageRenderer ir = super.createRenderer();
                final RenderingHints rh = ir.getRenderingHints();

                rh.add(hint(KEY_ALPHA_INTERPOLATION, VALUE_ALPHA_INTERPOLATION_QUALITY));
                rh.add(hint(KEY_INTERPOLATION, VALUE_INTERPOLATION_BICUBIC));
                rh.add(hint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON));
                rh.add(hint(KEY_COLOR_RENDERING, VALUE_COLOR_RENDER_QUALITY));
                rh.add(hint(KEY_DITHERING, VALUE_DITHER_DISABLE));
                rh.add(hint(KEY_RENDERING, VALUE_RENDER_QUALITY));
                rh.add(hint(KEY_STROKE_CONTROL, VALUE_STROKE_PURE));
                rh.add(hint(KEY_FRACTIONALMETRICS, VALUE_FRACTIONALMETRICS_ON));

                ir.setRenderingHints(rh);

                return ir;
            }
        };
    }

    private static RenderingHints hint(final Key key, final Object value) {
        return new RenderingHints(key, value);
    }
}

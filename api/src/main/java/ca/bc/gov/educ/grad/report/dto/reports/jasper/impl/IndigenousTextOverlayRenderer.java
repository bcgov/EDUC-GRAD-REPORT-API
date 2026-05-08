package ca.bc.gov.educ.grad.report.dto.reports.jasper.impl;

import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.base.JRBasePrintImage;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;
import net.sf.jasperreports.renderers.SimpleDataRenderer;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextHitInfo;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.AttributedString;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Replaces only PDF text elements containing Indigenous grapheme combining
 * marks with Java2D-rendered image overlays. Jasper/iText keeps the Unicode
 * data, but its normal PDF text path does not position these marks correctly.
 */
final class IndigenousTextOverlayRenderer {

    private static final Logger LOG = Logger.getLogger(IndigenousTextOverlayRenderer.class.getName());
    private static final int IMAGE_SCALE = 4;
    private static final int COMBINING_COMMA_ABOVE = 0x0313;
    private static final String FONT_BC_SANS = "BCSans";
    private static final Map<String, Font> FONT_CACHE = new ConcurrentHashMap<>();

    private IndigenousTextOverlayRenderer() {
    }

    static void apply(final JasperPrint print) {
        int replaced = 0;

        for (final JRPrintPage page : print.getPages()) {
            replaced += overlayElements(page.getElements());
        }

        if (replaced > 0) {
            LOG.log(Level.INFO, "Applied Indigenous grapheme PDF text overlays to {0} Jasper text elements", replaced);
        }
    }

    private static int overlayElements(final List<JRPrintElement> elements) {
        final List<JRPrintElement> updatedElements = new ArrayList<>(elements.size());
        int replaced = 0;

        for (final JRPrintElement element : elements) {
            updatedElements.add(element);

            if (element instanceof JRPrintFrame) {
                replaced += overlayElements(((JRPrintFrame) element).getElements());
            } else if (element instanceof JRPrintText) {
                final JRBasePrintImage overlay = createOverlay((JRPrintText) element);
                if (overlay != null) {
                    element.setForecolor(hiddenTextColor((JRPrintText) element));
                    updatedElements.add(overlay);
                    replaced++;
                }
            }
        }

        elements.clear();
        elements.addAll(updatedElements);
        return replaced;
    }

    private static JRBasePrintImage createOverlay(final JRPrintText textElement) {
        final String text = visibleText(textElement);
        if (!needsOverlay(text) || textElement.getWidth() <= 0 || textElement.getHeight() <= 0) {
            return null;
        }

        if (textElement.getRotationValue() != null && textElement.getRotationValue() != RotationEnum.NONE) {
            return null;
        }

        try {
            final byte[] imageBytes = renderText(textElement, text);
            final JRBasePrintImage image = new JRBasePrintImage(textElement.getDefaultStyleProvider());
            image.setX(textElement.getX());
            image.setY(textElement.getY());
            image.setWidth(textElement.getWidth());
            image.setHeight(textElement.getHeight());
            image.setMode(ModeEnum.TRANSPARENT);
            image.setScaleImage(ScaleImageEnum.FILL_FRAME);
            image.setHorizontalImageAlign(HorizontalImageAlignEnum.LEFT);
            image.setVerticalImageAlign(VerticalImageAlignEnum.TOP);
            image.setOnErrorType(OnErrorTypeEnum.ERROR);
            image.setUsingCache(false);
            image.setRenderer(SimpleDataRenderer.getInstance(imageBytes));
            return image;
        } catch (final IOException | FontFormatException ex) {
            LOG.log(Level.WARNING, "Unable to create Indigenous grapheme PDF text overlay; falling back to Jasper text", ex);
            return null;
        }
    }

    private static byte[] renderText(final JRPrintText textElement, final String text)
            throws IOException, FontFormatException {
        final int imageWidth = Math.max(1, textElement.getWidth() * IMAGE_SCALE);
        final int imageHeight = Math.max(1, textElement.getHeight() * IMAGE_SCALE);
        final BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D graphics = image.createGraphics();
        try {
            graphics.scale(IMAGE_SCALE, IMAGE_SCALE);
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            graphics.setColor(textElement.getForecolor() == null ? Color.BLACK : textElement.getForecolor());
            graphics.setFont(resolveFont(textElement));

            drawText(graphics, textElement, text);
        } finally {
            graphics.dispose();
        }

        try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", out);
            return out.toByteArray();
        }
    }

    private static void drawText(final Graphics2D graphics, final JRPrintText textElement, final String text) {
        final float leftPadding = padding(textElement.getLineBox().getLeftPadding());
        final float rightPadding = padding(textElement.getLineBox().getRightPadding());
        final float topPadding = padding(textElement.getLineBox().getTopPadding());
        final float bottomPadding = padding(textElement.getLineBox().getBottomPadding());
        final float contentWidth = Math.max(1f, textElement.getWidth() - leftPadding - rightPadding);
        final float contentHeight = Math.max(1f, textElement.getHeight() - topPadding - bottomPadding);
        final FontRenderContext frc = graphics.getFontRenderContext();
        graphics.setFont(fitSingleLineFont(text, graphics.getFont(), frc, contentWidth, contentHeight, textElement));
        final List<LineLayout> lines = layoutText(text, graphics.getFont(), frc, contentWidth, textElement);
        final float totalHeight = lines.stream().map(LineLayout::height).reduce(0f, Float::sum);
        float y = topPadding + verticalOffset(textElement, contentHeight, totalHeight);

        for (final LineLayout line : lines) {
            y += line.ascent();
            line.draw(graphics, leftPadding + horizontalOffset(textElement, contentWidth, line), y);
            y += line.descent() + line.leading();
        }
    }

    private static List<LineLayout> layoutText(final String text, final Font font, final FontRenderContext frc,
                                               final float width, final JRPrintText textElement) {
        final List<LineLayout> lines = new ArrayList<>();
        final String[] paragraphs = text.split("\\r\\n|\\r|\\n", -1);

        for (final String paragraph : paragraphs) {
            if (paragraph.isEmpty()) {
                lines.add(new LineLayout(new TextLayout(" ", font, frc)));
                continue;
            }

            final AttributedString attributed = new AttributedString(paragraph);
            attributed.addAttribute(java.awt.font.TextAttribute.FONT, font);
            final LineBreakMeasurer measurer = new LineBreakMeasurer(attributed.getIterator(), frc);
            final int end = paragraph.length();

            while (measurer.getPosition() < end) {
                final int start = measurer.getPosition();
                measurer.nextLayout(width);
                final int lineEnd = measurer.getPosition();
                final boolean lastLine = measurer.getPosition() >= end;
                final String lineText = paragraph.substring(start, lineEnd);
                lines.add(new LineLayout(lineText, font, frc, width, lastLine, textElement));
            }
        }

        return lines;
    }

    private static Font fitSingleLineFont(final String text, final Font font, final FontRenderContext frc,
                                          final float width, final float height, final JRPrintText textElement) {
        if (text.contains("\n") || text.contains("\r") || textElement.getHeight() > font.getSize2D() * 2.5f) {
            return font;
        }

        final String baseText = stripManualMarks(text).text;
        final TextLayout layout = new TextLayout(baseText.isEmpty() ? " " : baseText, font, frc);
        final float layoutHeight = layout.getAscent() + layout.getDescent() + layout.getLeading();
        final float widthScale = layout.getAdvance() <= 0f ? 1f : width / layout.getAdvance();
        final float heightScale = layoutHeight <= 0f ? 1f : height / layoutHeight;
        final float scale = Math.min(1f, Math.min(widthScale, heightScale));

        if (scale >= 1f) {
            return font;
        }
        return font.deriveFont(Math.max(4f, font.getSize2D() * scale * 0.98f));
    }

    private static TextLayout justify(final TextLayout layout, final float width, final boolean lastLine,
                                      final JRPrintText textElement) {
        if (!lastLine && textElement.getHorizontalTextAlign() == HorizontalTextAlignEnum.JUSTIFIED) {
            return layout.getJustifiedLayout(width);
        }
        return layout;
    }

    private static float horizontalOffset(final JRPrintText textElement, final float width, final LineLayout line) {
        final HorizontalTextAlignEnum alignment = textElement.getHorizontalTextAlign();

        if (alignment == HorizontalTextAlignEnum.CENTER) {
            return Math.max(0f, (width - line.advance()) / 2f);
        }
        if (alignment == HorizontalTextAlignEnum.RIGHT) {
            return Math.max(0f, width - line.advance());
        }
        return 0f;
    }

    private static float verticalOffset(final JRPrintText textElement, final float height, final float textHeight) {
        final VerticalTextAlignEnum alignment = textElement.getVerticalTextAlign();

        if (alignment == VerticalTextAlignEnum.MIDDLE) {
            return Math.max(0f, (height - textHeight) / 2f);
        }
        if (alignment == VerticalTextAlignEnum.BOTTOM) {
            return Math.max(0f, height - textHeight);
        }
        return 0f;
    }

    private static Font resolveFont(final JRPrintText textElement) throws IOException, FontFormatException {
        final String fontName = textElement.getFontName();
        final boolean bold = textElement.isBold();
        final boolean italic = textElement.isItalic();
        final float size = textElement.getFontsize();

        if (FONT_BC_SANS.equalsIgnoreCase(fontName) || "BC Sans".equalsIgnoreCase(fontName)) {
            return loadBcSans(bold, italic).deriveFont(size);
        }

        final int style = (bold ? Font.BOLD : Font.PLAIN) | (italic ? Font.ITALIC : Font.PLAIN);
        return new Font(fontName == null ? Font.SANS_SERIF : fontName, style, Math.round(size)).deriveFont(size);
    }

    private static Font loadBcSans(final boolean bold, final boolean italic) throws IOException, FontFormatException {
        final String key = bold + ":" + italic;
        final Font cached = FONT_CACHE.get(key);
        if (cached != null) {
            return cached;
        }

        final String resource = "fonts/BCSans/" + fontFile(bold, italic);
        try (final InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(resource)) {
            if (inputStream == null) {
                return new Font("BC Sans", fontStyle(bold, italic), 10);
            }

            final Font loaded = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            FONT_CACHE.put(key, loaded);
            return loaded;
        }
    }

    private static String fontFile(final boolean bold, final boolean italic) {
        if (bold && italic) {
            return "BCSans-BoldItalic.ttf";
        }
        if (bold) {
            return "BCSans-Bold.ttf";
        }
        if (italic) {
            return "BCSans-Italic.ttf";
        }
        return "BCSans-Regular.ttf";
    }

    private static int fontStyle(final boolean bold, final boolean italic) {
        return (bold ? Font.BOLD : Font.PLAIN) | (italic ? Font.ITALIC : Font.PLAIN);
    }

    private static boolean needsOverlay(final String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }

        final String normalized = Normalizer.normalize(text, Normalizer.Form.NFC);
        return normalized.codePoints().anyMatch(IndigenousTextOverlayRenderer::isIndigenousGraphemeCodePoint);
    }

    private static boolean isIndigenousGraphemeCodePoint(final int codePoint) {
        return (codePoint >= 0x0300 && codePoint <= 0x036F)
                || (codePoint >= 0x1AB0 && codePoint <= 0x1AFF)
                || (codePoint >= 0x1DC0 && codePoint <= 0x1DFF)
                || (codePoint >= 0x20D0 && codePoint <= 0x20FF)
                || (codePoint >= 0xFE20 && codePoint <= 0xFE2F)
                || codePoint == 0x0294
                || codePoint == 0x02B7
                || codePoint == 0x0259;
    }

    private static String visibleText(final JRPrintText textElement) {
        final String text = textElement.getOriginalText();
        if (text == null) {
            return null;
        }

        final Integer truncateIndex = textElement.getTextTruncateIndex();
        final StringBuilder visibleText = new StringBuilder();
        if (truncateIndex == null) {
            visibleText.append(text);
        } else {
            visibleText.append(text, 0, Math.min(truncateIndex, text.length()));
        }

        if (textElement.getTextTruncateSuffix() != null) {
            visibleText.append(textElement.getTextTruncateSuffix());
        }
        return visibleText.toString();
    }

    private static float padding(final Integer value) {
        return value == null ? 0f : value;
    }

    private static Color hiddenTextColor(final JRPrintText textElement) {
        if (textElement.getModeValue() == ModeEnum.OPAQUE && textElement.getBackcolor() != null) {
            return textElement.getBackcolor();
        }
        return Color.WHITE;
    }

    private static StrippedText stripManualMarks(final String text) {
        final StringBuilder stripped = new StringBuilder();
        final List<ManualMark> marks = new ArrayList<>();
        int previousBaseOffset = -1;

        for (int offset = 0; offset < text.length();) {
            final int codePoint = text.codePointAt(offset);
            final int charCount = Character.charCount(codePoint);

            if (codePoint == COMBINING_COMMA_ABOVE) {
                marks.add(new ManualMark(previousBaseOffset, codePoint));
            } else {
                final int strippedOffset = stripped.length();
                stripped.appendCodePoint(codePoint);
                if (!isCombiningMark(codePoint)) {
                    previousBaseOffset = strippedOffset;
                }
            }

            offset += charCount;
        }

        return new StrippedText(stripped.toString(), marks);
    }

    private static boolean isCombiningMark(final int codePoint) {
        final int type = Character.getType(codePoint);
        return type == Character.NON_SPACING_MARK
                || type == Character.COMBINING_SPACING_MARK
                || type == Character.ENCLOSING_MARK;
    }

    private static final class StrippedText {
        private final String text;
        private final List<ManualMark> marks;

        private StrippedText(final String text, final List<ManualMark> marks) {
            this.text = text;
            this.marks = marks;
        }
    }

    private static final class ManualMark {
        private final int previousBaseOffset;
        private final int codePoint;

        private ManualMark(final int previousBaseOffset, final int codePoint) {
            this.previousBaseOffset = previousBaseOffset;
            this.codePoint = codePoint;
        }
    }

    private static final class LineLayout {
        private final TextLayout baseLayout;
        private final List<ManualMark> marks;
        private final Font font;
        private final FontRenderContext fontRenderContext;

        private LineLayout(final TextLayout layout) {
            this.baseLayout = layout;
            this.marks = new ArrayList<>();
            this.font = null;
            this.fontRenderContext = null;
        }

        private LineLayout(final String text, final Font font, final FontRenderContext fontRenderContext,
                           final float width, final boolean lastLine, final JRPrintText textElement) {
            final StrippedText stripped = stripManualMarks(text);
            final String baseText = stripped.text.isEmpty() ? " " : stripped.text;
            this.baseLayout = justify(new TextLayout(baseText, font, fontRenderContext),
                    width, lastLine, textElement);
            this.marks = stripped.marks;
            this.font = font;
            this.fontRenderContext = fontRenderContext;
        }

        private void draw(final Graphics2D graphics, final float x, final float baseline) {
            baseLayout.draw(graphics, x, baseline);
            if (marks.isEmpty() || font == null || fontRenderContext == null) {
                return;
            }

            for (final ManualMark mark : marks) {
                if (mark.previousBaseOffset < 0 || mark.previousBaseOffset >= baseLayout.getCharacterCount()) {
                    continue;
                }

                final TextLayout markLayout = new TextLayout(new String(Character.toChars(mark.codePoint)),
                        font, fontRenderContext);
                final Rectangle2D markBounds = markLayout.getBounds();
                final float[] before = baseLayout.getCaretInfo(TextHitInfo.beforeOffset(mark.previousBaseOffset));
                final float[] after = baseLayout.getCaretInfo(TextHitInfo.afterOffset(mark.previousBaseOffset));
                final float baseGlyphCenter = (before[0] + after[0]) / 2f;
                final float markX = x + baseGlyphCenter - (float) markBounds.getCenterX();

                markLayout.draw(graphics, markX, baseline);
            }
        }

        private float advance() {
            return baseLayout.getAdvance();
        }

        private float ascent() {
            return baseLayout.getAscent();
        }

        private float descent() {
            return baseLayout.getDescent();
        }

        private float leading() {
            return baseLayout.getLeading();
        }

        private float height() {
            return ascent() + descent() + leading();
        }
    }
}

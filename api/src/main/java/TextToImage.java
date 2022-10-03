import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextToImage {

    private int paragraphStart;
    private int paragraphEnd;

    private int computeImageHeight(AttributedString attrString, int maxWidth) {
        BufferedImage buffRenderImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = buffRenderImage.createGraphics();
        AttributedCharacterIterator paragraph = attrString.getIterator();
        paragraphStart = paragraph.getBeginIndex();
        paragraphEnd = paragraph.getEndIndex();
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(paragraph, frc);

        float breakWidth = maxWidth;
        float drawPosY = 0;

        // Set position to the index of the first character in the paragraph.
        lineMeasurer.setPosition(paragraphStart);

        // Get lines until the entire paragraph has been displayed.
        while (lineMeasurer.getPosition() < paragraphEnd) {

            // Retrieve next layout. A cleverer program would also cache
            // these layouts until the component is re-sized.
            TextLayout layout = lineMeasurer.nextLayout(breakWidth);
            // Compute pen x position. If the paragraph is right-to-left we
            // will align the TextLayouts to the right edge of the panel.
            // Note: this won't occur for the English text in this sample.
            // Note: drawPosX is always where the LEFT of the text is placed.
//            float drawPosX = layout.isLeftToRight()
//                    ? 0 : breakWidth - layout.getAdvance();

            // Move y-coordinate by the ascent of the layout.
            drawPosY += layout.getAscent();

            // Draw the TextLayout at (drawPosX, drawPosY).

            // Move y-coordinate in preparation for next layout.
            drawPosY += layout.getDescent() + layout.getLeading();
        }

        g2d.dispose();
        return (int) Math.ceil(drawPosY);
    }

    /**
     * Converts the given text to image.
     *
     * @param text             text to convert
     * @param font             font to use when drawing text
     * @param maxWidth         the max width of the text
     * @param widthAdjustment  adjustment value to leave equal size before and
     *                         after the text
     * @param heightAdjustment adjustment value to leave equal size before and
     *                         after the text
     * @param foreground       foreground value
     * @param filename         absolute path to file
     * @throws IOException
     */
    public void convertToImage(String text, Font font, int maxWidth, int widthAdjustment, int heightAdjustment, Color foreground, String filename) throws IOException {
        final Map<TextAttribute, Object> map =
                new HashMap<>();
        map.put(TextAttribute.FONT, font);
        map.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
        map.put(TextAttribute.LIGATURES, TextAttribute.LIGATURES_ON);
        map.put(TextAttribute.JUSTIFICATION, TextAttribute.JUSTIFICATION_FULL);
        AttributedString attrString = new AttributedString(text, map);
        int width = maxWidth + widthAdjustment;
        int height = computeImageHeight(attrString, maxWidth) + heightAdjustment;
        BufferedImage buffRenderImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = buffRenderImage.createGraphics();
        AttributedCharacterIterator paragraph = attrString.getIterator();
        paragraphStart = paragraph.getBeginIndex();
        paragraphEnd = paragraph.getEndIndex();
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(paragraph, frc);


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setColor(foreground);

        // Set break width to width of maxWidth.
        float breakWidth = maxWidth;
        float drawPosY = 0;

        // Set position to the index of the first character in the paragraph.
        lineMeasurer.setPosition(paragraphStart);
        //took from Oracle's LineBreakSample.java
        // Get lines until the entire paragraph has been displayed.
        while (lineMeasurer.getPosition() < paragraphEnd) {

            // Retrieve next layout.
            TextLayout layout = lineMeasurer.nextLayout(breakWidth);
            // Compute pen x position. If the paragraph is right-to-left we
            // will align the TextLayouts to the right edge of the panel.
            float drawPosX = layout.isLeftToRight()
                    ? 0 : breakWidth - layout.getAdvance();

            // Move y-coordinate by the ascent of the layout.
            drawPosY += layout.getAscent();

            // Draw the TextLayout at (drawPosX, drawPosY).
            layout.draw(g2d, drawPosX + (widthAdjustment / 2), drawPosY + (heightAdjustment / 2));

            // Move y-coordinate in preparation for next layout.
            drawPosY += layout.getDescent() + layout.getLeading();
        }
        g2d.dispose();
        File file = new File(filename);

        ImageIO.write(buffRenderImage, "png", file);
    }

    public static void main(String[] args) {
        TextToImage tti = new TextToImage();
        String fontname = "Mistral";
        int fontStyle = Font.ITALIC;
        int fontSize = 30;
        int maxWidth = 320;
        int widthAdjustment = 10;
        int heightAdjustment = 30;
        Color color = Color.BLUE;
        String filePath = "C:\\Users\\kamal.mohammed\\Desktop\\Kamal\\MOE\\Database\\SCHEMAS\\API_GRAD_REPORT\\signatures-fake\\";

        String text = "   Signature  ";
        String fileName = "";

        for (int i=1; i<100; i++) {
            fileName = "";
            try {
                if (i<10)
                    fileName = fileName.concat("0");
                fileName = fileName.concat(String.valueOf(i)).concat(".png");

                tti.convertToImage(text.concat(fileName), new Font(fontname, fontStyle, fontSize),
                        maxWidth, widthAdjustment, heightAdjustment, color, filePath.concat(fileName));
            } catch (IOException ex) {
                Logger.getLogger(TextToImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String[] otherSignatures = {"independent.png", "MOAE.png", "MOE.png", "SALLY3.png"};
        for (int i=0; i<otherSignatures.length; i++) {
            fileName = otherSignatures[i];
            try {
                tti.convertToImage(text.concat(fileName), new Font(fontname, fontStyle, fontSize),
                        maxWidth, widthAdjustment, heightAdjustment, color, filePath.concat(fileName));
            } catch (IOException ex) {
                Logger.getLogger(TextToImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            tti.convertToImage("  ", new Font(fontname, fontStyle, fontSize),
                    maxWidth, widthAdjustment, heightAdjustment, color, filePath.concat("blank.png"));
        } catch (IOException ex) {
            Logger.getLogger(TextToImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

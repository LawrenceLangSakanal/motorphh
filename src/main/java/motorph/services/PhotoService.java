package motorph.services;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import motorph.ui.theme.Icons;

public class PhotoService {

    private static final String PHOTO_DIR = "data/profile_photos";

    public PhotoService() {
        new File(PHOTO_DIR).mkdirs();
    }

    public ImageIcon loadPhoto(int employeeId, int width, int height) {
        File photo = new File(PHOTO_DIR, employeeId + ".png");
        if (photo.exists()) {
            try {
                BufferedImage img = ImageIO.read(photo);
                Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(makeCircular(scaled, width, height));
            } catch (Exception ignored) {}
        }
        ImageIcon def = Icons.loadDefaultAvatar(width, height);
        if (def != null) return def;
        return new ImageIcon(createPlaceholder(width, height));
    }

    public boolean savePhoto(int employeeId, File sourceFile) {
        try {
            BufferedImage img = ImageIO.read(sourceFile);
            if (img == null) return false;
            File dest = new File(PHOTO_DIR, employeeId + ".png");
            return ImageIO.write(img, "png", dest);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private BufferedImage makeCircular(Image img, int w, int h) {
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillOval(0, 0, w, h);
        g2.setComposite(AlphaComposite.SrcIn);
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();
        return output;
    }

    private BufferedImage createPlaceholder(int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0xCBD5E1));
        g2.fillOval(0, 0, w, h);
        // Draw person silhouette
        g2.setColor(new Color(0x94A3B8));
        int headR = w / 5;
        g2.fillOval(w / 2 - headR, h / 4 - headR / 2, headR * 2, headR * 2);
        g2.fillArc(w / 2 - w / 3, h / 2 + headR / 2, (w * 2) / 3, h / 2, 0, 180);
        g2.dispose();
        return img;
    }
}

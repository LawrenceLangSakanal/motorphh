package motorph.ui.components;

import java.awt.*;
import javax.swing.*;
import motorph.services.PhotoService;
import motorph.ui.theme.Theme;

public class AvatarPanel extends JPanel {

    private final int employeeId;
    private final PhotoService photoService;
    private JLabel avatarLabel;
    private static final int AVATAR_SIZE = 120;

    public AvatarPanel(int employeeId, PhotoService photoService) {
        this.employeeId = employeeId;
        this.photoService = photoService;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        avatarLabel = new JLabel();
        avatarLabel.setAlignmentX(CENTER_ALIGNMENT);
        loadAvatar();
        add(avatarLabel);

        add(Box.createVerticalStrut(8));

        AppButton uploadBtn = new AppButton("Upload Photo", AppButton.Style.SECONDARY);
        uploadBtn.setAlignmentX(CENTER_ALIGNMENT);
        uploadBtn.setPreferredSize(new Dimension(130, 28));
        uploadBtn.setFont(Theme.FONT_SMALL);
        uploadBtn.addActionListener(e -> uploadPhoto());
        add(uploadBtn);
    }

    private void loadAvatar() {
        avatarLabel.setIcon(photoService.loadPhoto(employeeId, AVATAR_SIZE, AVATAR_SIZE));
    }

    private void uploadPhoto() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "png", "jpg", "jpeg"));
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (photoService.savePhoto(employeeId, fc.getSelectedFile())) {
                loadAvatar();
                JOptionPane.showMessageDialog(this, "Photo updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save photo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

package motorph.ui.router;

import java.awt.*;
import javax.swing.*;

public class AppRouter {

    private final CardLayout cardLayout;
    private final JPanel contentPanel;
    private Runnable logoutAction;

    public AppRouter() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
    }

    public void addPage(String name, JPanel page) {
        contentPanel.add(page, name);
    }

    public void navigateTo(String name) {
        cardLayout.show(contentPanel, name);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setLogoutAction(Runnable action) {
        this.logoutAction = action;
    }

    public void logout() {
        if (logoutAction != null) logoutAction.run();
    }
}

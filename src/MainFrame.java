import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GridLayout;

public class MainFrame extends JFrame {
    private ActivityManager activityManager;

    public void init() {
        setTitle("Daily Activity Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));

        activityManager = new ActivityManager();

        // Use GridLayout for splitting into two equal panels
        setLayout(new GridLayout(1, 2));

        // Create panels
        AddActivityPanel addActivityPanel = new AddActivityPanel(activityManager);
        ViewActivityPanel viewActivityPanel = new ViewActivityPanel(activityManager);

        // Add both panels to the frame
        add(addActivityPanel);
        add(viewActivityPanel);

        setVisible(true);
    }
}

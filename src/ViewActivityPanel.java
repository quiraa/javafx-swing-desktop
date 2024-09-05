import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.time.format.DateTimeFormatter;

public class ViewActivityPanel extends JPanel implements ActivityListener {
    private JTextArea priorityTextArea;
    private JTextArea nonPriorityTextArea;
    private ActivityManager activityManager;

    public ViewActivityPanel(ActivityManager activityManager) {
        this.activityManager = activityManager;
        activityManager.addListener(this); // Register this panel as a listener
        init();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel priorityLabel = new JLabel("Kegiatan Prioritas");
        priorityLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(priorityLabel);

        priorityTextArea = new JTextArea(10, 30);
        priorityTextArea.setEditable(false);
        add(new JScrollPane(priorityTextArea));

        JLabel nonPriorityLabel = new JLabel("Kegiatan Non-Prioritas");
        nonPriorityLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(nonPriorityLabel);

        nonPriorityTextArea = new JTextArea(10, 30);
        nonPriorityTextArea.setEditable(false);
        add(new JScrollPane(nonPriorityTextArea));

        updateTextArea(); // Populate UI initially
    }

    @Override
    public void onActivityListChanged() {
        updateTextArea(); // Refresh UI whenever activity list is updated
    }

    public void updateTextArea() {
        List<Activity> activities = activityManager.getActivities();

        String prioritizedActivities = activities.stream()
                .filter(Activity::isPrioritize)
                .map(this::formatActivity)
                .collect(Collectors.joining("\n"));

        String nonPrioritizedActivities = activities.stream()
                .filter(activity -> !activity.isPrioritize())
                .map(this::formatActivity)
                .collect(Collectors.joining("\n"));

        priorityTextArea
                .setText(prioritizedActivities.isEmpty() ? "Tidak ada kegiatan prioritas" : prioritizedActivities);
        nonPriorityTextArea.setText(
                nonPrioritizedActivities.isEmpty() ? "Tidak ada kegiatan non-prioritas" : nonPrioritizedActivities);
    }

    private String formatActivity(Activity activity) {
        return String.format("%s\nMulai: %s\nSelesai: %s",
                activity.getName(),
                activity.getStartTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                activity.getEndTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }
}
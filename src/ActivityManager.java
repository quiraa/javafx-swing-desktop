import java.util.*;

public class ActivityManager {
    private List<Activity> activities;
    private List<ActivityListener> listeners;

    public ActivityManager() {
        activities = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        notifyListeners(); // Notify all listeners that the activities list has changed
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addListener(ActivityListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ActivityListener listener : listeners) {
            listener.onActivityListChanged(); // Notify all listeners to refresh their UI
        }
    }
}
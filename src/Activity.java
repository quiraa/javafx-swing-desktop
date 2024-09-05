import java.time.LocalDateTime;

public class Activity {
    private final String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private boolean prioritize;

    public Activity(String name, LocalDateTime startTime, LocalDateTime endTime, boolean prioritize) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.prioritize = prioritize;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public boolean isPrioritize() {
        return prioritize;
    }
}

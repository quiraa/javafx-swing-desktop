
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeValidator {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static LocalDateTime validateAndParse(String dateTimeStr) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    public static boolean isEndTimeAfterStartTime(LocalDateTime startTime, LocalDateTime endTime) {
        return endTime.isAfter(startTime);
    }
}

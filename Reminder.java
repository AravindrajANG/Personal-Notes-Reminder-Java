
import java.time.LocalDateTime;

public class Reminder {
    private int id;
    private String message;
    private LocalDateTime reminderTime;

    public Reminder(int id, String message,
                    LocalDateTime reminderTime) {
        this.id = id;
        this.message = message;
        this.reminderTime = reminderTime;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void displayDetails() {
        System.out.println("Reminder ID: " + id);
        System.out.println("Message: " + message);
        System.out.println("Time: " + reminderTime);
    }
}

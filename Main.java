
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        NotesService service = new NotesService();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== PERSONAL NOTES & REMINDERS =====");
            System.out.println("1. Add Note");
            System.out.println("2. View All Notes");
            System.out.println("3. Search Note by ID");
            System.out.println("4. Update Note");
            System.out.println("5. Delete Note");
            System.out.println("6. Add Reminder");
            System.out.println("7. View All Reminders");
            System.out.println("8. Search Reminder by ID");
            System.out.println("9. Delete Reminder");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":
                    System.out.print("Enter note title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter note content: ");
                    String content = sc.nextLine();

                    Note note = service.addNote(title, content);
                    System.out.println("Note added successfully! ID: "
                            + note.getId());
                    break;

                case "2":
                    List<Note> notes = service.getAllNotes();

                    if (notes.isEmpty()) {
                        System.out.println("No notes found.");
                    } else {
                        for (Note n : notes) {
                            n.displayDetails();
                            System.out.println("----------------");
                        }
                    }
                    break;

                case "3":
                    System.out.print("Enter note ID: ");
                    int searchId = readId(sc);

                    if (searchId == -1) break;

                    Note found = service.getNoteById(searchId);

                    if (found != null) {
                        found.displayDetails();
                    } else {
                        System.out.println("Note not found.");
                    }
                    break;

                case "4":
                    System.out.print("Enter note ID to update: ");
                    int updateId = readId(sc);

                    if (updateId == -1) break;

                    System.out.print("Enter new title: ");
                    String newTitle = sc.nextLine();

                    System.out.print("Enter new content: ");
                    String newContent = sc.nextLine();

                    if (service.updateNote(
                            updateId, newTitle, newContent)) {
                        System.out.println("Note updated successfully.");
                    } else {
                        System.out.println("Note not found.");
                    }
                    break;

                case "5":
                    System.out.print("Enter note ID to delete: ");
                    int deleteId = readId(sc);

                    if (deleteId == -1) break;

                    if (service.deleteNote(deleteId)) {
                        System.out.println("Note deleted successfully.");
                    } else {
                        System.out.println("Note not found.");
                    }
                    break;

                case "6":
                    System.out.print("Enter reminder message: ");
                    String message = sc.nextLine();

                    try {
                        System.out.print(
                                "Enter date and time (yyyy-MM-dd HH:mm): ");
                        String dateTime = sc.nextLine();

                        LocalDateTime reminderTime =
                                LocalDateTime.parse(
                                        dateTime.replace(" ", "T"));

                        Reminder reminder = service.addReminder(
                                message, reminderTime);

                        System.out.println(
                                "Reminder added successfully! ID: "
                                        + reminder.getId());

                    } catch (Exception e) {
                        System.out.println(
                                "Invalid date/time. Example: 2026-11-05 14:30");
                    }
                    break;

                case "7":
                    List<Reminder> reminders = service.getAllReminders();

                    if (reminders.isEmpty()) {
                        System.out.println("No reminders found.");
                    } else {
                        for (Reminder r : reminders) {
                            r.displayDetails();
                            System.out.println("----------------");
                        }
                    }
                    break;

                case "8":
                    System.out.print("Enter reminder ID: ");
                    int reminderId = readId(sc);

                    if (reminderId == -1) break;

                    Reminder foundReminder =
                            service.getReminderById(reminderId);

                    if (foundReminder != null) {
                        foundReminder.displayDetails();
                    } else {
                        System.out.println("Reminder not found.");
                    }
                    break;

                case "9":
                    System.out.print("Enter reminder ID to delete: ");
                    int removeReminderId = readId(sc);

                    if (removeReminderId == -1) break;

                    if (service.deleteReminder(removeReminderId)) {
                        System.out.println("Reminder deleted successfully.");
                    } else {
                        System.out.println("Reminder not found.");
                    }
                    break;

                case "0":
                    running = false;
                    System.out.println("Thank you for using the application!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    private static int readId(Scanner sc) {
        try {
            int id = Integer.parseInt(sc.nextLine());

            if (id <= 0) {
                System.out.println("ID must be a positive number.");
                return -1;
            }

            return id;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid numeric ID.");
            return -1;
        }
    }
}


import java.util.ArrayList;
import java.util.List;

public class NotesService {

    // Store notes and reminders
    private final List<Note> notes = new ArrayList<>();
    private final List<Reminder> reminders = new ArrayList<>();

    private int nextId = 1;
    private int nextReminderId = 1;

    // ==============================
    // NOTE MANAGEMENT
    // ==============================

    // Add a new note
    public Note addNote(String title, String content) {
        Note note = new Note(nextId++, title, content);
        notes.add(note);
        return note;
    }

    // View all notes
    public List<Note> getAllNotes() {
        return new ArrayList<>(notes);
    }

    // Search note by ID
    public Note getNoteById(int id) {
        for (Note note : notes) {
            if (note.getId() == id) {
                return note;
            }
        }
        return null;
    }

    // Search notes by title
    public List<Note> searchNotesByTitle(String title) {
        List<Note> result = new ArrayList<>();

        for (Note note : notes) {
            if (note.getTitle().toLowerCase()
                    .contains(title.toLowerCase())) {
                result.add(note);
            }
        }

        return result;
    }

    // Update an existing note
    public boolean updateNote(
            int id, String title, String content) {

        Note note = getNoteById(id);

        if (note == null) {
            return false;
        }

        note.setTitle(title);
        note.setContent(content);
        return true;
    }

    // Delete a note
    public boolean deleteNote(int id) {
        return notes.removeIf(note -> note.getId() == id);
    }

    // ==============================
    // REMINDER MANAGEMENT
    // ==============================

    // Add a new reminder
    public Reminder addReminder(
            String message,
            java.time.LocalDateTime reminderTime) {

        Reminder reminder = new Reminder(
                nextReminderId++, message, reminderTime);

        reminders.add(reminder);
        return reminder;
    }

    // View all reminders
    public List<Reminder> getAllReminders() {
        return new ArrayList<>(reminders);
    }

    // Search reminder by ID
    public Reminder getReminderById(int id) {
        for (Reminder reminder : reminders) {
            if (reminder.getId() == id) {
                return reminder;
            }
        }
        return null;
    }

    // Delete a reminder
    public boolean deleteReminder(int id) {
        return reminders.removeIf(
                reminder -> reminder.getId() == id);
    }
}

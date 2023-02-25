package controllers;

import model.Note;
import model.Repository;

import java.util.List;

public class Controller {
    private final Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    public void saveNote(Note note) throws Exception {
        validateNote(note);
        repository.CreateNote(note);
    }

    public Note readNote(String noteId) throws Exception {
        return repository.readNote(noteId);
    }

    public List<Note> readNoteList() {
        return repository.getAllNotes();
    }

    public Note updateNote(Note note) throws Exception {
        validateNote(note);
        return repository.updateNote(note);
    }

    public void deleteNote(Note note) throws Exception {
        repository.deleteNote(note);
    }

    private void validateNote(Note note) throws Exception {
        if (note.getTitle().isEmpty()) {
            throw new Exception("Отсутствует название");
        }
        if (note.getFieldString().isEmpty()) {
            throw new Exception("Отсутствует заметка");
        }
        if (note.getDataString().isEmpty()) {
            throw new Exception("Отсутствует дата");
        }
    }

}
package model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    protected Mapper mapper = new Mapper();
    private Operation fileOperation;

    public RepositoryFile(Operation fileOperation) {
        this.fileOperation = fileOperation;
    }

    public RepositoryFile(Operation fileOperation, Mapper mapper) {
        this.fileOperation = fileOperation;
        this.mapper = mapper;
    }

    @Override
    public List<Note> getAllNotes() {
        List<String> lines = fileOperation.readAllLines();
        List<Note> notes = new ArrayList<>();
        for (String line : lines) {
            notes.add(mapper.map(line));
        }
        return notes;
    }

    @Override
    public String CreateNote(Note note) {

        List<Note> notes = getAllNotes();
        int max = 0;
        for (Note item : notes) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        note.setId(id);
        notes.add(note);
        saveNotes(notes);
        return id;
    }

    private void saveNotes(List<Note> notes) {
        List<String> lines = new ArrayList<>();
        for (Note item : notes) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public Note updateNote(Note note) throws Exception {
        List<Note> notes = getAllNotes();
        Note foundNote = findNoteById(notes, note.getId());
        foundNote.setTitle(note.getTitle());
        foundNote.setFieldString(note.getFieldString());
        foundNote.setDataString(note.getDataString());
        saveNotes(notes);
        return foundNote;
    }

    @Override
    public Note readNote(String noteId) throws Exception {
        List<Note> notes = getAllNotes();
        return findNoteById(notes, noteId);
    }

    private Note findNoteById(List<Note> notes, String noteId) throws Exception {
        for (Note note : notes) {
            if (note.getId().equals(noteId)) {
                return note;
            }
        }
        throw new Exception("Заметка не найдена.");
    }

    @Override
    public void deleteNote(Note note) throws Exception {
        List<Note> notes = getAllNotes();
        Note findedNote = findNoteById(notes, note.getId());
        notes.remove(findedNote);
        saveNotes(notes);

    }

}
package model;

import controllers.Loggable;
import java.util.List;

public class LoggerRepositor implements Repository {
    private Repository repository;
    private Loggable logger;
    protected Mapper mapper = new Mapper();
    private Operation fileOperation;

    public LoggerRepositor(Repository repository, Loggable logger, Operation fileOperation) {
        this.repository = repository;
        this.logger = logger;
        this.fileOperation = fileOperation;

    }

    @Override
    public List<Note> getAllNotes() {
        logger.log("- Выводим список записок... ");
        List<Note> notes = repository.getAllNotes();
        return notes;
    }

    @Override
    public String CreateNote(Note note) {
        String result;
        logger.log("- Создаем новую записку... ");
        result = repository.CreateNote(note);
        return result;
    }

    @Override
    public Note updateNote(Note note) throws Exception {
        logger.log("- Обновляем записку... ");
        Note noteUpdate = repository.updateNote(note);
        return noteUpdate;
    }

    @Override
    public Note readNote(String noteId) throws Exception {
        logger.log("- Ищем записку по Id и выводим... ");
        Note noteRead = repository.readNote(noteId);
        return noteRead;
    }

    @Override
    public void deleteNote(Note note) throws Exception {
        logger.log("- Ищем записку по Id и удаляем... ");
        repository.deleteNote(note);
    }
}
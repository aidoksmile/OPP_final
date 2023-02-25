package views;

import controllers.Loggable;
import controllers.Controller;
import model.Note;

import java.util.List;
import java.util.Scanner;

public class View {

    private Controller controller;
    private Loggable logger;

    public View(Controller controller, Loggable logger) {
        this.controller = controller;
        this.logger = logger;
    }

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду из списка: READ, CREATE, UPDATE, LIST, DELETE, EXIT: \n", true);
            try {
                com = Commands.valueOf(command);
            } catch (IllegalArgumentException e) {
                System.out.println("Неопознанная команда");
                logger.log("- Введена неправильная команда");
                continue;
            }

            try {
                switch (com) {
                    case CREATE:
                        Note note = setNote(false);
                        logger.log("- Выбрано создание заметки. ");
                        controller.saveNote(note);
                        logger.log("- Ок. ");
                        break;
                    case READ:
                        String id = prompt("Номер заметки: ", false);
                        logger.log("- Выбрано чтение заметки по Id: " + id);
                        Note readedNote = controller.readNote(id);
                        System.out.println(readedNote);
                        logger.log("- Прочитано. ");
                        break;
                    case LIST:
                        System.out.println("Все заметки: \n");
                        logger.log("- Выбрано отображение всех заметок. ");
                        List<Note> noteList = controller.readNoteList();
                        for (Note u : noteList) {
                            System.out.println(u + "\n");
                        }
                        break;
                    case UPDATE:
                        logger.log("- Выбрано обновление заметки. ");
                        Note updateNote = setNote(true);
                        controller.updateNote(updateNote);
                        System.out.println("Заметка заменена. \n");
                        logger.log("- Заметка заменена. ");
                        break;
                    case DELETE:
                        String noteId = prompt("Номер заметки: ", false);
                        logger.log("- Выбрано удаление заметки по Id: " + noteId);
                        Note deleteNote = controller.readNote(noteId);
                        controller.deleteNote(deleteNote);
                        System.out.println("Заметка удалена. \n");
                        logger.log("- Заметка удалена. ");
                        break;
                    case EXIT:
                        return;
                    case NONE:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                logger.log("- Ошибка: " + e.getMessage());

            }
        }
    }

    private String prompt(String message, Boolean upCase) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        if (upCase) {
            return in.nextLine().toUpperCase();
        } else {
            return in.nextLine();
        }
    }

    private Note setNote(boolean forUpdate) {
        String idString = "";
        if (forUpdate) {
            idString = prompt("Id: ", false);

        }

        String title = prompt("Заголовок: ", false);
        String fieldString = prompt("Заметка: ", false);
        String dataString = prompt("Дата создания: ", false);
        if (forUpdate) {
            return new Note(idString, title, fieldString, dataString);
        }
        return new Note(title, fieldString, dataString);

    }
}

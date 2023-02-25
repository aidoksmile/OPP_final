import controllers.Controller;
import controllers.Logger;
import model.FileOperation;
import model.LoggerRepositor;
import model.Repository;
import model.RepositoryFile;

import views.View;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger("loggs.txt");
        FileOperation fileOperation = new FileOperation("notes.txt");

        Repository repository = new RepositoryFile(fileOperation);
        Repository repositoryLog = new LoggerRepositor(repository, logger, fileOperation);
        Controller controller = new Controller(repositoryLog);

        View view = new View(controller, logger);
        view.run();
    }
}
package controller;

public class Controller {

    public ProcessBuilderController processBuilder;
    public ProcessManagerController processManager;
    public DataBaseController dataBaseController;

    public Controller() {

        processBuilder = new ProcessBuilderController();
        processManager = new ProcessManagerController();
        dataBaseController = new DataBaseController();
    }

}

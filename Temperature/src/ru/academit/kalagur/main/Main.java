package ru.academit.kalagur.main;

import ru.academit.kalagur.controller.Controller;
import ru.academit.kalagur.model.Model;
import ru.academit.kalagur.view.View;

public class Main {
    public static void main(String args[]) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.initController();
    }
}

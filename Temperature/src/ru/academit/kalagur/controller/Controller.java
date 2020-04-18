package ru.academit.kalagur.controller;

import ru.academit.kalagur.model.Model;
import ru.academit.kalagur.view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void initController() {
        view.getButton().addActionListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String text = view.getInputField().getText();
                Integer number = Integer.parseInt(text);

                Object from = view.getFromScale().getSelectedItem();
                Object to = view.getToScale().getSelectedItem();

                Double result = model.convertValue(from, to, number);

                view.setOutput(new JLabel(result.toString()));
                view.getOutput().setVisible(true);
            } catch (Exception exception) {
                view.setOutput(new JLabel("Ошибка! Введите число!"));
                view.getOutput().setVisible(true);
            }
        }
    }
}



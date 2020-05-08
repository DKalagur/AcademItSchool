package ru.academit.kalagur.view;

import ru.academit.kalagur.model.Model;
import ru.academit.kalagur.model.TemperatureScale;

import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;
    private JLabel header;
    private JLabel offer;
    private JTextField inputField;
    private JLabel fromText;
    private JLabel toText;
    private JComboBox<TemperatureScale> fromScale;
    private JComboBox<TemperatureScale> toScale;
    private JButton button;
    private JLabel output;

    public View() {
        SwingUtilities.invokeLater(() -> {
            // создаем frame
            frame = new JFrame("Программа перевода температур");
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = screenSize.width;
            int height = screenSize.height;

            frame.setSize((int) (width * 0.42), (int) (height * 0.42));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setMinimumSize(new Dimension((int) (width * 0.42), (int) (height * 0.42)));

            Image img = Toolkit.getDefaultToolkit().getImage("icon.jpg");
            frame.setIconImage(img);

            GridBagLayout gbl = new GridBagLayout();
            frame.setLayout(gbl);

            // создаем и настраиваем header
            header = new JLabel("Программа перевода температуры в другую систему измерения");
            GridBagConstraints c1 = new GridBagConstraints();

            c1.gridx = 0;
            c1.gridy = 0;
            c1.gridwidth = 4;
            c1.gridheight = 1;
            c1.anchor = GridBagConstraints.NORTH;
            c1.insets = new Insets(0, 0, 30, 0);

            frame.add(header, c1);

            // создаем и настраиваем offer
            offer = new JLabel("Введите температуру: ");
            GridBagConstraints c2 = new GridBagConstraints();

            c2.gridx = 0;
            c2.gridy = 1;
            c2.gridwidth = 2;
            c2.gridheight = 1;
            c2.anchor = GridBagConstraints.EAST;

            frame.add(offer, c2);

            // создаем и настраиваем inputField
            inputField = new JTextField("0", 8);
            GridBagConstraints c3 = new GridBagConstraints();

            c3.gridx = 3;
            c3.gridy = 1;
            c3.gridwidth = 1;
            c3.gridheight = 1;

            frame.add(inputField, c3);

            // создаем и настраиваем fromText
            fromText = new JLabel("Из какой шкалы: ");
            GridBagConstraints c4 = new GridBagConstraints();

            c4.gridx = 0;
            c4.gridy = 2;
            c4.gridwidth = 2;
            c4.gridheight = 1;
            c4.anchor = GridBagConstraints.EAST;

            frame.add(fromText, c4);

            // создаем и настраиваем fromScale
            TemperatureScale[] scales = Model.getScales();

            fromScale = new JComboBox<>(scales);
            GridBagConstraints c5 = new GridBagConstraints();

            c5.gridx = 3;
            c5.gridy = 2;
            c5.gridwidth = 1;
            c5.gridheight = 1;

            frame.add(fromScale, c5);

            // создаем и настраиваем toText
            toText = new JLabel("В какую шкалу: ");
            GridBagConstraints c6 = new GridBagConstraints();

            c6.gridx = 0;
            c6.gridy = 3;
            c6.gridwidth = 2;
            c6.gridheight = 1;
            c6.anchor = GridBagConstraints.EAST;

            frame.add(toText, c6);

            // создаем и настраиваем toScale
            toScale = new JComboBox<>(scales);
            GridBagConstraints c7 = new GridBagConstraints();

            c7.gridx = 3;
            c7.gridy = 3;
            c7.gridwidth = 1;
            c7.gridheight = 1;

            frame.add(toScale, c7);

            // создаем и настраиваем button
            button = new JButton("Преобразовать");
            GridBagConstraints c8 = new GridBagConstraints();

            c8.gridx = 2;
            c8.gridy = 4;
            c8.gridwidth = 1;
            c8.gridheight = 1;
            c8.insets = new Insets(20, 0, 0, 0);

            frame.add(button, c8);

            // создаем и настраиваем output
            output = new JLabel();
            GridBagConstraints c9 = new GridBagConstraints();

            output.setVisible(false);
            c9.gridx = 2;
            c9.gridy = 5;
            c9.gridwidth = 1;
            c9.gridheight = 1;
            c9.insets = new Insets(20, 0, 0, 0);

            frame.add(output, c9);

            button.addActionListener(e -> {
                try {
                    String text = inputField.getText();
                    double number = Double.parseDouble(text);

                    TemperatureScale from = (TemperatureScale) fromScale.getSelectedItem();
                    TemperatureScale to = (TemperatureScale) toScale.getSelectedItem();
                    double result = Model.convertValue(from, to, number);

                    output.setText(String.format("%.2f", result));
                    output.setVisible(true);
                } catch (NumberFormatException exception) {
                    output.setText("Ошибка! Введите число!");
                    output.setVisible(true);
                }
            });
        });
    }
}
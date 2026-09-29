package pl.edu.pw.elka.prm2t.lab6.zad3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberInputApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Input");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 120);

        JButton button = new JButton("Wpisz:");
        JTextField textField = new JTextField(20);
        textField.setEditable(false);
        textField.setFocusable(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (true) {
                    String input = JOptionPane.showInputDialog(frame, "Podaj wartość:");

                    if (input == null) {
                        return; // User canceled
                    }

                    try {
                        Double.parseDouble(input);
                        textField.setText(input);
                        break;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Niepoprawna wartość. Podaj wartość numeryczną.");
                    }
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(button);
        panel.add(textField);
        frame.add(panel);
        frame.setVisible(true);
    }
}


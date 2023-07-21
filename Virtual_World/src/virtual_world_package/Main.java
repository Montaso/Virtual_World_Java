package virtual_world_package;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JTextField sizeXField = new JTextField(10);
        JTextField sizeYField = new JTextField(10);

        panel.add(new JLabel("Enter size X:"));
        panel.add(sizeXField);
        panel.add(new JLabel("Enter size Y:"));
        panel.add(sizeYField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Enter sizes", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {

                int sizeX = Integer.parseInt(sizeXField.getText());
                int sizeY = Integer.parseInt(sizeYField.getText());

                //System.out.println("sizeX: "+sizeX+"\nsizeY: "+sizeY);
                World world = new World(sizeX, sizeY);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
            }
        }
    }
}
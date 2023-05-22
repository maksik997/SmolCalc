package UIComp;

import Logic.Logic;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Formatter;

public class DisplayPanel extends JPanel {

    private JTextField display;

    private Logic logic;

    public DisplayPanel(Logic logic) {
        this.logic = logic;


        display = new JTextField();

        display.setEnabled(false);
        display.setBackground(Color.DARK_GRAY);
        display.setForeground(Color.WHITE);
        display.setFont(new Font("Monospaced", Font.PLAIN, 26));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setBorder(new CompoundBorder(display.getBorder(), new EmptyBorder(15, 2, 15,2)));

        display.setText("");

        this.setLayout(new GridLayout());

        this.logic.setDisplay(display);
        this.add(display);
    }
}

import Logic.Logic;
import UIComp.ButtonPanel;
import UIComp.DisplayPanel;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    public View(){

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        JPanel main = new JPanel();

        Logic logic = new Logic();

        main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
        main.add(new DisplayPanel(logic));
        main.add(Box.createRigidArea(new Dimension(0, 5)));
        main.add(new ButtonPanel(5, 4, logic));

        this.setTitle("SmolCalc");
        this.add(main);
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

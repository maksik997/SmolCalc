package UIComp;

import Logic.Logic;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonPanel extends JPanel {

    private List<JButton> buttons;

    private int dim;

    private Logic logic;

    private static final String[][] buttonSequence = new String[][]{
        {"+/-", "CE", "C", "‹-"},
        {"7", "8", "9", "x"},
        {"4", "5", "6", "-"},
        {"1", "2", "3", "+"},
        {".", "0", "÷", "="},
    };

    public ButtonPanel(int rows, int cols, Logic logic) {
        this.logic = logic;

        buttons = new ArrayList<>();

        this.logic.setButtons(buttons);

        dim = rows*cols;

        this.setLayout(new GridLayout(rows, cols));

        init();
    }

    public void init(){

        for (String[] sArr : buttonSequence){
            for (String s : sArr){
                createButton(s);
            }
        }

        ActionListener al = null;

        int cols = 0, rows = 0;

        for (JButton button : buttons){
            if(cols % 4 == 0 && cols != 0)
                rows++;

            String s = buttonSequence[rows%5][cols++%4];

            switch (s){
                // Display operations
                case "C" -> al = e -> {
                    logic.setBuffer(new ArrayList<>());
                    logic.getDisplay().setText("");
                };
                case "+/-" -> al = e -> {
                    if(logic.getDisplay().getText().matches("\\-\\w+"))
                        logic.getDisplay().setText(logic.getDisplay().getText().replace("-",""));
                    else
                        logic.getDisplay().setText("-"+logic.getDisplay().getText());
                };
                case "‹-" -> al = e -> logic.getDisplay().setText(
                    logic.getDisplay().getText().substring(
                            0,
                            logic.getDisplay().getText().length()-1)
                );
                case "." -> al = e -> {
                    if(!logic.getDisplay().getText().matches("-?\\d+\\.\\d*")){
                        if (logic.getDisplay().getText().equals("0"))
                            logic.getDisplay().setText("0" + s);
                        else
                            logic.getDisplay().setText(logic.getDisplay().getText() + s);
                    }
                };
                case "CE" -> al = e -> logic.getDisplay().setText("");
                // Math operations
                case "=" -> al = e -> {
                    System.out.println("WIP");

                    if (!logic.getDisplay().getText().equals("")) {
                        logic.addToBuffer(logic.getDisplay().getText());

                        double res = logic.calcBuffer();

                        logic.getDisplay().setText(String.valueOf(res));
                    }
                };
                // Building numbers + Signs operations
                default -> al = e -> {
                    if(s.matches("[+\\-÷x]")){
                        if (logic.getBuffer().size() == 0)
                            logic.addToBuffer(logic.getDisplay().getText());
                        else if(logic.getDisplay().getText().equals(""))
                            logic.addToBuffer("0");

                        double res = logic.calcBuffer();

                            logic.addToBuffer(String.valueOf(res));
                            logic.addToBuffer(s);
                            logic.getDisplay().setText("");
                    }else{
                        if (logic.getDisplay().getText().equals("0"))
                            logic.getDisplay().setText(s);
                        else
                            logic.getDisplay().setText(logic.getDisplay().getText() + s);
                    }
                };
            }


            attachListener(button, al);
        }
    }

    public void createButton(String s){
        JButton button = new JButton(s);

        button.setFont(new Font("Dialog", Font.PLAIN, 20));
        button.setBorder(new CompoundBorder(button.getBorder(), new EmptyBorder(10, 0, 10, 0)));

        buttons.add(button);
        this.add(button);
    }
    public void createButton(Icon ico){
        JButton button = new JButton(ico);

        buttons.add(button);
        this.add(button);
    }
    public void attachListener(JButton button, ActionListener al){
        buttons.get(buttons.indexOf(button)).addActionListener(al);
    }
}

package Logic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logic {

    private JTextField display;

    private List<String> buffer;

    private static int idx = 0;

    private java.util.List<JButton> buttons;

    public Logic() {
        buffer = new ArrayList<>();
    }

    public void setDisplay(JTextField display) {
        this.display = display;
    }

    public JTextField getDisplay() {
        return display;
    }

    public void setButtons(List<JButton> buttons) {
        this.buttons = buttons;
    }

    public List<JButton> getButtons() {
        return buttons;
    }

    public List<String> getBuffer() {
        return buffer;
    }

    public void setBuffer(List<String> buffer) {
        this.buffer = buffer;
    }

    public void addToBuffer(String s){
        buffer.add(s);
    }

    public void reassembleBuffer(){
        StringBuilder sb = new StringBuilder();
        buffer.forEach(
                sb::append
        );

        buffer = Arrays.stream(sb.toString().split(" ")).toList();
    }

    public double calcBuffer(){
        System.out.println(buffer.size());
        System.out.println(buffer);
        if (buffer.size() == 3){
            double res = 0;
            java.util.List<Double> numbers = new ArrayList<>();
            java.util.List<String> signs = new ArrayList<>();

            buffer.forEach(
                v->{
                    if (v.matches("-?\\d+|-?\\d+\\.\\d+"))
                        numbers.add(Double.valueOf(v));
                    else
                        signs.add(v);
                }
            );

            int idx = 0;
            for (int i = 0; i < numbers.size(); i++) {
                double a = numbers.get(i++), b = numbers.get(i);
                res += switch (signs.get(idx++)){
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "x" -> a * b;
                    default -> a / b;
                };
            }

            buffer = new ArrayList<>();
            return res;
        }else if(buffer.size() == 1) {
            String s = buffer.get(0);
            buffer = new ArrayList<>();
            return s.equals("") ? 0 : Double.parseDouble(s);
        }

        return 0;
    }
}

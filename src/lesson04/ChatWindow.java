package lesson04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatWindow extends JFrame {

    private final JTextArea upTextField= new JTextArea(19,23);
    private final JTextField inputMessage= new JTextField(13);

    public ChatWindow() {
        setTitle("Сетевой чат");
        setIconImage(new ImageIcon("src/lesson04/icon/netChat.png").getImage());

        GraphicsDevice monitors = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        setBounds(monitors.getDisplayMode().getWidth() / 2 - 134 ,monitors.getDisplayMode().getHeight() / 2 - 190,268,380);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        upTextField.setLineWrap(true);
        upTextField.setWrapStyleWord(true);
        upTextField.setEditable(false);

        JPanel panel = new JPanel();
        panel.add(new JScrollPane(upTextField));
        add(panel, BorderLayout.CENTER);

        JButton button = new JButton("Отправить");
        button.addActionListener(this::PrintText);

        inputMessage.addKeyListener(new PressEnterKey());
        JPanel panelDown = new JPanel();
        panelDown.add(inputMessage);
        panelDown.add(button);
        add(panelDown, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void PrintText(ActionEvent event) {
        RemoveInputTextToTextArea();
    }

    class PressEnterKey extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent event){
            if(event.getKeyCode() == KeyEvent.VK_ENTER)
                RemoveInputTextToTextArea();
        }
    }

    private void RemoveInputTextToTextArea() {
        upTextField.append(inputMessage.getText() + "\n");
        inputMessage.setText("");
        inputMessage.requestFocus();
    }
}

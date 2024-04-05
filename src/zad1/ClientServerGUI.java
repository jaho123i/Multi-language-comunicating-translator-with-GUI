package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientServerGUI extends JFrame {
    private JTextField wordField;
    private JTextField languageField;
    private JTextArea translationArea;

    public ClientServerGUI() {
        setTitle("Dictionary Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

        mainPanel.add(new JLabel("Word to translate:"));
        wordField = new JTextField();
        mainPanel.add(wordField);

        mainPanel.add(new JLabel("Target language code:"));
        languageField = new JTextField();
        mainPanel.add(languageField);

        JButton translateButton = new JButton("Translate");
        translateButton.addActionListener(new TranslateButtonListener());
        mainPanel.add(translateButton);

        mainPanel.add(new JLabel("Translation:"));
        translationArea = new JTextArea();
        translationArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(translationArea);
        mainPanel.add(scrollPane);

        add(mainPanel);
        setVisible(true);
    }

    private class TranslateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String word = wordField.getText();
            String languageCode = languageField.getText();

            try (Socket socket = new Socket("localhost", 8889);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                out.writeObject(word);
                out.writeObject(languageCode);

                String translation = (String) in.readObject();
                translationArea.setText(translation);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientServerGUI::new);
    }
}


import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.util.TextRange;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import org.intellij.lang.annotations.JdkConstants;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class searchQuery extends AnAction {

    String[] links = {"https://www.google.ru/search?q=",
            "http://yandex.ru/yandsearch?lr=10&text= ",
            "https://search.yahoo.com/search?p=",
            "https://www.bing.com/search?q",
    };

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {


        Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        String selectedString = caretModel.getCurrentCaret().getSelectedText();

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        JTextField text = new JTextField(selectedString,20);

        JLabel label = new JLabel("Сформируйте запрос", SwingConstants.CENTER);
        textPanel.add(label);
        textPanel.add(text);

        String [] listEnties = {"Google", "Yandex", "Yahoo", "Bing"};

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.darkGray);

        JBList list = new JBList(listEnties);
        list.setVisibleRowCount(4);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPanel.add(list);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.darkGray);

        Button okButton = new Button("Окей");
        Button exitButton = new Button("Выход");

        Component horizontalStrut = Box.createHorizontalStrut(15);

        buttonPanel.add(okButton);
        buttonPanel.add(horizontalStrut);
        buttonPanel.add(exitButton);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() != -1) {

                     BrowserUtil.browse(links[list.getSelectedIndex()] + text.getText());
                    frame.setVisible(false);
                    frame.dispose();

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Вы не сделали выбор!");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.getContentPane().add(textPanel);
        frame.getContentPane().add(listPanel);
        frame.getContentPane().add(buttonPanel);
        frame.setSize(300,320);
        frame.setVisible(true);

        }

    }


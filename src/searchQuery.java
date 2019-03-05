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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class searchQuery extends AnAction {

   private String[] links = {
           "https://www.google.ru/search?q=",
            "http://yandex.ru/yandsearch?lr=10&text= ",
            "https://search.yahoo.com/search?p=",
            "https://www.bing.com/search?q=",
    };

   private String [] listEnties = {"Google", "Yandex", "Yahoo", "Bing"};

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {


        Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        String selectedString = caretModel.getCurrentCaret().getSelectedText();

        JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel textPanel = new JPanel();
        textPanel.setBackground(Color.darkGray);
        JTextField text = new JTextField(selectedString,20);
        textPanel.add(text, SwingConstants.CENTER);

        JPanel labelPanel = new JPanel();
        JLabel label = new JLabel("Сформируйте запрос", SwingConstants.CENTER);
        labelPanel.add(label, SwingConstants.CENTER);

        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.darkGray);
        JBList list = new JBList(listEnties);
        list.setVisibleRowCount(4);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPanel.add(list, SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.darkGray);

        Button okButton = new Button("Окей");
        Button exitButton = new Button("Выход");

        Component horizontalStrut = Box.createHorizontalStrut(25);

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


        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(boxlayout);
        panel.setBorder(new EmptyBorder(new Insets(40, 40, 40, 40)));

        panel.add(labelPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel.add(textPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel.add(listPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel.add(buttonPanel);


        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        }

    }


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

        String request = JOptionPane.showInputDialog(
                "Сформируйте запрос",
                selectedString);

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
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBackground(Color.darkGray);

        Button okButton = new Button("Окей");
        Button exitButton = new Button("Выход");

        buttonPanel.add(BorderLayout.WEST, okButton);
        buttonPanel.add(BorderLayout.EAST, exitButton);

        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.getContentPane().add(BorderLayout.CENTER, listPanel);
        frame.setSize(150,200);
        frame.setVisible(true);

        


       /* int x = JOptionPane.showOptionDialog(null, "Выберете поисковик",
                "Поисковик",
                JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);


        if (check.isSelected() && x != -1) {
            BrowserUtil.browse(links[x] + result);
            */
        }

    }


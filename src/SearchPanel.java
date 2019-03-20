import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import java.awt.*;

public class SearchPanel implements ApplicationComponent {

    public void initComponent() {

        ProjectManager.getInstance().addProjectManagerListener(new ProjectManagerListener() {
            public void projectOpened(final Project project) {
               final ToolWindowManager twm = ToolWindowManager.getInstance(project);
               ToolWindow toolWindow = twm.registerToolWindow("Search Notes", true, ToolWindowAnchor.RIGHT);
            }

        });
    }
}


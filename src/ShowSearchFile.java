import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class ShowSearchFile extends AnAction {

    private static String curFileName = ".searchFile.txt";
    private Project project;

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {

        project = anActionEvent.getProject();
        String filePath = getFilePath();
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                String errorMessage = "Error";
                Messages.showMessageDialog(project, errorMessage, "Error", Messages.getErrorIcon());
            }
        }

        VirtualFile fileVirtual = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(file);
        FileEditorManager.getInstance(project).openFile(fileVirtual, false);

    }


    public String getFilePath() {
        return this.project.getBasePath() + File.separator + Project.DIRECTORY_STORE_FOLDER + File.separator + ShowSearchFile.curFileName;
    }
}

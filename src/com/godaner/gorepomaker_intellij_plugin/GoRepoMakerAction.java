package com.godaner.gorepomaker_intellij_plugin;

import com.godaner.gorepomaker_intellij_plugin.repomaker.Entity;
import com.godaner.gorepomaker_intellij_plugin.repomaker.GoRepoMaker;
import com.godaner.gorepomaker_intellij_plugin.repomaker.maker.inf.RepoInterface;
import com.godaner.gorepomaker_intellij_plugin.repomaker.maker.mongo.RepoMongoImpl;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GoRepoMakerAction extends AnAction {

    private String selectedText;
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);

        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        if (null == editor) {
            return;
        }
        this.selectedText = editor.getSelectionModel().getSelectedText();
        if (this.selectedText==null||this.selectedText.length()<=0){
            Messages.showErrorDialog(project,"Make Repo Failed : Selected struct is empty !" , "Make Repo Failed");
            return;
        }
        List<Entity> entities= getEntities();
        if (entities.size()<=0){
            Messages.showErrorDialog(project,"Make Repo Failed : Selected struct is empty !" , "Make Repo Failed");
            return;
        }

        List<GoRepoMaker> goRepoMakers = registRepoMakers();

        String repoStr= batchMakeRepo(entities,goRepoMakers);

        int textLength = editor.getDocument().getTextLength();

        new WriteCommandAction(project) {
            @Override
            protected void run(@NotNull Result result) {
                editor.getDocument().insertString(textLength, repoStr);
                editor.getCaretModel().moveToOffset(textLength + 2);
                editor.getScrollingModel().scrollToCaret(ScrollType.CENTER_UP);
            }
        }.execute();


        String[] options={"谢谢大哥"};
        Messages.showDialog("生成Repo成功","成功",options,1,null);
    }

    private List<GoRepoMaker> registRepoMakers() {
        List<GoRepoMaker> goRepoMakers = new ArrayList();
        goRepoMakers.add(new RepoMongoImpl());
        goRepoMakers.add(new RepoInterface());
        return goRepoMakers;
    }

    private String batchMakeRepo(List<Entity> entities,List<GoRepoMaker> goRepoMakers) {
        String repoStr="";
        Collections.sort(goRepoMakers);
        for(Entity entity : entities){
            for (GoRepoMaker goRepoMaker:goRepoMakers){
                repoStr+=goRepoMaker.makeRepo(entity);
            }

        }
        return repoStr;
    }
    private List<Entity> getEntities(){
        List<Entity> entities = new ArrayList();
        String[] lines = this.selectedText.split(this.getSeparator(this.selectedText));
        for (int i = 0; i < lines.length; i++) {
            String str = lines[i];
            String structRegex =  "^\\s*type\\s+(\\w+)\\s+struct\\s*\\{\\s*";
            Pattern structPattern = Pattern.compile(structRegex);
            Matcher structMatcher = structPattern.matcher(str);
            while( structMatcher.find() )
            {
                String structName = structMatcher.group(1);
                Entity entity=new Entity(structName,null);
                entities.add(entity);
            }
        }
        return entities;
    }
    private String getSeparator(String text){
        return text.indexOf("\r\n")>0?"\r\n":"\n";
    }

}

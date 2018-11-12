package com.godaner.gorepomaker_intellij_plugin.repomaker;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class Util {
    public static String file2Str(String filePathName)
    {
        String fileContent = "";
        try {
            File f = new File(filePathName);
            if (f.isFile() && f.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(f), "UTF-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent += (line+"\n");
                }
                read.close();
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        return fileContent;
    }

    public static String makeTemplate2RepoStr(Entity entity, String templatePathName) {

        String text=Util.file2Str(templatePathName);
        try {
            text=text.replace(placeholder(Const.ENTITY_MONGO_REPO_NAME),entity.getMongoRepoName());
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            text=text.replace(placeholder(Const.ENTITY_NAME),entity.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            text=text.replace(placeholder(Const.ENTITY_MONGO_COLLECTION_NAME),entity.getMongoCollectionName());
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            text=text.replace(placeholder(Const.ENTITY_REPO_INTERFACE_NAME),entity.getRepoInterfaceName());
        } catch (Exception e){
            e.printStackTrace();
        }

        return text;
    }
    private static String placeholder(String source){
        return "${"+source+"}";
    }
}

  
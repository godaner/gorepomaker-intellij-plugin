package com.godaner.gorepomaker_intellij_plugin.repomaker;


import java.io.*;


public class Util {
    public static String inputStream2Str(InputStream inputStream)
    {
        String fileContent = "";
        try {
            InputStreamReader read = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent += (line+"\n");
            }
            read.close();
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        return fileContent;
    }

    public static String makeTemplate2RepoStr(Entity entity, InputStream inputStream) {

        String text=Util.inputStream2Str(inputStream);
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

  
package com.godaner.gorepomaker_intellij_plugin.util;


import com.godaner.gorepomaker_intellij_plugin.repomaker.Const;
import com.godaner.gorepomaker_intellij_plugin.repomaker.Entity;
import com.godaner.gorepomaker_intellij_plugin.repomaker.GoRepoMaker;
import com.godaner.gorepomaker_intellij_plugin.repomaker.maker.mongo.RepoMongoImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Util {
    public static List<GoRepoMaker> loadGoMakers()
    {
        List<GoRepoMaker> goRepoMakers = new ArrayList();
        InputStream inputStream=RepoMongoImpl.class.getClassLoader().getResourceAsStream("maker_classes.txt");
        try {
            InputStreamReader read = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                GoRepoMaker goRepoMaker= (GoRepoMaker) Class.forName(line).newInstance();
                goRepoMakers.add(goRepoMaker);
            }
            read.close();
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        return goRepoMakers;
    }
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

  
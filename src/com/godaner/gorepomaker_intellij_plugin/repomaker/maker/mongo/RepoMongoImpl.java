package com.godaner.gorepomaker_intellij_plugin.repomaker.maker.mongo;


import com.godaner.gorepomaker_intellij_plugin.repomaker.AbstractGoRepoMaker;
import com.godaner.gorepomaker_intellij_plugin.repomaker.Entity;
import com.godaner.gorepomaker_intellij_plugin.repomaker.Util;

public class RepoMongoImpl extends AbstractGoRepoMaker {
    @Override
    public String makeRepo(Entity entity) {
        String filePath = this.getClass().getResource("/").getFile();
        String filePathName=filePath+ "repotemplates/impl_mgo_repo.txt";
        return Util.makeTemplate2RepoStr(entity,filePathName);
    }

    @Override
    public int sortNum() {
        return 0;
    }

}
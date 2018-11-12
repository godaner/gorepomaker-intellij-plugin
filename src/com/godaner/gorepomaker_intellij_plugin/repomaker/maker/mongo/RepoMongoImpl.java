package com.godaner.gorepomaker_intellij_plugin.repomaker.maker.mongo;


import com.godaner.gorepomaker_intellij_plugin.repomaker.AbstractGoRepoMaker;
import com.godaner.gorepomaker_intellij_plugin.repomaker.Entity;
import com.godaner.gorepomaker_intellij_plugin.repomaker.Util;
import com.godaner.gorepomaker_intellij_plugin.repomaker.maker.inf.RepoInterface;

public class RepoMongoImpl extends AbstractGoRepoMaker {
    @Override
    public String makeRepo(Entity entity) {
        String filePathName = RepoInterface.class.getClassLoader().getResource("repotemplates/impl_mgo_repo.txt").getPath();
        return Util.makeTemplate2RepoStr(entity,filePathName);
    }

    @Override
    public int sortNum() {
        return 0;
    }

}
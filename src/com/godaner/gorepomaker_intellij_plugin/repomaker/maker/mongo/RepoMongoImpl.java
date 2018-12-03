package com.godaner.gorepomaker_intellij_plugin.repomaker.maker.mongo;


import com.godaner.gorepomaker_intellij_plugin.repomaker.AbstractGoRepoMaker;
import com.godaner.gorepomaker_intellij_plugin.repomaker.Entity;
import com.godaner.gorepomaker_intellij_plugin.util.Util;

public class RepoMongoImpl extends AbstractGoRepoMaker {
    @Override
    public String makeRepo(Entity entity) {

        return Util.makeTemplate2RepoStr(entity,RepoMongoImpl.class.getClassLoader().getResourceAsStream("repotemplates/impl_mgo_repo.txt"));
    }

    @Override
    public int sortNum() {
        return 1;
    }

}


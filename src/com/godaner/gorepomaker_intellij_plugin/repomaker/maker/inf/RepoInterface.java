package com.godaner.gorepomaker_intellij_plugin.repomaker.maker.inf;

import com.godaner.gorepomaker_intellij_plugin.repomaker.AbstractGoRepoMaker;
import com.godaner.gorepomaker_intellij_plugin.repomaker.Entity;
import com.godaner.gorepomaker_intellij_plugin.util.Util;
import com.godaner.gorepomaker_intellij_plugin.repomaker.maker.mongo.RepoMongoImpl;

public class RepoInterface extends AbstractGoRepoMaker {
    @Override
    public String makeRepo(Entity entity) {
        return Util.makeTemplate2RepoStr(entity,RepoMongoImpl.class.getClassLoader().getResourceAsStream("repotemplates/inf_repo.txt"));
    }

    @Override
    public int sortNum() {
        return 0;
    }

}
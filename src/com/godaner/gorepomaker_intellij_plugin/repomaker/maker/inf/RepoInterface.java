package com.godaner.gorepomaker_intellij_plugin.repomaker.maker.inf;

import com.godaner.gorepomaker_intellij_plugin.repomaker.AbstractGoRepoMaker;
import com.godaner.gorepomaker_intellij_plugin.repomaker.Entity;
import com.godaner.gorepomaker_intellij_plugin.repomaker.Util;

public class RepoInterface extends AbstractGoRepoMaker {
    @Override
    public String makeRepo(Entity entity) {
        String filePathName = RepoInterface.class.getClassLoader().getResource("repotemplates/inf_repo.txt").getPath();
        return Util.makeTemplate2RepoStr(entity,filePathName);
    }

    @Override
    public int sortNum() {
        return 1;
    }

}
package com.godaner.gorepomaker_intellij_plugin.repomaker.maker.inf;

import com.godaner.gorepomaker_intellij_plugin.repomaker.AbstractGoRepoMaker;
import com.godaner.gorepomaker_intellij_plugin.repomaker.Entity;
import com.godaner.gorepomaker_intellij_plugin.repomaker.Util;

public class RepoInterface extends AbstractGoRepoMaker {
    @Override
    public String makeRepo(Entity entity) {
        String filePath = this.getClass().getResource("/").getFile();
        String filePathName=filePath+ "repotemplates/inf_repo.txt";
        return Util.makeTemplate2RepoStr(entity,filePathName);
    }

    @Override
    public int sortNum() {
        return 1;
    }

}
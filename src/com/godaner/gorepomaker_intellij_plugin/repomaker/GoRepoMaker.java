package com.godaner.gorepomaker_intellij_plugin.repomaker;


public interface GoRepoMaker extends Comparable<GoRepoMaker> {
    String makeRepo(Entity entity);
    int sortNum();
}

package com.godaner.gorepomaker_intellij_plugin.repomaker;

import org.jetbrains.annotations.NotNull;

public class AbstractGoRepoMaker implements GoRepoMaker {
    @Override
    public String makeRepo(Entity entity) {
        return null;
    }

    @Override
    public int sortNum() {
        return 0;
    }

    @Override
    public int compareTo(@NotNull GoRepoMaker o) {
        return this.sortNum()>o.sortNum()?1:this.sortNum()==o.sortNum()?0:-1;
    }
}

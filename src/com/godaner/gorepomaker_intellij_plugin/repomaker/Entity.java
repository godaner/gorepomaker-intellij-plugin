package com.godaner.gorepomaker_intellij_plugin.repomaker;


public class Entity {
    String name;//Person
    String mongoCollectionName;//PersonCol
    String mongoRepoName;//PersonMongoRepo
    String repoInterfaceName;//PersonRepo
    Field[] fields;
    public Entity() {
    }

    public String getRepoInterfaceName() {
        return repoInterfaceName;
    }

    public String getName() {
        return name;
    }

    public String getMongoCollectionName() {
        return mongoCollectionName;
    }

    public String getMongoRepoName() {
        return mongoRepoName;
    }

    public Field[] getFields() {
        return fields;
    }

    public Entity(String name, Field[] fields) {
        this.name = name;
        this.fields = fields;
        this.mongoCollectionName = name+"Col";
        this.mongoRepoName = name+"MongoRepo";
        this.repoInterfaceName = name+"Repo";
    }
    //Field
    static class Field{
        String name;
        String[] tag;

        public Field(String name, String[] tag) {
            this.name = name;
            this.tag = tag;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String[] getTag() {
            return tag;
        }

        public void setTag(String[] tag) {
            this.tag = tag;
        }
    }
}
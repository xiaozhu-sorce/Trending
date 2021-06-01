package com.bignerdranch.android.trending.Model;

public class User {
    private String Username;
    private String Reponame;
    private int imgID;

    public User(int imgID, String username, String reponame){
        this.imgID = imgID;
        this.Username = username;
        this.Reponame = reponame;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getReponame() {
        return Reponame;
    }

    public void setReponame(String reponame) {
        Reponame = reponame;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}

package com.bignerdranch.android.trending.Model;

import java.util.List;

public class User {

    /**
     * rank : 1
     * username : alibaba
     * repositoryName : COLA
     * url : https://github.com/alibaba/COLA
     * description : 🥤COLA: Clean Object-oriented & Layered Architecture
     * language : Java
     * languageColor : #b07219
     * totalStars : 7557
     * forks : 2079
     * starsSince : 106
     * since : weekly
     * builtBy : [{"username":"oldratlee","url":"https://github.com/oldratlee","avatar":"https://avatars.githubusercontent.com/u/1063891?s=40&v=4"},{"username":"significantfrank","url":"https://github.com/significantfrank","avatar":"https://avatars.githubusercontent.com/u/8212932?s=40&v=4"},{"username":"sinopower","url":"https://github.com/sinopower","avatar":"https://avatars.githubusercontent.com/u/33787?s=40&v=4"},{"username":"apps/dependabot","url":"https://github.com/apps/dependabot","avatar":"https://avatars.githubusercontent.com/in/29110?s=40&v=4"},{"username":"xyz0001","url":"https://github.com/xyz0001","avatar":"https://avatars.githubusercontent.com/u/46206436?s=40&v=4"}]
     */

    private String username;
    private String repositoryName;
    private String language;
    private int totalStars;
    private int forks;
    private String description;
    private String url;
    /**
     * username : oldratlee
     * url : https://github.com/oldratlee
     * avatar : https://avatars.githubusercontent.com/u/1063891?s=40&v=4
     */

    private List<BuiltByBean> builtBy;

    public User(String username, String repositoryName, String language, int totalStars, int forks, String description, String url, List<BuiltByBean> builtBy) {
        this.username = username;
        this.repositoryName = repositoryName;
        this.language = language;
        this.totalStars = totalStars;
        this.forks = forks;
        this.description = description;
        this.url = url;
        this.builtBy = builtBy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTotalStars() {
        return totalStars;
    }

    public void setTotalStars(int totalStars) {
        this.totalStars = totalStars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public List<BuiltByBean> getBuiltBy() {
        return builtBy;
    }

    public void setBuiltBy(List<BuiltByBean> builtBy) {
        this.builtBy = builtBy;
    }

    public static class BuiltByBean {
        private String avatar;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}

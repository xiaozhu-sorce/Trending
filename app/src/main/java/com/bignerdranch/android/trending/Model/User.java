package com.bignerdranch.android.trending.Model;

import java.util.List;

public class User {

    /**
     * repo : CyberPunkMetalHead/Binance-volatility-trading-bot
     * repo_link : https://github.com/CyberPunkMetalHead/Binance-volatility-trading-bot
     * desc : This is a fully functioning Binance trading bot that measures the volatility of every coin on Binance and places trades with the highest gaining coins If you like this project consider donating though the Brave browser to allow me to continuously improve the script.
     * lang : Python
     * stars : 1,982
     * forks : 349
     * added_stars : 139 stars today
     * avatars : ["https://avatars.githubusercontent.com/u/45662650?s=40&v=4","https://avatars.githubusercontent.com/u/77636146?s=40&v=4","https://avatars.githubusercontent.com/u/13003926?s=40&v=4","https://avatars.githubusercontent.com/u/83913879?s=40&v=4","https://avatars.githubusercontent.com/u/3529834?s=40&v=4"]
     */

    private String repo;
    private String lang;
    private String stars;
    private String forks;
    private List<String> avatars;

    public User(String repo,String lang,String stars,String forks,List<String> avatars){
        this.repo = repo;
        this.lang = lang;
        this.stars = stars;
        this.forks = forks;
        this.avatars = avatars;
    }

    public User(String repo,String lang){
        this.repo = repo;
        this.lang = lang;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public List<String> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<String> avatars) {
        this.avatars = avatars;
    }
}

package com.example.christianbocanegra_comp228lab5;

import java.sql.Date;

public class Games {

    //Games information fields
    private String gameTitle;
    private Date playingDate;
    private int score;

    // Constructor to initialize a new Games object with all details
    public Games(String gameTitle, Date playingDate, int score) {
        this.gameTitle = gameTitle;
        this.playingDate = playingDate;
        this.score = score;
    }

    // Getters
    public String getGameTitle() { return gameTitle; }

    public Date getPlayingDate() { return playingDate; }

    public int getScore() { return score; }
}

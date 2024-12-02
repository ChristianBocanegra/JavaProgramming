package com.example.christianbocanegra_comp228lab5;

import java.sql.Date;

public class Games {

    // Fields to store game details
    private int playerId;
    private String gameTitle;
    private Date playingDate;
    private int score;

    // Constructor to initialize a new Games object with all details
    public Games(int playerId, String gameTitle, Date playingDate, int score) {
        this.playerId = playerId;
        this.gameTitle = gameTitle;
        this.playingDate = playingDate;
        this.score = score;
    }

    // Getters to retrieve values of the fields
    public int getPlayerId() { return playerId; }
    public String getGameTitle() { return gameTitle; }
    public Date getPlayingDate() { return playingDate; }
    public int getScore() { return score; }
}

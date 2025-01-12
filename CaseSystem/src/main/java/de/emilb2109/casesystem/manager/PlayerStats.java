package de.emilb2109.casesystem.manager;

import java.util.UUID;

public class PlayerStats {

    private final UUID uuid;

    private int vote;
    private int supreme;
    private int mega;

    public PlayerStats(UUID uuid) {
        this.uuid = uuid;
        this.vote = 0;
        this.supreme = 0;
        this.mega = 0;
    }

    public PlayerStats(UUID uuid, int vote, int supreme, int mega) {
        this.uuid = uuid;
        this.vote = vote;
        this.supreme = supreme;
        this.mega = mega;
    }

    public UUID getUuid() {
        return uuid;
    }


    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public PlayerStats addVote() {
        setVote(getVote() + 1);
        return this;
    }

    public int getSupreme() {
        return supreme;
    }

    public void setSupreme(int supreme) {
        this.supreme = supreme;
    }

    public int getMega() {
        return mega;
    }

    public void setMega(int mega) {
        this.mega = mega;
    }
}
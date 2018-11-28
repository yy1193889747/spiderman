package com.cy.spiderman.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author congyang.guo
 */
@Entity
public class XiaMiFirst implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String songname;
    private String playervia;
    private String firsttime;
    private int count;
    private String lasttime;

    public XiaMiFirst(String songname, String playervia, String firsttime, int count, String lasttime) {
        this.songname = songname;
        this.playervia = playervia;
        this.firsttime = firsttime;
        this.count = count;
        this.lasttime = lasttime;
    }

    public XiaMiFirst() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getPlayervia() {
        return playervia;
    }

    public void setPlayervia(String playervia) {
        this.playervia = playervia;
    }

    public String getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(String firsttime) {
        this.firsttime = firsttime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }
}

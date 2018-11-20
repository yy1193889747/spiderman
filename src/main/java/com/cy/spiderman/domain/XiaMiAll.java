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
public class XiaMiAll implements Serializable {
    private static final long serialVersionUID = 6540067837475384103L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String songname;
    private String playervia;
    private String tracktime;


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

    public String getTracktime() {
        return tracktime;
    }

    public void setTracktime(String tracktime) {
        this.tracktime = tracktime;
    }

    public XiaMiAll(String songname, String playervia, String tracktime) {
        this.songname = songname;
        this.playervia = playervia;
        this.tracktime = tracktime;
    }

    public XiaMiAll() {
    }
}

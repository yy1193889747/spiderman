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

    public XiaMiFirst(String songname, String playervia, String firsttime, int count) {
        this.songname = songname;
        this.playervia = playervia;
        this.firsttime = firsttime;
        this.count = count;
    }

    public XiaMiFirst() {
    }

}
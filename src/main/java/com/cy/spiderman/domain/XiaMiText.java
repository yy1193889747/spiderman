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
public class XiaMiText implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private String text;

    public XiaMiText(String data, String text) {
        this.data = data;
        this.text = text;
    }

    public XiaMiText() {
    }
}

package com.cy.spiderman.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author congyang.guo
 */
@Entity
public class QuanMing implements Serializable {
    private static final long serialVersionUID = -7693889559128217239L;
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

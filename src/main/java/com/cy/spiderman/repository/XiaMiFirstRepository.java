package com.cy.spiderman.repository;

import com.cy.spiderman.domain.XiaMiFirst;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author congyang.guo
 */
public interface XiaMiFirstRepository extends JpaRepository<XiaMiFirst, Long> {

    XiaMiFirst findBySongname(String songname);

    void deleteAllByFirsttimeNotContains(String firsttime);
}

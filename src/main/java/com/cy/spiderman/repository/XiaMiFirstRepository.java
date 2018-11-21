package com.cy.spiderman.repository;

import com.cy.spiderman.domain.XiaMiFirst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

/**
 * @author congyang.guo
 */
public interface XiaMiFirstRepository extends JpaRepository<XiaMiFirst, Long> {

    XiaMiFirst findBySongname(String songname);
    @Modifying
    @Transactional
    void deleteAllByFirsttimeEndingWith(String firsttime);
}

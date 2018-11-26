package com.cy.spiderman.repository;

import com.cy.spiderman.domain.XiaMiFirst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @author congyang.guo
 */
public interface XiaMiFirstRepository extends JpaRepository<XiaMiFirst, Long> {

    XiaMiFirst findBySongname(String songname);

    @Modifying
    @Transactional
    @Query("update XiaMiFirst u set u.count = ?1, u.lasttime = ?3  where u.songname = ?2")
    int updatecount(int count, String songname, String lasttime);

    @Modifying
    @Transactional
    void deleteAllByFirsttimeEndingWith(String firsttime);

    @Modifying
    @Transactional
    void deleteAllByLasttimeEndingWith(String lasttime);
}

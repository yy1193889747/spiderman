package com.cy.spiderman.repository;

import com.cy.spiderman.domain.XiaMiAll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author congyang.guo
 */

public interface XiaMiAllRepository extends JpaRepository<XiaMiAll, Long> {

    XiaMiAll findBySongnameAndPlayerviaAndTracktime(String songname, String playervia, String tracktime);

    XiaMiAll findFirstBySongnameOrderByIdAsc(String songname);

    List<XiaMiAll> findAllByTracktimeIsStartingWithOrderByIdAsc(String tracktime);


    List<XiaMiAll> findAllBySongname(String songname);

    void deleteAllByTracktimeNotContains(String tracktime);



}

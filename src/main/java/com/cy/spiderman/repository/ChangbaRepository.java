package com.cy.spiderman.repository;

import com.cy.spiderman.domain.ChangBa;
import com.cy.spiderman.domain.XiaMiAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author congyang.guo
 */

public interface ChangbaRepository extends JpaRepository<ChangBa, Long> {



}

package com.cy.spiderman.music;

import com.cy.spiderman.domain.XiaMiAll;
import com.cy.spiderman.repository.XiaMiAllRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author congyang.guo
 */
@Component
public class XiaMi {

    @Autowired
    private XiaMiAllRepository xiaMiAllRepository;

    public static void main(String[] args) throws IOException {
        new XiaMi().getAll();

    }

    public void getAll() throws IOException {
        System.out.println("开始");

    }
}

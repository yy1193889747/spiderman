package com.cy.spiderman;

import com.cy.spiderman.domain.XiaMiAll;
import com.cy.spiderman.domain.XiaMiFirst;
import com.cy.spiderman.repository.XiaMiAllRepository;
import com.cy.spiderman.repository.XiaMiFirstRepository;
import com.cy.spiderman.util.JavaMail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpidermanApplicationTests {

    @Autowired
    private XiaMiAllRepository xiaMiAllRepository;
    @Autowired
    private XiaMiFirstRepository xiaMiFirstRepository;


    @Test
    public void first() {
        List<XiaMiAll> all = xiaMiAllRepository.findAll();
        xiaMiFirstRepository.deleteAllByFirsttimeEndingWith("前");
        xiaMiFirstRepository.deleteAllByLasttimeEndingWith("前");
        for (XiaMiAll xiaMiAll : all) {
            String songname = xiaMiAll.getSongname();
            XiaMiFirst bySongname = xiaMiFirstRepository.findBySongname(songname);
            if (bySongname == null) {
                List<XiaMiAll> allBySongname = xiaMiAllRepository.findAllBySongname(songname);
                XiaMiAll first = xiaMiAllRepository.findFirstBySongnameOrderByIdAsc(songname);
                XiaMiAll last = xiaMiAllRepository.findFirstBySongnameOrderByIdDesc(songname);
                xiaMiFirstRepository.save(new XiaMiFirst(first.getSongname(), first.getPlayervia(),
                        first.getTracktime(), allBySongname.size(), last.getTracktime()));
            }
        }
        List<XiaMiFirst> all1 = xiaMiFirstRepository.findAll();
        for (XiaMiFirst xiaMiFirst : all1) {
            String songname = xiaMiFirst.getSongname();
            List<XiaMiAll> allBySongname = xiaMiAllRepository.findAllBySongname(songname);
            XiaMiAll last = xiaMiAllRepository.findFirstBySongnameOrderByIdDesc(songname);
            xiaMiFirstRepository.updatecount(allBySongname.size(), songname, last.getTracktime());
        }
    }

    @Test
    public void text() {
        List<XiaMiAll> all = xiaMiAllRepository.findAllByTracktimeIsStartingWithOrderByIdAsc("2018-11-23");

        for (XiaMiAll xiaMiAll : all) {
            System.out.println(xiaMiAll.getSongname() + "---" + xiaMiAll.getPlayervia() + "---" + xiaMiAll.getTracktime());
        }
    }

    @Test
    public void all() throws IOException {
        String url;
        xiaMiAllRepository.deleteAllByTracktimeEndingWith("前");
        for (int i = 5; i > 0; i--) {
            url = "";
            Document parse = Jsoup.connect(url).ignoreContentType(true).execute().parse();
            Elements select = parse.select("#column695 > div.c695_main.clearfix > div.c695_left > div > div" +
                    ".c695_l_content.profile_tracks >" + " div.pool5.blank10 > table > tbody > tr");
            for (int j = select.size() - 1; 0 <= j; j--) {
                String song = select.get(j).selectFirst("td.song_name").text();
                String player = select.get(j).selectFirst("td.player_via").text();
                String tracktime = select.get(j).selectFirst("td.track_time").text();
                System.out.println(song + "," + player + "," + tracktime);
                List<XiaMiAll> bySongnameAndPlayerviaAndTracktime =
                        xiaMiAllRepository.findAllBySongnameAndPlayerviaAndTracktime(song, player, tracktime);
                if (bySongnameAndPlayerviaAndTracktime.size() == 0 || tracktime.contains("前")) {
                    System.out.println("不存在");
                    xiaMiAllRepository.save(new XiaMiAll(song, player, tracktime));
                }
            }
        }
    }

    @Autowired
    private JavaMailSender mailSender;
    @Test
    public void send() throws IOException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("704739362@qq.com");
        message.setTo("704739362@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }
}

package com.cy.spiderman;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.spiderman.domain.XiaMiAll;
import com.cy.spiderman.domain.XiaMiFirst;
import com.cy.spiderman.repository.XiaMiAllRepository;
import com.cy.spiderman.repository.XiaMiFirstRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String first;

    @Test
    public void first() {
        List<XiaMiAll> all = xiaMiAllRepository.findAll();
        xiaMiFirstRepository.deleteAllByFirsttimeEndingWith("前");
        xiaMiFirstRepository.deleteAllByLasttimeEndingWith("前");
        for (XiaMiAll xiaMiAll : all) {
            String songname = xiaMiAll.getSongname();
            XiaMiFirst bySongname = xiaMiFirstRepository.findBySongname(songname);
            List<XiaMiAll> allBySongname = xiaMiAllRepository.findAllBySongname(songname);
            XiaMiAll first = xiaMiAllRepository.findFirstBySongnameOrderByIdAsc(songname);
            XiaMiAll last = xiaMiAllRepository.findFirstBySongnameOrderByIdDesc(songname);
            if (bySongname == null) {
                xiaMiFirstRepository.save(new XiaMiFirst(first.getSongname(), first.getPlayervia(),
                        first.getTracktime(), allBySongname.size(), last.getTracktime()));
            } else {
                xiaMiFirstRepository.updatecount(allBySongname.size(), songname, last.getTracktime());
            }
        }
    }

    @Test
    public void text() {
        List<XiaMiAll> all = xiaMiAllRepository.findAllByTracktimeIsStartingWithOrderByIdAsc("2018-11-2");

        for (XiaMiAll xiaMiAll : all) {
            System.out.println(xiaMiAll.getSongname() + "---" + xiaMiAll.getPlayervia() + "---" + xiaMiAll.getTracktime());
        }
    }

    @Test
    public void all() throws IOException {
        String url;
        xiaMiAllRepository.deleteAllByTracktimeEndingWith("前");
        for (int i = 2; i > 0; i--) {
            url = "";
            Document parse = Jsoup.connect(url).ignoreContentType(true).execute().parse();
            Elements select = parse.select("#column695 > div.c695_main.clearfix > div.c695_left > div > div" +
                    ".c695_l_content.profile_tracks >" + " div.pool5.blank10 > table > tbody > tr");

            for (int j = select.size() - 1; 0 <= j; j--) {
                String song = select.get(j).selectFirst("td.song_name").text();
                String player = select.get(j).selectFirst("td.player_via").text();
                String tracktime = select.get(j).selectFirst("td.track_time").text();
                System.out.println(song + "," + player + "," + tracktime);
                XiaMiAll bySongnameAndPlayerviaAndTracktime =
                        xiaMiAllRepository.findBySongnameAndPlayerviaAndTracktime(song, player, tracktime);
                if (null == bySongnameAndPlayerviaAndTracktime) {
                    System.out.println("不存在");
                    xiaMiAllRepository.save(new XiaMiAll(song, player, tracktime));
                }
            }
        }
    }

    @Test
    public void music() throws IOException, InterruptedException {
        String params = "";


        for (int j = 0; j < 10000; j++) {
            String body =
                    Jsoup.connect("https://music.163.com/weapi/user/playlist?csrf_token=").requestBody(params).ignoreContentType(true).method(Connection.Method.POST).execute().body();
            JSONArray parse = JSON.parseObject(body).getJSONArray("playlist");

            for (int i = 0; i < 1; i++) {


                JSONObject o = parse.getJSONObject(i);


                String trackCount = o.getString("trackCount");
                String playCount = o.getString("playCount");
                String nickname = o.getJSONObject("creator").getString("nickname");

                if (null == first) {
                    logger.info(trackCount + "---" + playCount + "---" + nickname);
                    first = o.getString("playCount");
                }
                if (!first.equals(o.getString("playCount"))) {
                    first = o.getString("playCount");
                    logger.info(trackCount + "---" + playCount + "---" + nickname);
                }
            }

            Thread.sleep(60000);

        }
    }

}

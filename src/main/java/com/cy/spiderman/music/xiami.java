package com.cy.spiderman.music;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author congyang.guo
 */
public class xiami {

    public static final String listMusicUrl =
            "";

    public static void main(String[] args) throws IOException {



        Document parse = Jsoup.connect(listMusicUrl).ignoreContentType(true).execute().parse();
        Elements select = parse.select("#column695 > div.c695_main.clearfix > div.c695_left > div > div" +
                ".c695_l_content.profile_tracks >" + " div.pool5.blank10 > table > tbody > tr");

        for (Element element : select) {
            String song = element.selectFirst("td.song_name").text();
            String player = element.selectFirst("td.player_via").text();
            String track_time = element.selectFirst("td.track_time").text();

            System.out.println(String.format("%-45s %-45s %45s", song, player, track_time));
        }
    }
}

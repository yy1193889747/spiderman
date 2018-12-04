package com.cy.spiderman.music;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author congyang.guo
 */
public class Music163 {


    public static final String listMusicUrl = "https://music.163.com/weapi/user/playlist?csrf_token=";
    public static final String listLikeMusicUrl = "https://music.163.com/weapi/v1/play/record?csrf_token=";

    private static final String params = "params=BaOw7mxg%2Fi3lub1PgHGHPegaMfO" +
            "%2FqElND2YkCtmktT0EBAMPlZHlc8aJAKeFmrBdHIfuqxZIc2fF21tHk63J9tRIe8cLHByfKD5eDWqER9" +
            "%2FuKtJTL7zhWx4Hbj6UNfprmzyP%2Fcmo9sYA6NT4HWNcPfermiA4O0gHQfcKfKNiUFcXbw8kU8Nw7WtCyIxVldj1&encSecKey" +
            "=369cd27561339fdfb8cc3c94f5d12acf4ec327a236284db57fa1444db0116921703e54f1a9b71b68b002d4ad9b700f12d81127e3278eb6509c12afdae0a7169de3f61f2c449c3bf6c1e9fd3998bf490a28db3a60f4ff99a1ecce39a85f25658e14d499c03cd29aa8a02962a404433bb27ce9241fe962c9a5d704eab55c47ec52";
    private static String first;
    private static String xiamifirst;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws IOException, InterruptedException {
        new Music163().whatch();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String body =
                Jsoup.connect(listMusicUrl).requestBody(params).ignoreContentType(true).method(Connection.Method.POST).execute().body();

        JSONArray parse = JSON.parseObject(body).getJSONArray("playlist");

        for (int i = 0; i < parse.size(); i++) {

            JSONObject o = parse.getJSONObject(i);
            String name = o.getString("name");
            String trackCount = o.getString("trackCount");
            String playCount = o.getString("playCount");
            String updateTime = o.getString("updateTime");
            String trackUpdateTime = o.getString("trackUpdateTime");
            String nickname = o.getJSONObject("creator").getString("nickname");
            String d = format.format(new Long(updateTime));
            String dd = format.format(new Long(trackUpdateTime));
            System.out.println(name + "---" + trackCount + "---" + playCount + "---" + dd + "---" + d + "---" + nickname);
        }


        String bodys =
                Jsoup.connect(listLikeMusicUrl).requestBody(params).ignoreContentType(true).method(Connection.Method.POST).execute().body();
        JSONArray parses = JSON.parseObject(bodys).getJSONArray("weekData");

        for (int i = 0; i < parses.size(); i++) {
            JSONObject o = parses.getJSONObject(i);
            String name = o.getJSONObject("song").getString("name");
            String score = o.getString("score");
            String playCount = o.getString("playCount");
            System.out.println(name + "---" + score + "---" + playCount);
        }
    }


    public void whatch() throws InterruptedException, IOException {

        Map<String, JSONObject> map = new HashMap<>(30);
        for (int j = 0; j < 10000; j++) {
            String body =
                    Jsoup.connect("https://music.163.com/weapi/user/playlist?csrf_token=").requestBody(params).ignoreContentType(true).method(Connection.Method.POST).execute().body();
            JSONArray parse = JSON.parseObject(body).getJSONArray("playlist");

            for (int i = 0; i < parse.size(); i++) {

                JSONObject o = parse.getJSONObject(i);
                String name = o.getString("name");
                String trackCount = o.getString("trackCount");
                String playCount = o.getString("playCount");
                String nickname = o.getJSONObject("creator").getString("nickname");
                if (!map.containsKey(name)) {
                    map.put(name, o);
                    logger.info("init(add)，{} ,{} ,{} ,{}", name, trackCount, playCount, nickname);
                }
                if (nickname.equals("")) {
                    if (!map.get(name).getString("playCount").equals(playCount) || !map.get(name).getString(
                            "trackCount").equals(trackCount)) {
                        logger.info("change，{} ,{} ,{}", name, trackCount, playCount);
                        map.put(name, o);
                    }
                }
            }

            String url = "";
            Document parses = Jsoup.connect(url).ignoreContentType(true).execute().parse();
            Elements select = parses.select("#column695 > div.c695_main.clearfix > div.c695_left > div > div" +
                    ".c695_l_content.profile_tracks >" + " div.pool5.blank10 > table > tbody > tr");
            for (int l = 0; l < 1; l++) {
                String song = select.get(l).selectFirst("td.song_name").text();
                String player = select.get(l).selectFirst("td.player_via").text();
                String tracktime = select.get(l).selectFirst("td.track_time").text();

                if (null == xiamifirst) {
                    logger.info(song + "---" + player + "---" + tracktime);
                    xiamifirst = song;
                }
                if (!xiamifirst.equals(song)) {
                    xiamifirst = song;
                    logger.info(song + "---" + player + "---" + tracktime);
                }
            }

            Thread.sleep(120000);

        }
    }
}


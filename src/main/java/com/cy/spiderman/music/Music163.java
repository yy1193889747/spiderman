package com.cy.spiderman.music;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author congyang.guo
 */
public class Music163 {


    public static final String listMusicUrl = "https://music.163.com/weapi/user/playlist?csrf_token=";
    public static final String listLikeMusicUrl = "https://music.163.com/weapi/v1/play/record?csrf_token=";


    public static void main(String[] args) throws IOException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


        String params = "";

        String body =
                Jsoup.connect(listMusicUrl).requestBody(params).ignoreContentType(true).method(Connection.Method
                .POST).execute().body();

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
            System.out.println(name + "---" + trackCount + "---" + playCount + "---" + dd + "---"+ d + "---" +
            nickname);
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
}

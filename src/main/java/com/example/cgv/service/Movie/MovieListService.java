package com.example.cgv.service.Movie;

import com.example.cgv.domain.dto.Movie.MovieListDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
public class MovieListService {
    private final RestTemplate restTemplate;


    public MovieListService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    String key="63c3d4c3e3724ea08877a2ef4d87e222";
    String stdate="20160101";
    String eddate="20160630";
    String cpage="1";
    String rows="5";
    String url="http://kopis.or.kr/openApi/restful/pblprfr?"+
            "service="+key+
            "&stdate="+stdate+
            "&eddate="+eddate+
            "&cpage="+cpage+
            "&rows="+rows;


    public String movieList(MovieListDto movieListDto) throws IOException,ParseException {
        StringBuffer result=new StringBuffer();
        URL url1= new URL(url);

        HttpURLConnection httpURLConnection=(HttpURLConnection) url1.openConnection();
        httpURLConnection.setRequestMethod("GET");
        BufferedReader br= new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String returnLine;
        while((returnLine=br.readLine())!=null){
            result.append(returnLine+"\n");
        }

        httpURLConnection.disconnect();
        String xmlresult=result+"";
        JSONObject jsObject= XML.toJSONObject(xmlresult);
        int num=2;
        JSONArray re=jsObject.getJSONObject("dbs").getJSONArray("db");
        // String result3=re.getJSONObject(num).get("mt20id").toString();
        String []resultarr= new String[re.length()];


        //mt20id를 뽑아오는 부분
        for(int i=0; i<re.length(); i++){
            resultarr[i]=re.getJSONObject(i).get("mt20id").toString();
        }
        String result3="";
        for(int i=0; i<re.length(); i++){
            result3+=resultarr[i]+",";
        }



        String u1=movieListDto.getMt20id();
        System.out.println(u1);



        return result3;
    }






}

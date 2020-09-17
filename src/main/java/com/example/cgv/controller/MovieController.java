package com.example.cgv.controller;

import com.example.cgv.domain.dto.Movie.MovieListDto;
import com.example.cgv.service.Movie.MovieListService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;


@RestController
@RequestMapping("/movie/categori")
public class MovieController {

    //opapi 인증키
    String key="63c3d4c3e3724ea08877a2ef4d87e222";
    private final MovieListService movieListService;

    public MovieController(MovieListService movieListService) {
        this.movieListService = movieListService;
    }

    //공연목록 리스트 json으로 값 가져오기
    @RequestMapping(value = "/movieList",method = {RequestMethod.GET})
    public String movieList() throws IOException, ParseException {
        StringBuffer result=new StringBuffer();
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
        System.out.println(jsObject);
        int num=2;
        JSONArray re=jsObject.getJSONObject("dbs").getJSONArray("db");
        String result3=re.getJSONObject(num).get("mt20id").toString();


        return result3;
    }


    //공연목록에서 공연ID를 뽑아내 공연 상세 출력
    @RequestMapping(value = "/movieImformation",method = {RequestMethod.GET})
    public String movieImformation() throws IOException, ParseException {
        String mt20id=movieList();
        StringBuffer result=new StringBuffer();
        String url="http://kopis.or.kr/openApi/restful/pblprfr/" +
                mt20id+"?"+
                "service="+key;
        URL url2=new URL(url);
        HttpURLConnection urlConnection=(HttpURLConnection) url2.openConnection();
        urlConnection.setRequestMethod("GET");

        //xml파일 잃기
        BufferedReader br= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String returnLine;
        while((returnLine=br.readLine())!=null){
            result.append(returnLine+"\n");
        }

        urlConnection.disconnect();
        String xmlresult=result+"";

        //xml파일을 json으로 변환
       JSONObject jsObject= XML.toJSONObject(xmlresult);
        String jsonresult=jsObject.toString();


        //dbs 안의 db안의 값을 뽑아오는 코드
        JSONObject re=jsObject.getJSONObject("dbs").getJSONObject("db");
        String result1=re.getString("sty");
        System.out.println(result1);



        return jsonresult;
    }


    //공연시설 리스트 json으로 값 가져오기
    @RequestMapping(value = "/movieLocationList",method = {RequestMethod.GET})
    public String movielocationList() throws IOException, ParseException {
        StringBuffer result=new StringBuffer();
        String cpage="1";
        String rows="5";
        String shprfnmfct="예술의전당";
        String url="http://kopis.or.kr/openApi/restful/prfplc?"+
                "service="+key+
                "&cpage="+cpage+
                "&rows="+rows+
                "&shprfnmfct="+shprfnmfct;

        URL url1= new URL(url);
        System.out.println(url);

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
        System.out.println(jsObject);
        String resultList=jsObject.toString();
        JSONArray re=jsObject.getJSONObject("dbs").getJSONArray("db");
        String result3=re.getJSONObject(1).get("mt10id").toString();

        return result3;
    }

    //공연시설목록에서 공연시설ID를 뽑아내 공연시설 상세 출력
    @RequestMapping(value = "/movieLocationImformation",method = {RequestMethod.GET})
    public String movieLocationImformation() throws IOException, ParseException {
        String mt10id=movielocationList();
        StringBuffer result=new StringBuffer();
        String url="http://kopis.or.kr/openApi/restful/prfplc/" +
                mt10id+"?"+
                "service="+key;
        URL url2=new URL(url);
        HttpURLConnection urlConnection=(HttpURLConnection) url2.openConnection();
        urlConnection.setRequestMethod("GET");

        //xml파일 잃기
        BufferedReader br= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String returnLine;
        while((returnLine=br.readLine())!=null){
            result.append(returnLine+"\n");
        }

        urlConnection.disconnect();
        String xmlresult=result+"";
        System.out.println(xmlresult);

        //xml파일을 json으로 변환
        org.json.JSONObject jsObject= XML.toJSONObject(xmlresult);
        String jsonresult=jsObject.toString();

        return jsonresult;
    }



    //예매 현황판(1~5위의 연극 제목 추출)
    @RequestMapping(value = "/movieLocationReservation",method = {RequestMethod.GET})
    public String movieLocationReservation() throws IOException, ParseException {

        StringBuffer result=new StringBuffer();
        String ststype="day";
        String date="20171218";
        String catecode="AAAA";
        String area="11";
        String url="http://kopis.or.kr/openApi/restful/boxoffice?"+
                   "service="+key+
                   "&ststype="+ststype+
                   "&date="+date+
                   "&catecode="+catecode+
                   "&area="+area;

        URL url2=new URL(url);
        HttpURLConnection urlConnection=(HttpURLConnection) url2.openConnection();
        urlConnection.setRequestMethod("GET");

        //xml파일 잃기
        BufferedReader br= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String returnLine;
        while((returnLine=br.readLine())!=null){
            result.append(returnLine+"\n");
        }

        urlConnection.disconnect();
        String xmlresult=result+"";
       // System.out.println(xmlresult);

        //xml파일을 json으로 변환
        org.json.JSONObject jsObject= XML.toJSONObject(xmlresult);
        String jsonresult=jsObject.toString();
        System.out.println(jsonresult);

        JSONArray re=jsObject.getJSONObject("boxofs").getJSONArray("boxof");
        //System.out.println(re);
        String []resultarr= new String[5];
        for(int i=0; i<5; i++) {
            String result3 = re.getJSONObject(i).get("prfnm").toString();
           resultarr[i]=re.getJSONObject(i).get("prfnm").toString();

        }
        System.out.println(Arrays.toString(resultarr));
        return jsonresult;
    }

    //movieListService를 이용해 데이터 얻기
   // @RequestMapping(value = "/movieList1",method = {RequestMethod.GET})
    @GetMapping("/movieList1")
    public String movieList1(@RequestBody MovieListDto movieListDto) throws IOException, ParseException {
        //System.out.println(movieListDto.getMt20id());
        return  movieListService.movieList(movieListDto);
    }

}

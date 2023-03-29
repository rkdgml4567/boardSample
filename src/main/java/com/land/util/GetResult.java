package com.land.util;

import com.land.vo.prices.PrBatAptMmVo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GetResult {
  public HashMap<String, Object> ParseXml(String url, String contractYmd, int pageNo, String areaCd) {
    int tCnt = 0;
    //String ntceInfo = "";
    HashMap<String, Object> map = new HashMap<>();
    try {
      BufferedReader rd;
      StringBuilder urlBuilder = new StringBuilder(url);
      String numOfRows = "10";
      urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + 
          "=YJch%2FTcqdanC6AZOscVqhGOZUMztRCKE7FMs9s9XF45N4UgLL9Lib3RXt%2Bf%2FQYOAJJT6GQfLHh4iDRZh5ihdjg%3D%3D");
      urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8"));
      urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
      urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(areaCd, "UTF-8"));
      urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(contractYmd, "UTF-8"));
      URL urlAll = new URL(urlBuilder.toString());
      HttpURLConnection conn = (HttpURLConnection)urlAll.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Content-type", "application/xml");
      if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      } else {
        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
      } 
      String sb = "";
      String line;
      while ((line = rd.readLine()) != null)
        sb = String.valueOf(sb) + line.trim(); 
      rd.close();
      conn.disconnect();
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      DocumentBuilder builder = null;
      builder = factory.newDocumentBuilder();
      System.out.println(sb.toString());
      Document doc = null;
      InputSource is = new InputSource(new StringReader(sb));
      doc = builder.parse(is);
      String resultCd = doc.getElementsByTagName("resultCode").item(0).getTextContent();
      String resultMsg = doc.getElementsByTagName("resultMsg").item(0).getTextContent();
      NodeList nodeList = doc.getElementsByTagName("item");
      if (resultCd.equals("00") && doc.getElementsByTagName("totalCount").item(0) != null) {
        tCnt = Integer.valueOf(doc.getElementsByTagName("totalCount").item(0).getTextContent()).intValue();
      } else {
        tCnt = 0;
      } 
      if (tCnt > 0) {
        List<PrBatAptMmVo> itemList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
          NodeList child = nodeList.item(i).getChildNodes();
          PrBatAptMmVo aptRst = new PrBatAptMmVo();
          for (int j = 0; j < child.getLength(); j++) {
            Node node = child.item(j);
            System.out.println("node.getNodeName()===" + node.getNodeName());
            String str = node.getNodeName();
            
            switch (str) {
              case "법정동본번코드":
                aptRst.setBjdBonbunCode(node.getTextContent().trim());
                break;
              case "법정동부번코드":
                aptRst.setBjdBubunCode(node.getTextContent().trim());
                break;
              case "도로지상지하코드":
                aptRst.setLoadUpCode(node.getTextContent().trim());
                break;
              case "법정동지번코드":
                aptRst.setBjdJibunCode(node.getTextContent().trim());
                break;
              case "도로명시군구코드":
                aptRst.setLaodSggCode(node.getTextContent().trim());
                break;
              case "법정동시군구코드":
                aptRst.setBjdSggCode(node.getTextContent().trim());
                break;
              case "년":
                aptRst.setYyyy(node.getTextContent().trim());
                break;
              case "월":
                aptRst.setMm(node.getTextContent().trim());
                break;
              case "일":
                aptRst.setDd(node.getTextContent().trim());
                break;
              case "층":
                aptRst.setFloor(node.getTextContent().trim());
                break;
              case "지번":
                aptRst.setJibun(node.getTextContent().trim());
                break;
              case "도로명":
                aptRst.setLoadName(node.getTextContent().trim());
                break;
              case "법정동":
                aptRst.setBjdName(node.getTextContent().trim());
                break;
              case "아파트":
                aptRst.setKaptName(node.getTextContent().trim());
                break;
              case "도로명일련번호코드":
                aptRst.setLoadSno(node.getTextContent().trim());
                break;
              case "도로명코드":
                aptRst.setLoadCode(node.getTextContent().trim());
                break;
              case "법정동읍면동코드":
                aptRst.setBjdUmdCode(node.getTextContent().trim());
                break;
              case "거래금액":
                aptRst.setTrAmount(node.getTextContent().trim().replaceAll(",", ""));
                break;
              case "건축년도":
                aptRst.setStYyyy(node.getTextContent().trim());
                break;
              case "도로명건물본번코드":
                aptRst.setLoadBonbunCode(node.getTextContent().trim());
                break;
              case "도로명건물부번코드":
                aptRst.setLoadBubunCode(node.getTextContent().trim());
                break;
              case "일련번호":
                aptRst.setSn(node.getTextContent().trim());
                break;
              case "전용면적":
                aptRst.setAreaUnit(Double.parseDouble(node.getTextContent().trim()));
                break;
              case "지역코드":
                aptRst.setAreaCode(node.getTextContent().trim());
                break;
            } 
          } 
          aptRst.setModUser("XML");
          itemList.add(i, aptRst);
        } 
        int totPage = (tCnt % 10 == 0) ? (int)Math.floor((tCnt / 10)) : ((int)Math.floor((tCnt / 10)) + 1);
        if (totPage != pageNo)
          pageNo++; 
        map.put("totCnt", Integer.valueOf(tCnt));
        map.put("pageNo", Integer.valueOf(pageNo));
        map.put("totPage", Integer.valueOf(totPage));
        map.put("numOfRows", numOfRows);
        map.put("resultCd", resultCd);
        map.put("resultMsg", resultMsg);
        map.put("itemList", itemList);
        map.put("wkCnt", Integer.valueOf(nodeList.getLength()));
      } else {
        map.put("totCnt", Integer.valueOf(0));
        map.put("wkCnt", Integer.valueOf(0));
        map.put("pageNo", Integer.valueOf(0));
        map.put("totPage", Integer.valueOf(0));
        map.put("numOfRows", Integer.valueOf(10));
        map.put("resultCd", "999");
        map.put("resultMsg", "END");
      } 
      System.out.println("map=" + map.toString());
    } catch (IOException|javax.xml.parsers.ParserConfigurationException|org.xml.sax.SAXException e) {
      e.printStackTrace();
    } 
    return map;
  }
}

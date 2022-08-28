package hello.itemservice.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
@Slf4j
public class PublicApiConnectService {

    public PublicData connectApi(Facility facility) throws IOException {

        StringBuilder urlBuilder = new StringBuilder("http://www.kspo.or.kr/openapi/service/sportsNewFacilInfoService/getNewFacilInfoList"); /*URL*/

        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=gLqQXaEl7S%2F1SKAHgfQg6i%2FhHE0PHZOxx66vvYlVs0EAXuisC6z57aBdJSdkX2BwZ03megcKkrzvP%2Bd5cx4lNg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(facility.getPageNo(), "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/

        if(!facility.getFaciNm().equals("all")) {
            urlBuilder.append("&" + URLEncoder.encode("faciNm","UTF-8") + "=" + URLEncoder.encode(facility.getFaciNm(), "UTF-8")); /*체육시설명*/
        }

        if(!facility.getFcobNm().equals("전체")) {
            urlBuilder.append("&" + URLEncoder.encode("fcobNm","UTF-8") + "=" + URLEncoder.encode(facility.getFcobNm(), "UTF-8")); /*운동분류*/

        }

        urlBuilder.append("&" + URLEncoder.encode("faciRoadAddr1","UTF-8") + "=" + URLEncoder.encode(facility.getFaciRoadAddr1(), "UTF-8")); /*도로명 주소*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("accept", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        ObjectMapper objectMapper = new ObjectMapper();
        PublicData publicData = objectMapper.readValue(sb.toString(), PublicData.class);


        return publicData;
    }
}

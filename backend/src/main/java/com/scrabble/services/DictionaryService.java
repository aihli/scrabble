package com.scrabble.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class DictionaryService {

    @Value("${rapid-api-key}")
    private String apiKey;

    public boolean verifyAllWords(Set<String> allWords) {
        for (String word : allWords) {
            StringBuilder builder = new StringBuilder(word);
            if (!verifyWord(builder.toString()) && !verifyWord(builder.reverse().toString())) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyWord(String str) {
        try {
            System.out.println(apiKey);
            String url = "https://wordsapiv1.p.rapidapi.com/words/" + str + "/definitions/?rapidapi-key=" + this.apiKey;
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            System.out.println(connection.getResponseCode());
            return connection.getResponseCode() == 200;
        } catch(Exception e) {
            return false;
        }
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://wordsapiv1.p.rapidapi.com/words/" + str +"/definitions/?rapidapi-key=c80c1f735bmsh7438297f7054c98p11c0bdjsn85ad969e7e49";
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.TEXT_PLAIN);
//        HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
//        restTemplate.getForObject(url, String.class, httpHeaders);
//        ResponseEntity<String> map = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        return map != null;
//        restTemplate.postForObject()
//
//        req.headers({ 	"x-rapidapi-host": "wordsapiv1.p.rapidapi.com", 	"x-rapidapi-key": "c80c1f735bmsh7438297f7054c98p11c0bdjsn85ad969e7e49", 	"useQueryString": true });
    }

}

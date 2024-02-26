package com.techelevator.dndapi.services;


import com.techelevator.dndapi.model.Monster;
import com.techelevator.dndapi.model.Root;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class MonsterService {

    public static String API_BASE_URL = "https://www.dnd5eapi.co/api/monsters";
    private RestTemplate restTemplate = new RestTemplate();

    public Root getListOfMonsters(){
        try {
            return restTemplate.getForObject(API_BASE_URL, Root.class);
        }  catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Monster getMonsterById(String url){
        try {
            return restTemplate.getForObject(API_BASE_URL + "/" + url, Monster.class);
        }  catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}

package com.techelevator.dndapi.services;

import com.techelevator.dndapi.model.CharacterClass;
import com.techelevator.dndapi.model.Root;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class ClassService {
    public static String API_BASE_URL = "https://www.dnd5eapi.co/api/classes";
    private RestTemplate restTemplate = new RestTemplate();

    public Root getListOfClasses(){
        try {
            return restTemplate.getForObject(API_BASE_URL, Root.class);
        }  catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public CharacterClass getCharacterClassById(String url){
       try {
           return restTemplate.getForObject(API_BASE_URL + "/" + url, CharacterClass.class);
       }  catch (RestClientResponseException e) {
           System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
       } catch (ResourceAccessException e) {
           System.out.println(e.getMessage());
       }
        return null;
    }
}

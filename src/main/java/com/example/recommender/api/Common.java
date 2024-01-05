package com.example.recommender.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Common {
    public static Map<String, Object> parseJsonStringForMap(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> myMap = objectMapper.readValue(json, new TypeReference<HashMap<String,Object>>() {});
        return myMap;
    }
    public static String getResourcePath(String resourceName) {
        URL resource = Common.class.getClassLoader().getResource(resourceName);
        if(resource != null) {
            String path = resource.getPath();
            if(path.startsWith("/"))
                path = path.substring(1);
            return path;
        }

        return "";
    }
}

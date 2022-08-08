package com.simba.missonGame.service;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

    public JSONObject strToJson(String str) throws ParseException {
        JSONParser jsonParser = new JSONParser();

        //3. To Object
        Object obj = jsonParser.parse(str);

        //4. To JsonObject
        JSONObject jsonObj = (JSONObject) obj;

        return jsonObj;
    }

}

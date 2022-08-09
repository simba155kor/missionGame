package com.simba.missonGame.service;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;

public interface JsonService {
    public JSONObject strToJson(String str) throws ParseException;
}

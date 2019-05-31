package com.gs.extractor.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonMapper {


    public static <T> T getJob(final String response, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}

package com.network.retrofit_rxjava_two.helper;

import com.common.log.KLog;
import com.common.utils.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GsonHelper {

    private static Gson sGson;
    private static JsonParser sJsonParser = new JsonParser();


    /**
     * 将json数据转化为实体数据
     *
     * @param jsonData    json字符串
     * @param entityClass 类型
     * @return 实体
     */
    public static <T> T convertEntity(String jsonData, Class<T> entityClass) {
        T entity = null;
        if (jsonData == null) {
            return null;
        }
        try {
            entity = getsGson().fromJson(jsonData, entityClass);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return entity;
    }
    public static <T> T convertEntity(String jsonData, Type type) {
        T entity = null;
        if (jsonData == null) {
            return null;
        }
        try {
            entity = getsGson().fromJson(jsonData, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 将json数据转化为实体列表数据
     *
     * @param jsonData    json字符串
     * @param entityClass 类型
     * @return 实体列表
     */
    public static <T> List<T> convertEntities(String jsonData, Class<T> entityClass) {
        List<T> entities = null;
        try {
            entities = new ArrayList<>();
            JsonArray jsonArray = sJsonParser.parse(jsonData).getAsJsonArray();
            for (JsonElement element : jsonArray) {
                entities.add(getsGson().fromJson(element, entityClass));
            }
            return entities;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将 Object 对象转为 String
     *
     * @param jsonObject json对象
     * @return json字符串
     */
    public static String object2JsonStr(Object jsonObject) {
        return sGson.toJson(jsonObject);
    }

    public static Gson getsGson() {

        if (sGson == null) {
            synchronized (GsonHelper.class) {
                sGson = new GsonBuilder()
                        .serializeNulls()
//                        .registerTypeAdapter(String.class,new NumType1Adapter())
//                        .registerTypeAdapter(String.class,new NumTypeAdapter<String>())
//                        .registerTypeAdapterFactory(new NumTypeAdapterFactory())
//                        .setLenient() //设置宽松的容错性
//                        .setPrettyPrinting() //设置漂亮的打印（打印出来的有缩进风格）
                        .create();
            }
        }
        return sGson;
    }

    public static Map<String, String> object2Map(Object object) {
        Map<String, String> result = new HashMap<>();
        if (object == null) {
            return result;
        }
        try {
            result.putAll(getsGson().fromJson(getsGson().toJson(object), new TypeToken<Map<String, String>>() {
            }.getType()));
        } catch (JsonSyntaxException exception) {
            KLog.e("object2Map解析异常");
        }
        return result;
    }

    public static Map<String, String> string2Map(String json) {

        Map<String, String> result = new HashMap<>();
        if (StringUtil.isTrimEmpty(json)) {
            return result;
        }
        try {
            result.putAll(getsGson().fromJson(json, new TypeToken<Map<String, String>>() {
            }.getType()));
        } catch (JsonSyntaxException exception) {
            KLog.e("string2Map解析异常");
        }
        return result;

    }

    public static String map2String(Object json) {

        String result;
        if (!(json instanceof Map)) {
            return "";
        }
        if (((Map<?, ?>) json).isEmpty()) {
            return "";
        }
        result = getsGson().toJson(json);
        return result;

    }

}
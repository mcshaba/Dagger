package com.dagger.testutils;

import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.reflect.Type;

public class TestUtils {
    private static TestUtils INSTANCE = new TestUtils();
    private static final Moshi TEST_MOSHI = initializeMoshi();
    private TestUtils(){

    }

    public static <T> T loadJson(String path, Type type){
        try {
            String json = getFileString(path);
            return (T) TEST_MOSHI.adapter(type).fromJson(json);
        } catch (IOException e){
            throw new IllegalArgumentException("could not deserialize: + path " + "into type: "+ type);
        }
    }

    public static <T> T loadJson()
}

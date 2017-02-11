package com.arbuleac.chuckjokes.injection;

import android.app.Application;

import com.arbuleac.chuckjokes.BuildConfig;
import com.arbuleac.chuckjokes.Config;
import com.arbuleac.chuckjokes.service.JokesService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injector {

    private static Map<Class, Object> objectMap = new HashMap<>();

    public static void init(final Application application) {

        objectMap.put(Application.class, application);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();

        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.cache(cache);
        okBuilder.connectTimeout(5, TimeUnit.SECONDS);
        okBuilder.readTimeout(20, TimeUnit.SECONDS);
        okBuilder.writeTimeout(20, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okBuilder.addInterceptor(interceptor);
        }
        OkHttpClient okClient = okBuilder.build();

        Retrofit retrofit =
                new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl(Config.API_URL)
                        .client(okClient)
                        .build();

        JokesService crmService = retrofit.create(JokesService.class);
        objectMap.put(JokesService.class, crmService);
    }

    public static <T> T obtain(Class<T> type) {
        return (T) objectMap.get(type);
    }
}

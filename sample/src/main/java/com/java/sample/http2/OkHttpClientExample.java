package com.java.sample.http2;


import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class OkHttpClientExample {
    public static void main(String[] args) {
        OkHttpClient okHttpClient=createHttpClient(10,5);
        Request request=new Request.Builder()
                .url("https://127.0.0.1:8443")
                .build();
        long startTime=System.nanoTime();
        for (int i =0; i<3 ; i++ ){
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                   long duration =TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
                    System.out.println("After"+duration +"seconds"+response.body().string());
                }
            });
        }

    }

    private static OkHttpClient createHttpClient(int maxTotalConnections ,long connectionKeepAliveTimeInMillis){
        ConnectionPool connectionPool=new ConnectionPool(maxTotalConnections,connectionKeepAliveTimeInMillis, TimeUnit.MILLISECONDS);
        return new OkHttpClient.Builder()
                    .followRedirects(false)
                    .protocols(Collections.singletonList(Protocol.H2_PRIOR_KNOWLEDGE))
                    .retryOnConnectionFailure(true)
                    .connectionPool(connectionPool)
                    .build();
    }
}

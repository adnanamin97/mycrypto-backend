package com.mycrypto.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycrypto.exceptions.ResourceNotFoundException;
import com.mycrypto.util.ControllerUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
public class Client {

    private final Logger log = LoggerFactory.getLogger(ControllerUtils.class);

    private OkHttpClient client = new OkHttpClient().newBuilder().build();


    public <T>  T getCryptoData(String queryParams, Class<T> type) {
        try {
            String trimmedParams = StringUtils.trimAllWhitespace((String) queryParams);
            Request request = new Request.Builder()
                    .url("" + trimmedParams)
                    .method("GET", null)
                    .build();

            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            return type.cast(objectMapper.readValue(responseBody.string(), AssetData.class));

        } catch (IOException e) {
            log.error("Something went wrong with the server!");
            throw new RuntimeException(e);

        }

    }
}

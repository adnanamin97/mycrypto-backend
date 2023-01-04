package com.mycrypto.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.util.annotation.Nullable;

@Component
public class Client {


   private final RestTemplate client;

   @Autowired
    public Client(final RestTemplate client) {
        this.client = client;
   }

    public <T> ResponseEntity<T> exchange(final String url,
                                          final HttpMethod httpMethod,
                                          @Nullable final HttpEntity<?> entity,
                                          final ParameterizedTypeReference<T> responseType,
                                          final Object... uriVariables)  {

       return client.exchange(url, httpMethod, entity, responseType);
    }
}


package com.mycrypto.http;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "rank",
    "symbol",
    "name",
    "supply",
    "maxSupply",
    "marketCapUsd",
    "volumeUsd24Hr",
    "priceUsd",
    "changePercent24Hr",
    "vwap24Hr"
})
@Generated("jsonschema2pojo")
public class AssetData {

    @JsonProperty("id")
    private String id;
    @JsonProperty("rank")
    private String rank;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("name")
    private String name;
    @JsonProperty("supply")
    private String supply;
    @JsonProperty("maxSupply")
    private String maxSupply;
    @JsonProperty("marketCapUsd")
    private String marketCapUsd;
    @JsonProperty("volumeUsd24Hr")
    private String volumeUsd24Hr;
    @JsonProperty("priceUsd")
    private String priceUsd;
    @JsonProperty("changePercent24Hr")
    private String changePercent24Hr;

    private String vwap24Hr;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonIgnore
    public String getVwap24Hr() {
        return vwap24Hr;
    }

    @JsonIgnore
    public void setVwap24Hr(String vwap24Hr) {
        this.vwap24Hr = vwap24Hr;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

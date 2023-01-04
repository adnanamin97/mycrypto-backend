
package com.mycrypto.http;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetData implements Serializable {


    private String id;

    private String rank;

    private String symbol;

    private String name;

    private String supply;


    private String maxSupply;

    private String marketCapUsd;

    private String volumeUsd24Hr;

    private String priceUsd;

    private String changePercent24Hr;

    private String vwap24Hr;

    private String explorer;




}

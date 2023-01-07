package com.mycrypto.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycrypto.model.response.AssetData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset implements Serializable {
    private List<AssetData> data;


}

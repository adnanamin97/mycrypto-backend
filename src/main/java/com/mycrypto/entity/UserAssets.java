package com.mycrypto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "assets")
public class UserAssets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    private int quantity;

    private String supply;

    @Column(name = "max_supply")
    private String maxSupply;

    @Column(name = "market_cap_usd")
    private String marketCapUsd;

    @Column(name = "volume_usd_2hr")
    private String volumeUsd24Hr;

    @Column(name = "price_usd")
    private String priceUsd;

    @Column(name = "change_percent_24hr")
    private String changePercent24Hr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

}

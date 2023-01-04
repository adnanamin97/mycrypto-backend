package com.mycrypto.service;

public enum Paths {

    ASSETS("/assets/"),
    RATES("/rates/"),
    EXCHANGES("/exchanges/"),
    MARKETS("/markets/"),
    CANDLES("/candles/");

    private final String path;
    Paths(final String path) {
        this.path = path;
    }

    public String path() {return this.path;};
}

package com.lampola.myyntitykki.service;

import com.lampola.myyntitykki.googleplaces.GeocodingResponse;

public interface GeoService {

    void setUri(String uri);

    GeocodingResponse findByAddress(String address);
}

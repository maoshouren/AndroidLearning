package com.example.asus.a_1;


import org.litepal.crud.LitePalSupport;

public class EnvData extends LitePalSupport {

    private String temperature;
    private String humidity;
    private String atmospherePressure;
    private String lightIntensity;
    private String ammoniaConcentration;
    private String carbonDioxide;
    private String oxygen;
    private String pm25;
    private String noise;
    private String longitude;
    private String latitude;
    private String time;
    private String date;

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String getTemperature() {
        return this.temperature;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getHumidity() {
        return this.humidity;
    }

    public void setAtmospherePressure(String atmospherePressure) {
        this.atmospherePressure = atmospherePressure;
    }
    public String getAtmospherePressure() {
        return this.atmospherePressure;
    }

    public void setLightIntensity(String lightIntensity) {
        this.lightIntensity = lightIntensity;
    }
    public String getLightIntensity() {
        return this.lightIntensity;
    }

    public void setAmmoniaConcentration(String ammoniaConcentration) {
        this.ammoniaConcentration = ammoniaConcentration;
    }
    public String getAmmoniaConcentration() {
        return this.ammoniaConcentration;
    }

    public void setCarbonDioxide(String carbonDioxide) {
        this.carbonDioxide = carbonDioxide;
    }
    public String getCarbonDioxide() {
        return this.carbonDioxide;
    }

    public void setOxygen(String oxygen) {
        this.oxygen = oxygen;
    }
    public String getOxygen() {
        return this.oxygen;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }
    public String getPm25() {
        return this.pm25;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }
    public String getNoise() {
        return this.noise;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLongitude() {
        return this.longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLatitude() {
        return this.latitude;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return this.time;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }
}

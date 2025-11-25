package com.example.demo.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String country;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeatherData> weatherData = new ArrayList<>();

    public Location() {}

    public Location(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<WeatherData> getWeatherData() {
        return weatherData;
    }

    public void addWeatherData(WeatherData data) {
        weatherData.add(data);
        data.setLocation(this);
    }

    public void removeWeatherData(WeatherData data) {
        weatherData.remove(data);
        data.setLocation(null);
    }
}

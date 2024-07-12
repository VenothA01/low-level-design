package com.dailycoder.ola.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static java.lang.Math.*;

@ToString
@Getter
@AllArgsConstructor
public class Location {

    private Double lat;
    private Double lon;


    public Double distance(Location otherLocation){
        return sqrt( pow(this.lat - otherLocation.lon, 2) + pow(this.lon - otherLocation.lon, 2) );
    }
}

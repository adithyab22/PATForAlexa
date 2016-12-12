/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pat.pojo;

/**
 *
 * @author Adithya
 */
public class Stops {

    private String stopID;//Stop ID
    private String stopName;//Description of stop
    private String direction;//inbound/outbound
    private String mode; //bus
    private double latitude; //latitude
    private double longitude; //longitude
    private int cleverID; //actual stop ID that shows up on all the apps
    private String routes;//String containing list of comma separated routes
    private String zone; //Zone number
    private String timepoint; //Don't know what this is..just says Yes/ No
    private String shelterOwn; //indicates whether the stop has a shelter
    private String stopType; //indicates type of stop: bus stop/ Train et al.

    public String getStopID() {
        return stopID;
    }

    public void setStopID(String stopID) {
        this.stopID = stopID;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCleverID() {
        return cleverID;
    }

    public void setCleverID(int cleverID) {
        this.cleverID = cleverID;
    }

    public String getRoutes() {
        return routes;
    }

    public void setRoutes(String routes) {
        this.routes = routes;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getTimepoint() {
        return timepoint;
    }

    public void setTimepoint(String timepoint) {
        this.timepoint = timepoint;
    }

    public String getShelterOwn() {
        return shelterOwn;
    }

    public void setShelterOwn(String shelterOwn) {
        this.shelterOwn = shelterOwn;
    }

    public String getStopType() {
        return stopType;
    }

    public void setStopType(String stopType) {
        this.stopType = stopType;
    }

}

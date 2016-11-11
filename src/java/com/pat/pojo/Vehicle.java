/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pat.pojo;

/**
*
 * @author Adithya
 * 
 * sample call:
 * http://truetime.portauthority.org/bustime/api/v2/getvehicles?key=929FvbAPSEeyexCex5a7aDuus&rt=61C&tmres=s&format=json
 */
public class Vehicle {
    private String error;
    private String tmstmp;
    private double lat;
    private double lon;
    private int hdg; //heading
    private String pid; //pattern id
    private String rt; //route
    private String des; //destination
    private boolean dly; //delay
    private int spd; //speed
    private String tabockid;
    private String zone;

    public Vehicle() {
    }

    public Vehicle(String error, String tmstmp, double lat, double lon, int hdg, String pid, String rt, String des, boolean dly, int spd, String tabockid, String zone) {
        this.error = error;
        this.tmstmp = tmstmp;
        this.lat = lat;
        this.lon = lon;
        this.hdg = hdg;
        this.pid = pid;
        this.rt = rt;
        this.des = des;
        this.dly = dly;
        this.spd = spd;
        this.tabockid = tabockid;
        this.zone = zone;
    }
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTmstmp() {
        return tmstmp;
    }

    public void setTmstmp(String tmstmp) {
        this.tmstmp = tmstmp;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getHdg() {
        return hdg;
    }

    public void setHdg(int hdg) {
        this.hdg = hdg;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isDly() {
        return dly;
    }

    public void setDly(boolean dly) {
        this.dly = dly;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public String getTabockid() {
        return tabockid;
    }

    public void setTabockid(String tabockid) {
        this.tabockid = tabockid;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
    
    
    
}

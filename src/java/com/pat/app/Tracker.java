/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pat.app;

import com.pat.pojo.Vehicle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Adithya
 */
public class Tracker {

    
    /**
     * Ask alexa the route number Example: 61C and the direction i.e. inbound/
     * outbound inbound: towards downtown/final stop outbound: Away from
     * downtown/ final stop
     *
     * @param routeID
     * @param direction
     */
    List<Vehicle> listofVehicles = null;

    public void askAlexa(String routeID, String direction) {
        //where is the nearest 61C towards inbound

    }

    public List<Vehicle> getDetails(JSONObject json) throws JSONException {
        listofVehicles = new ArrayList<>();
        JSONArray vehicles = json.getJSONObject("bustime-response").getJSONArray("vehicle");
        JSONArray errors = json.getJSONObject("bustime-response").getJSONArray("error");
        if (vehicles != null) {
            for (int i = 0; i < vehicles.length(); i++) {
                JSONObject vehicle = vehicles.getJSONObject(i);
                String tmstmp = vehicle.getString("name");
                double lat = vehicle.getDouble("lat");
                double lon = vehicle.getDouble("lon");
                int hdg = vehicle.getInt("hdg");
                String pid = vehicle.getString("pid");
                String rt = vehicle.getString("rt");
                String des = vehicle.getString("des");
                boolean dly = vehicle.getBoolean("dly");
                int spd = vehicle.getInt("spd");
                String tabockid = vehicle.getString("tabockid");
                String zone = vehicle.getString("zone");
                Vehicle v = new Vehicle(tmstmp, lat, lon, hdg, pid, rt, des, dly, spd, tabockid, zone);
                listofVehicles.add(v);
            }
        }else if(errors!= null) {
            for(int i=0; i < errors.length(); i++){
                JSONObject e = errors.getJSONObject(i);
                String error = e.getString("error");
                String rt = e.getString("rt");
                Vehicle v = new Vehicle(error, rt);
                listofVehicles.add(v);
            }
        }
        return listofVehicles;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pat.app;

import com.pat.pojo.Coordinates;
import com.pat.pojo.Stop;
import com.pat.pojo.Vehicle;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Adithya
 */
public class LocationTracker {
    List<Coordinates> coordinates = null;
    List<Stop> stops = null;
    public List<Coordinates> getLatLngDetails(JSONObject json) throws JSONException {
        coordinates = new ArrayList<>();
        JSONArray results = json.getJSONArray("results");
//        JSONArray errors = json.getJSONObject("bustime-response").getJSONArray("error");
        
        if(results != null){
            int length = (results.length() < 3)?results.length():3;
            for(int i = 0; i < length; i++){
                JSONObject location = results.getJSONObject(i).getJSONObject("geometry").getJSONObject("location");
                double lat = location.getDouble("lat");
                double lng = location.getDouble("lng");
                Coordinates c = new Coordinates();
                c.setLat(lat);
                c.setLng(lng);
                coordinates.add(c);
            }
        }
        return coordinates;
    }
    
    public List<Stop> getStopDetails(JSONObject json) throws JSONException {
        stops = new ArrayList<>();
        JSONArray stopsResponse = json.getJSONObject("bustime-response").getJSONArray("stops");
        
        if(stopsResponse != null){
            for (int i = 0; i < stopsResponse.length(); i++) {
                JSONObject stop = stopsResponse.getJSONObject(i);
                double lat = stop.getDouble("lat");
                double lon = stop.getDouble("lon");
                String stopID = stop.getString("stpid");
                String stpnm = stop.getString("stpnm");
                Stop s = new Stop(stopID, stpnm, lat, lon);
                stops.add(s);
            }
        }
        return stops;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pat.process;

import com.pat.app.LocationTracker;
import com.pat.pojo.Coordinates;
import com.pat.pojo.Stop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Adithya
 */
public class Processor {

    LocationTracker track = null;
    StopsProcessor sp = new StopsProcessor();
    Map<Integer, Stop> map = null;

    Processor() {
        track = new LocationTracker();
        sp = new StopsProcessor();
    }
   
    public void process(String source){
        
    }
    /**
     * Step 1
     * Pads "Pittsburgh" to input and requests the Google location API which returns list of location details.
     * 
     * Case 1: Request returns list of Coordinates
     * Proceed to Step 2
     * 
     * Case 2: Unable to understand source location
     * Ask user to try again.
     * 
     * Case 3: Source location very generic
     * Ask user to be more specific.

     * @param Location name / Landmark / Any keyword/s to identify source location
     * @return List of top 5 location details. (1st being the most relevant)
     * @throws IOException
     * @throws JSONException 
     */
    public List<Coordinates> getSourceLocationCoordinates(String source) throws IOException, JSONException {
        JSONObject currentLocationDetails = null;
        List<Coordinates> listOfLocationCoordinates = null;
        String currLocation = (source + " Pittsburgh").replaceAll("\\s", "+");
        String currLocationURL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + currLocation + "&sensor=false&key=AIzaSyBzW19DGDOi_20t46SazRquCLw9UNp_C8s";
        currentLocationDetails = readJsonFromUrl(currLocationURL);
        if (currentLocationDetails != null) {
            listOfLocationCoordinates = track.getLatLngDetails(currentLocationDetails);
        }
        return listOfLocationCoordinates;
    }

    /**
     * Step 2
     * 
     * @param lat
     * @param lng
     * @return List of top 5 nearest bus stops (1st being the nearest)
     * @throws IOException
     * @throws JSONException 
     */
    public List<Coordinates> findNearestBusStopsCoordinates(double lat, double lng) throws IOException, JSONException {
        JSONObject nearbyBusStationDetails = null;
        List<Coordinates> listOfNearestBusStopsCoordinates = null;
        String busStationsURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + lat + "," + lng + "&sensor=true&key=AIzaSyBzW19DGDOi_20t46SazRquCLw9UNp_C8s&rankby=distance&types=bus_station";
        nearbyBusStationDetails = readJsonFromUrl(busStationsURL);
        if (nearbyBusStationDetails != null) {
            listOfNearestBusStopsCoordinates = track.getLatLngDetails(nearbyBusStationDetails);
        }
        return listOfNearestBusStopsCoordinates; //Do Null check while implementing
    }
    
    /**
     * Step 3
     * Takes list of Stops Coordinates and performs DB call to return Stops objects using these coordinates
     * 
     * Case 1: Request does successful DB call and returns Stop objects
     * Proceed to Function 4
     * 
     * Case 2: DB doesn't return Stop Objects
     * Report to System administrator
     * 
     * @param stopCoordinates
     * @return
     * @throws FileNotFoundException 
     */
    public List<Stop> findNearestBusStops(List<Coordinates> stopCoordinates) throws FileNotFoundException {
        map = sp.readFromFile(); //replace with database call
        List<Stop> listOfStops = new ArrayList<>();
        int length = Math.min(5, stopCoordinates.size());
        for (int i = 0; i < length; i++) {
            double lat2 = Math.round(stopCoordinates.get(0).getLat() * 1000000.0) / 1000000.0;
            double lng2 = Math.round(stopCoordinates.get(0).getLng() * 1000000.0) / 1000000.0;
            Stop stop = map.get(sp.generateHash(lat2, lng2));
            listOfStops.add(stop);
        }
        return listOfStops; //Do Null check while implementing
    }
    
    /**
     * Step 4
     * @param routeID
     * @param stops
     * @return 
     */
    public String findStopIDIfRouteExists(String routeID, List<Stop> stops){
        for(Stop stop : stops){
            if(stop.getRoutes().contains(routeID)){
                return stop.getStopID();
            }
        }
        return "NotAvailable";
    }

    public int generateHash(Double x, Double y) {
        return x.hashCode() + y.hashCode();
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    
    public List<Stop> getStops(String stopID, String direction) throws IOException, JSONException{
       String url =  "http://truetime.portauthority.org/bustime/api/v2/getstops?key=929FvbAPSEeyexCex5a7aDuus&rt=61C&dir=INBOUND&format=json";
       JSONObject stopsJSON = null;
       List<Stop> listOfStops = null;
       stopsJSON = readJsonFromUrl(url);
       listOfStops = track.getStopDetails(stopsJSON);
       return listOfStops;
    }
    
    public Stop findNearestStop(double sourceLat, double sourceLon, List<Stop> stops) throws JSONException, IOException{
        Stop nearestStop = null;
        double shortestDistance = Double.MAX_VALUE;
        double distance;
        for(Stop s : stops){
            distance = calculateDistance(sourceLat, sourceLon, s.getLatitude(), s.getLongitude());
            if( distance < shortestDistance){
                shortestDistance = distance;
                nearestStop = s;
            }
        }
        return nearestStop;
    }
    /**
     * Calculating shortest distance as straight lines between points (for now) : Needs update
     * @param sourceLat
     * @param sourceLon
     * @param destLat
     * @param destLon
     * @return 
     */
    public double calculateDistance(double sourceLat, double sourceLon, double destLat, double destLon) throws JSONException, IOException{
        //https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=40.4413962,-80.0035603&destinations=40.4419433,-80.0055022&mode=walk&transit_mode=walking&key=AIzaSyBzW19DGDOi_20t46SazRquCLw9UNp_C8s
        //https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=40.4413962,-80.0035603&destinations=40.433251,-79.9257867&mode=walk&transit_mode=walking&key=AIzaSyBzW19DGDOi_20t46SazRquCLw9UNp_C8s
      //  return Math.sqrt(Math.pow(destLat - destLon, 2) + Math.pow(sourceLat - sourceLon, 2));
       String url =  "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+sourceLat+","+sourceLon+"&destinations="+destLat+","+destLon+"&mode=walk&transit_mode=walking&key=AIzaSyBzW19DGDOi_20t46SazRquCLw9UNp_C8s";
       JSONObject distanceJSON = null;
       String distance = "";
       distanceJSON = readJsonFromUrl(url);
       distance = distanceJSON.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getString("text");
       return convertMileToFeet(distance);
    }
    
    /**
     * Util method:
     */
    public double convertMileToFeet(String distance){
        double result = 0.0;
        if(distance.contains("mi")){
            result = Double.parseDouble(distance.substring(0, distance.length() - 2))*5280.0;
        }else if(distance.contains("ft")){
            result = Double.parseDouble(distance.substring(0, distance.length() - 2));
        }else{
            result = 0.0;
        }
        return result;
    }
    
    
}

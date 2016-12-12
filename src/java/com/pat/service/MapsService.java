/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pat.service;

import com.pat.app.LocationTracker;
import com.pat.app.VehicleTracker;
import com.pat.common.Constants;
import com.pat.pojo.Coordinates;
import com.pat.pojo.Stops;
import com.pat.pojo.Vehicle;
import com.pat.process.StopsProcessor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *Web service that calls the port authority api to get the bus location
 * The end point is q which is the city name which user queries the url.
 * @author Adithya Balasubramanian
 */
@SuppressWarnings("serial")
public class MapsService extends HttpServlet {

//    static String error = "";
//    static String tm = "";

    /**
     * Web application that strikes the url and reads JSON output.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nextView;    
        StopsProcessor sp = new StopsProcessor();
        Map<Integer, Stops> map = sp.readFromFile();
        //String url = "http://truetime.portauthority.org/bustime/api/v2/gettime?key=929FvbAPSEeyexCex5a7aDuus&format=json";
        String currLocation = request.getParameter("source").replaceAll("\\s","+");
        String locationURL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query="+currLocation+"&sensor=false&key=AIzaSyBzW19DGDOi_20t46SazRquCLw9UNp_C8s";
        String url3 = "http://truetime.portauthority.org/bustime/api/v1/getpredictions?key=929FvbAPSEeyexCex5a7aDuus&rt=61C&stpid=10950";
       // String url2 = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=49.89458000,-97.14137&sensor=true&key=AIzaSyBzW19DGDOi_20t46SazRquCLw9UNp_C8s&rankby=distance&types=bus_station";
        JSONObject json1;
        JSONObject json2;
        LocationTracker track = new LocationTracker();
        try {
            json1 = readJsonFromUrl(locationURL);
            List<Coordinates> list = track.getDetails(json1);
            double lat = list.get(0).getLat();
            double lng =  list.get(0).getLng();
            String url2 = fillURL(lat, lng);
            json2 = readJsonFromUrl(url2);
            List<Coordinates> list2 = track.getDetails(json2);
            double lat2 = Math.round(list2.get(0).getLat() * 1000000.0) / 1000000.0;
            double lng2 = Math.round(list2.get(0).getLng() * 1000000.0) / 1000000.0;
            Stops stop = map.get(sp.generateHash(lat2, lng2));
            request.setAttribute("stopName", stop.getStopName());
            request.setAttribute("routes", stop.getRoutes());
            String s[] = stop.getRoutes().replace("\"", "").split(",");
            List<String> routes  = new ArrayList<String>();
            routes.addAll(Arrays.asList(s));
            
            //String predictionsURL = "http://truetime.portauthority.org/bustime/api/v1/getpredictions?key=929FvbAPSEeyexCex5a7aDuus&rt=61C&stpid=10950";
           
          //  tm = (json.getJSONObject("bustime-response").get("tm")!=null)?json.getJSONObject("bustime-response").get("tm").toString():"time not available";
          //  error = (json.get("error").toString()!=null)?json.get("error").toString():"";
        } catch (JSONException ex) {
            Logger.getLogger(TrackerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      //  request.setAttribute("tm", tm);
       // request.setAttribute("error", error);
    	nextView = "result.jsp";

    	//direct to the next view as specified
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }
    
    private String fillURL(double x, double y){
        return "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+x+","+y+"&sensor=true&key=AIzaSyBzW19DGDOi_20t46SazRquCLw9UNp_C8s&rankby=distance&types=bus_station";
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
}

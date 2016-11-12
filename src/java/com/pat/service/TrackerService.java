/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pat.service;

import com.pat.common.Contants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
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
public class TrackerService extends HttpServlet {

    static String error = "";
    static String tm = "";

    /**
     * Web application that strikes the url and reads JSON output.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nextView;
        String url = "http://truetime.portauthority.org/bustime/api/v2/gettime?key=929FvbAPSEeyexCex5a7aDuus&format=json";
        JSONObject json;
        
        try {
            json = readJsonFromUrl(url);
            tm = (json.getJSONObject("bustime-response").get("tm")!=null)?json.getJSONObject("bustime-response").get("tm").toString():"time not available";
            error = (json.get("error").toString()!=null)?json.get("error").toString():"";
        } catch (JSONException ex) {
            Logger.getLogger(TrackerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("tm", tm);
        request.setAttribute("error", error);
    	nextView = "result.jsp";

    	//direct to the next view as specified
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
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

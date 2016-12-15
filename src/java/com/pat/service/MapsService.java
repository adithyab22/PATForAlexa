/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pat.service;
import com.pat.pojo.Stop;
import com.pat.process.NearestStopLocator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 *
 * @author Adithya
 */
@SuppressWarnings("serial")
public class MapsService extends HttpServlet {

    /**
     * Web application to determine nearest bus stop from a source location for a given route# and 
     * direction of transit.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {   
        NearestStopLocator p = new NearestStopLocator();
        
        //Test Inputs fom UI:
        String source = request.getParameter("source").replaceAll("\\s","+");
        String route = request.getParameter("route").trim();
        String direction = request.getParameter("direction").trim();
        
        try {
           Stop stop = p.process(source, route, direction);
           request.setAttribute("nearestStopName", stop.getStopName());
        } catch (JSONException ex) {
            Logger.getLogger(TrackerService.class.getName()).log(Level.SEVERE, null, ex);
        }

    	//direct to the results page
        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
        view.forward(request, response);
    }
}

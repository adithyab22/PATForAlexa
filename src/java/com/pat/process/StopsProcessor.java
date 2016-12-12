/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pat.process;

import com.pat.pojo.Stops;
import com.pat.common.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Adithya
 */
public class StopsProcessor {
    Map<Integer, Stops> stopsDB = new HashMap<>();
    /**
     * need to be replaced by a Database call
     * @return
     * @throws FileNotFoundException 
     */
    public Map<Integer, Stops> readFromFile() throws FileNotFoundException {
        {
            Scanner scan = new Scanner(new File(Constants.STOPS));
            while (scan.hasNext()) {
                Stops stop = new Stops();
                String curLine = scan.nextLine();
                String[] stopDetails = curLine.split("\t");
                String StopID = stopDetails[0].trim();
                String StopName = stopDetails[1].trim();
                String Direction = stopDetails[2].trim();
                String Mode = stopDetails[3].trim();
                double Latitude = Double.parseDouble(stopDetails[4].trim());
                double Longitude = Double.parseDouble(stopDetails[5].trim());
                int CleverID = Integer.parseInt(stopDetails[6].trim());
                String Routes = stopDetails[7].trim();
                String Zone = stopDetails[8].trim();
                String TimePoint = stopDetails[9].trim();
                String ShelterOwn = stopDetails[10].trim();
                String StopType = stopDetails[11].trim();
                //set details
                stop.setCleverID(CleverID);
                stop.setDirection(Direction);
                stop.setLatitude(Latitude);
                stop.setLongitude(Longitude);
                stop.setMode(Mode);
                stop.setRoutes(Routes);
                stop.setShelterOwn(ShelterOwn);
                stop.setStopID(StopID);
                stop.setStopName(StopName);
                stop.setStopType(StopType);
                stop.setTimepoint(TimePoint);
                stop.setZone(Zone);
                int hash = generateHash(Latitude, Longitude);
                stopsDB.put(hash, stop);
            }
            scan.close();
        }
        return stopsDB;
    }
    
    public int generateHash(Double x, Double y){
       return x.hashCode() + y.hashCode();
    }
   
}

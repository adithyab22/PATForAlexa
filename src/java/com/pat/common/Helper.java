/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pat.common;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Adithya
 */
public class Helper {
    public DateTime extractTime(String time){
       DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMyyyy HH:mm:ss");
       DateTime dt = formatter.parseDateTime(time);
       return dt;
    }
}

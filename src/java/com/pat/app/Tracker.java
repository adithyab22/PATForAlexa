/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pat.app;

/**
 *
 * @author Adithya
 */
public class Tracker {
    
    /**
     * Sample Response:
     * {
	"bustime-response": {
		"vehicle": [
			{
				"vid": "5649",
				"tmstmp": "20161110 22:42:11",
				"lat": "40.43650592457164",
				"lon": "-79.97032789750533",
				"hdg": "111",
				"pid": 4373,
				"rt": "61C",
				"des": "McKeesport ",
				"pdist": 8836,
				"dly": false,
				"spd": 52,
				"tatripid": "6822",
				"tablockid": "061C-261",
				"zone": ""
			},
			{
				"vid": "5816",
				"tmstmp": "20161110 22:42:00",
				"lat": "40.352210998535156",
				"lon": "-79.86114501953125",
				"hdg": "276",
				"pid": 4666,
				"rt": "61C",
				"des": "Downtown",
				"pdist": 993,
				"dly": false,
				"spd": 0,
				"tatripid": "7446",
				"tablockid": "061C-260",
				"zone": ""
			},
			{
				"vid": "5545",
				"tmstmp": "20161110 22:42:27",
				"lat": "40.406819015252786",
				"lon": "-79.91115995313301",
				"hdg": "253",
				"pid": 4666,
				"rt": "61C",
				"des": "Downtown",
				"pdist": 39430,
				"dly": false,
				"spd": 32,
				"tatripid": "7445",
				"tablockid": "061C-263",
				"zone": ""
			},
			{
				"vid": "5928",
				"tmstmp": "20161110 22:42:03",
				"lat": "40.44141",
				"lon": "-80.00101666666667",
				"hdg": "98",
				"pid": 4666,
				"rt": "61C",
				"des": "Downtown",
				"pdist": 80614,
				"dly": false,
				"spd": 0,
				"tatripid": "7443",
				"tablockid": "061C-262",
				"zone": ""
			}
		]
	}
}
     */
    /**
     * Ask alexa the route number Example: 61C
     * and the direction i.e. inbound/ outbound
     * inbound: towards downtown/final stop
     * outbound: Away from downtown/ final stop
     * @param routeID
     * @param direction 
     */
    
    public void askAlexa(String routeID, String direction){
        //where is the nearest 61C towards inbound
        
    }
    
    
    
}

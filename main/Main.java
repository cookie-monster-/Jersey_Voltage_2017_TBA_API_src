package main;

import com.cpjd.main.Settings;
import com.cpjd.main.TBA;
import com.cpjd.models.Event;
import com.cpjd.models.Match;
import com.cpjd.models.Team;
import com.cpjd.requests.MatchRequest;
import com.cpjd.requests.TeamRequest;

public class Main {
	// Get everything set up
	
	public static void main(String[] args){
	    TBA tba = new TBA();
	    TBA.setID("frc4587", "Scouting application", "V1");
	    Settings.GET_EVENT_MATCHES = true;
	    Settings.GET_EVENT_TEAMS = true;
	    Settings.FIND_TEAM_RANKINGS = true;
	    //Settings.GET_EVENT_STATS = true;
	    
	    Methods methods = new Methods();
	    
	    //methods.findEventClimbs("lake");
	    methods.findEventClimbPastAvgs("lake");
	    
	    //Match[] matches = bayou.matches; 
	    
	}
}

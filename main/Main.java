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
	    // Pull the team
	    
	    //Team team = tba.getTeam(m_teamNum);
	    // Print some information
	    /*System.out.println(team.name);
	    System.out.println(team.team_number);
	    System.out.println(team.website);
	    System.out.println(team.locality);
	    System.out.println(team.region);
	    System.out.println(team.country_name);
	    System.out.println(team.location);
	    System.out.println(team.key);
	    System.out.println(team.nickname);
	    System.out.println(team.rookie_year);
	    System.out.println(team.motto);*/
	    Settings.GET_EVENT_MATCHES = true;
	    Settings.GET_EVENT_TEAMS = true;
	    Settings.FIND_TEAM_RANKINGS = true;
	    //Settings.GET_EVENT_STATS = true;
	    
	    Methods methods = new Methods();
	    methods.findEventClimbs("lake");
	    //Match[] matches = bayou.matches; 
	    

    	
	}
}

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
	    String eventKey = "lake";
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
	    /*Event event = new TBA().getEvent(eventKey,2017);

		double eventAvg = 0.0;
	    int numTeams = event.teams.length;
	    Settings.GET_EVENT_MATCHES = true;
	    Settings.GET_EVENT_TEAMS = true;
	    Settings.FIND_TEAM_RANKINGS = true;

	    //for(int i = 0;i < event.teams.length;i++){
	    	System.out.println("hi");
		    int m_teamNum = (int) event.teams[0].team_number;
		    TeamRequest tr = new TeamRequest();
		    //Event[] events = tr.getTeamEvents((int) m_teamNum,2017);
		    Event[] teamEvents = tr.getTeamEvents(m_teamNum, 2017);
		    for(int j = 0;j < teamEvents.length;j++){
		    	try{
				    double climbs = 0.0;
			    	int matchesTeamsIn = 0;
			    	Match[] teamMatches = tr.getTeamEventMatches(2017, teamEvents[j].key, m_teamNum);
			    	for(int x = 0;x < teamMatches.length;x++){
		
			    		Match match = teamMatches[x];
			        	matchesTeamsIn = x+1;
			    		int climbVal = methods.findClimbValueNum(m_teamNum, match);
			    		//System.out.println("climbVal: "+climbVal);
			    		//System.out.println("match num: " + match.comp_level+match.match_number);
				    	if(climbVal >= 0){
				    		if(match.blueTeams[0].equals("frc"+m_teamNum) || match.blueTeams[1].equals("frc"+m_teamNum) || match.blueTeams[2].equals("frc"+m_teamNum)){
				    			if(match.blueValues[climbVal].equals("ReadyForTakeoff")){climbs+=1.0;}
				    			//else{System.out.println("match: " + match.comp_level+match.match_number+" "+m_teamNum+" missed climb");}
				    		}else{
				    			if(match.redValues[climbVal].equals("ReadyForTakeoff")){climbs+=1.0;}
				    			//else{System.out.println("match: " + match.comp_level+match.match_number+" "+m_teamNum+" missed climb");}
				    		}
				    	}
			    	}
			    	double teamClimbAvg = climbs/matchesTeamsIn;
			    	if(Double.isNaN(teamClimbAvg) == false){
			    		eventAvg += teamClimbAvg;
			    	}else{
			    		numTeams --;
			    	}
			    	System.out.println(teamEvents[j].name+" "+m_teamNum+" Climbs: "+climbs+" numMatches: "+matchesTeamsIn+" %climb: "+teamClimbAvg*100);
			    	//System.out.println(m_teamNum+" numMatches: "+matchesTeamsIn);
			    	//System.out.println(m_teamNum+" %climb: "+climbs / matchesTeamsIn*100);
		    	}catch(Exception e){}
		    }*/
	    //}
	}
}

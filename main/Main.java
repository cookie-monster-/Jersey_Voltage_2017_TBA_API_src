package main;

import com.cpjd.main.Settings;
import com.cpjd.main.TBA;
import com.cpjd.models.Event;
import com.cpjd.models.Match;
import com.cpjd.models.Team;
import com.cpjd.requests.TeamRequest;

public class Main {
	// Get everything set up
	
	public static void main(String[] args){
	    TBA tba = new TBA();
	    TBA.setID("frc4587", "Scouting application", "V1");
	    // Pull the team
	    Team team = tba.getTeam(4587);
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
	    Settings.GET_EVENT_STATS = true;
	    
	    Event bayou = new TBA().getEvent("2017lake");
	    Match[] matches = bayou.matches; 
	    TeamRequest tr = new TeamRequest();
	    Event[] events = tr.getTeamEvents((int) team.team_number,2017);
	    /*for(int i = 0; i<events.length;i++){
		    System.out.println(events[i].short_name);
	    }*/
	    /*System.out.println(matches[5].blueTeams[2]);
	    System.out.println(matches[5].scorableItems[1]);
	    System.out.println(matches[5].blueValues[12]);
	    for(int i = 0;i < matches[5].scorableItems.length;i++){
	    	System.out.println("Item: " + matches[5].scorableItems[i]+" Blue score: "+matches[5].blueValues[i]+" - "+i);
	    }*/
	    /*for(int i = 0; i < bayou.teams.length; i++){
	    	System.out.println("Number: "+bayou.teams[i].team_number+" Rank: "+bayou.teams[i].rank+" OPR: "+bayou.teams[i].opr);
	    }*/
	    /*for(int i = 0; i<bayou.matches.length; i++){
	    	System.out.println(bayou.matches[i].comp_level+bayou.matches[i].match_number+" Blue Alliance: "+bayou.matches[i].blueTeams[0]+","+bayou.matches[i].blueTeams[1]+","+bayou.matches[i].blueTeams[2]);
	    }*/
	    
	    int teamNum = 4587;
	    
    	double climbs = 0.0;
    	int matchesTeamsIn = matches.length;
    	for(int x = 0;x < matches.length;x++){

    		Match match = matches[x];

    		String dspos;
    		String alliance;
    		if(match.blueTeams[0].equals("frc"+teamNum)){alliance = "blue"; dspos = "0";}
    		if(match.blueTeams[1].equals("frc"+teamNum)){alliance = "blue"; dspos = "1";}
    		if(match.blueTeams[2].equals("frc"+teamNum)){alliance = "blue"; dspos = "2";}
    		if(match.redTeams[0].equals("frc"+teamNum)){alliance = "red"; dspos = "0";}
    		if(match.redTeams[1].equals("frc"+teamNum)){alliance = "red"; dspos = "1";}
    		if(match.redTeams[2].equals("frc"+teamNum)){alliance = "red"; dspos = "2";}
    		else{alliance = "purple"; dspos = "118";}
    		//else{alliance = "red"; dspos = "2";}
    		String[] pos = new String[]{"a","b"};
    		pos[0] = alliance;
    		pos[1] = dspos;
    		
    		if(pos[0].equals("blue") && pos[1].equals("0")){if(matches[x].blueValues[2].equals("ReadyForTakeoff")){climbs+=1.0;}}//far
    		if(pos[0].equals("blue") && pos[1].equals("1")){if(matches[x].blueValues[33].equals("ReadyForTakeoff")){climbs+=1.0;}}//middle
    		if(pos[0].equals("blue") && pos[1].equals("2")){if(matches[x].blueValues[21].equals("ReadyForTakeoff")){climbs+=1.0;}}//near
    		if(pos[0].equals("red") && pos[1].equals("0")){if(matches[x].redValues[2].equals("ReadyForTakeoff")){climbs+=1.0;}}//far
    		if(pos[0].equals("red") && pos[1].equals("1")){if(matches[x].redValues[33].equals("ReadyForTakeoff")){climbs+=1.0;}}//middle
    		if(pos[0].equals("red") && pos[1].equals("2")){if(matches[x].redValues[21].equals("ReadyForTakeoff")){climbs+=1.0;}}//near
    		else{matchesTeamsIn -= 1;}

        	System.out.println("Climbs: "+climbs);
        	System.out.println("numMatches: "+matchesTeamsIn);
    	}
    	System.out.println(climbs);
    	System.out.println(matchesTeamsIn);
    	System.out.println(climbs / matchesTeamsIn);
	    
	}
	private void climbPercent(int teamNum, Event event){
    	Match[] matches = event.matches;
    	double climbs = 0.0;
    	int matchesTeamsIn = matches.length;
    	for(int x = 0;x < matches.length;x++){
    		String[] dspos = findTeamPositionAndColor(teamNum, matches[x]);
    		if(dspos[0].equals("blue") && dspos[1].equals("0") && matches[x].blueValues[2].equals("ReadyForTakeoff")){climbs+=1.0;}//far
    		if(dspos[0].equals("blue") && dspos[1].equals("1") && matches[x].blueValues[33].equals("ReadyForTakeoff")){climbs+=1.0;}//middle
    		if(dspos[0].equals("blue") && dspos[1].equals("2") && matches[x].blueValues[21].equals("ReadyForTakeoff")){climbs+=1.0;}//near
    		if(dspos[0].equals("red") && dspos[1].equals("0") && matches[x].redValues[2].equals("ReadyForTakeoff")){climbs+=1.0;}//far
    		if(dspos[0].equals("red") && dspos[1].equals("1") && matches[x].redValues[33].equals("ReadyForTakeoff")){climbs+=1.0;}//middle
    		if(dspos[0].equals("red") && dspos[1].equals("2") && matches[x].redValues[21].equals("ReadyForTakeoff")){climbs+=1.0;}//near
    		else{matchesTeamsIn -= 1;}
    	}
    	System.out.println(climbs / matchesTeamsIn);
	}
	private String[] findTeamPositionAndColor(int teamNum, Match match){
		String dspos;
		String alliance;
		if(match.blueTeams[0].equals(teamNum)){alliance = "blue"; dspos = "0";}
		if(match.blueTeams[1].equals(teamNum)){alliance = "blue"; dspos = "1";}
		if(match.blueTeams[2].equals(teamNum)){alliance = "blue"; dspos = "2";}
		if(match.redTeams[0].equals(teamNum)){alliance = "red"; dspos = "0";}
		if(match.redTeams[1].equals(teamNum)){alliance = "red"; dspos = "1";}
		if(match.redTeams[2].equals(teamNum)){alliance = "red"; dspos = "2";}
		else{alliance = null; dspos = null;}
		String[] pos = new String[]{"a","b"};
		pos[0] = alliance;
		pos[1] = dspos;
		return pos;
	}
}

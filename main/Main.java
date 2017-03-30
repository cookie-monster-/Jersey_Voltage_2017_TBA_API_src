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
	    int m_teamNum = 4587;
	    Team team = tba.getTeam(m_teamNum);
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
	    Match[] teamMatches = tr.getTeamEventMatches(2017, "lake", m_teamNum);
	    Methods m = new Methods();
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
    	int matchesTeamsIn = teamMatches.length;
    	int badCount = 0;
    	for(int x = 0;x < teamMatches.length;x++){

    		Match match = teamMatches[x];
    		int climbVal = m.findClimbValueNum(m_teamNum, match);
    		//System.out.println("climbVal: "+climbVal);
    		System.out.println("match num: " + match.comp_level+match.match_number);
    		for(int i = 0;i < match.scorableItems.length;i++)
    		{
        		System.out.println(match.scorableItems[i]+" red "+match.redValues[i]+" blue "+match.blueValues[i]);	
    		}
	    	if(climbVal >= 0){
	    		if(match.blueTeams[0].equals("frc"+teamNum) || match.blueTeams[1].equals("frc"+teamNum) || match.blueTeams[2].equals("frc"+teamNum)){
	    			if(match.blueValues[climbVal].equals("ReadyForTakeoff")){climbs+=1.0;}
	    			//System.out.println("blue "+match.blueValues[climbVal]);
	    		}else{
	    			if(match.redValues[climbVal].equals("ReadyForTakeoff")){climbs+=1.0;}
	    			//System.out.println("red "+match.redValues[climbVal]);
	    		}
    		}else{
    			badCount++;
    		}
    		/*String dspos = "118";
    		String alliance = "purple";
    		if(match.blueTeams[0].equals("frc"+teamNum)){alliance = "blue"; dspos = "0";}
    		if(match.blueTeams[1].equals("frc"+teamNum)){alliance = "blue"; dspos = "1";}
    		if(match.blueTeams[2].equals("frc"+teamNum)){alliance = "blue"; dspos = "2";}
    		if(match.redTeams[0].equals("frc"+teamNum)){alliance = "red"; dspos = "0";}
    		if(match.redTeams[1].equals("frc"+teamNum)){alliance = "red"; dspos = "1";}
    		if(match.redTeams[2].equals("frc"+teamNum)){alliance = "red"; dspos = "2";}
    		//System.out.println("b0 "+match.blueTeams[0]+" b1 "+match.blueTeams[1]+" b2 "+match.blueTeams[2]+" r0 "+match.redTeams[0]+" r1 "+match.redTeams[1]+" r2 "+match.redTeams[2]);
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
*/
        	System.out.println("Climbs: "+climbs);
        	//System.out.println("numMatches: "+matchesTeamsIn);
    	}
    	MatchRequest mr = new MatchRequest();
    	Match xx = mr.getMatch(2017, "lake", "sf2m2");
    	for(int i = 0;i < xx.scorableItems.length;i++){
    		System.out.println(xx.scorableItems[i]+" red "+xx.redValues[i]+" blue "+xx.blueValues[i]);
    	}

    	System.out.println(climbs);
    	System.out.println(matchesTeamsIn);
    	System.out.println(climbs / matchesTeamsIn);
    	System.out.println(badCount);
    	System.out.println(climbs / (matchesTeamsIn-badCount));
	}
}

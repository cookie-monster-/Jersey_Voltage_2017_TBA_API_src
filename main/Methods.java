package main;

import com.cpjd.main.Settings;
import com.cpjd.main.TBA;
import com.cpjd.models.Event;
import com.cpjd.models.Match;
import com.cpjd.requests.TeamRequest;

public class Methods {
	public void climbPercent(int teamNum, Event event){
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
	public String[] findTeamPositionAndColor(int teamNum, Match match){
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
	public int findClimbValueNum(int teamNum, Match match){
		int climbValue = -1;
		//0-far, 1-middle, 2-near
		String touchpad = "lowbar";
		if(match.blueTeams[0].equals("frc"+teamNum)){touchpad = "touchpadFar";}
		if(match.blueTeams[1].equals("frc"+teamNum)){touchpad = "touchpadMiddle";}
		if(match.blueTeams[2].equals("frc"+teamNum)){touchpad = "touchpadNear";}
		if(match.redTeams[0].equals("frc"+teamNum)){touchpad = "touchpadFar";}
		if(match.redTeams[1].equals("frc"+teamNum)){touchpad = "touchpadMiddle";}
		if(match.redTeams[2].equals("frc"+teamNum)){touchpad = "touchpadNear";}
		
		for(int i = 0;i < match.scorableItems.length;i++){
			//System.out.println(match.scorableItems[i] + " red " + match.redValues[i] + " blue " + match.blueValues[i]);
			if(match.scorableItems[i] != null){
				if(match.scorableItems[i].equals(touchpad)){
					climbValue = i;
				}
			}
		}
		return climbValue;
	}
	public void findEventClimbs(String eventKey){
	    Event event = new TBA().getEvent(eventKey,2017);
		double eventAvg = 0.0;
	    int numTeams = event.teams.length;
	    Settings.GET_EVENT_MATCHES = true;
	    Settings.GET_EVENT_TEAMS = true;
	    Settings.FIND_TEAM_RANKINGS = true;
	    //Settings.GET_EVENT_STATS = true;
	    
	    for(int i = 0;i < event.teams.length;i++){
		    int m_teamNum = (int) event.teams[i].team_number;
		    TeamRequest tr = new TeamRequest();
		    //Event[] events = tr.getTeamEvents((int) m_teamNum,2017);
		    Match[] teamMatches = tr.getTeamEventMatches(2017, eventKey, m_teamNum);
		    Methods methods = new Methods();
		    double climbs = 0.0;
	    	int matchesTeamsIn = 0;
	    	for(int x = 0;x < teamMatches.length;x++){

	    		Match match = teamMatches[x];
	        	matchesTeamsIn = x+1;
	    		int climbVal = methods.findClimbValueNum(m_teamNum, match);
	    		//System.out.println("climbVal: "+climbVal);
	    		//System.out.println("match num: " + match.comp_level+match.match_number);
	    		/*for(int i = 0;i < match.scorableItems.length;i++)
	    		{
	        		System.out.println(match.scorableItems[i]+" red "+match.redValues[i]+" blue "+match.blueValues[i]);	
	    		}*/
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
	    	System.out.println(m_teamNum+" Climbs: "+climbs+" numMatches: "+matchesTeamsIn+" %climb: "+teamClimbAvg*100+" - "+i);
	    	//System.out.println(m_teamNum+" numMatches: "+matchesTeamsIn);
	    	//System.out.println(m_teamNum+" %climb: "+climbs / matchesTeamsIn*100);
	    }
	    System.out.println(eventKey+" climb avg: "+eventAvg/numTeams*100+"%");
	}
}

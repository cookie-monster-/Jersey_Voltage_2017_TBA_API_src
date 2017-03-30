package main;

import com.cpjd.models.Event;
import com.cpjd.models.Match;

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
}

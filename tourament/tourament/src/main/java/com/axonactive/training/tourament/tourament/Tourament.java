package com.axonactive.training.tourament.tourament;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import com.axonactive.training.tourament.match.Match;
import com.axonactive.training.tourament.matchresult.MatchResult;
import com.axonactive.training.tourament.table.Table;
import com.axonactive.training.tourament.team.Team;

import lombok.Getter;
import lombok.Setter;


public class Tourament {
	
	public static final int MINIMUN_TEAM_NUMBER_IN_ONE_TABLE = 4;
	public static final int MINIMUM_MEMBER_NUMBER_IN_ONE_TEAM = 7;
	
	/**
	 * store the name of the tourament
	 * @author vhgia
	 */
	private @Getter @Setter String name;
	
	/**
	 * store the list of the team join in this tourament
	 * @author vhgia
	 */
	private @Getter @Setter ArrayList<Team> teamList;
	
	/**
	 * store the list of the match in this tourament
	 * @author vhgia
	 */
	private @Getter ArrayList<Match> matchList;
	
	/**
	 * store the list of result of match in the tourament.
	 * eatch match will have two result for two team
	 * @author vhgia
	 */
	private @Getter ArrayList<MatchResult> matchResultList;
	
	/**
	 * store the name of table round in the tourament
	 * @author vhgia
	 */
	private @Getter ArrayList<Table> tableList;
	
	/**
	 * 
	 * @param name
	 */
	public Tourament(String name) {
		this.name = name;
		this.teamList = new ArrayList<>();
		this.matchList =  new ArrayList<>();
		this.matchResultList = new ArrayList<>();
		this.tableList = new ArrayList<>();
	}
	
 	/**
 	 * 
 	 * @param team
 	 */
	public void addTeam(Team team) {
		if(Objects.isNull(team)) {
			throw new IllegalArgumentException("The input Team is null");
		}
;		
		if(teamList.contains(team)) {
			throw new IllegalArgumentException("The input Team was Exist");
		}
		
//		if(team.getPlayers().size() < MINIMUM_MEMBER_NUMBER_IN_ONE_TEAM) {
//			String message = String.format("The input Team isn't enough Player. Current Player: %s. Require Player: %s",
//												teamList.size(), Team.MINIMUM_PLAYER_IN_TEAM);
//			throw new IllegalArgumentException(message);
//		}
		
		this.teamList.add(team);
	}
	                                     
	/**
	 * 
	 */
	public void arrangeTeamIntoTable() {
		
		if(teamList.size() <= MINIMUN_TEAM_NUMBER_IN_ONE_TABLE) {
			throw new IllegalArgumentException("Team number isn't enough to arrange");
		}
		else {
			
			int teamSize = teamList.size();
			
			int tableSize = teamSize / MINIMUN_TEAM_NUMBER_IN_ONE_TABLE;
			
			
			for(int i = 0; i < tableSize; i++) {
				char TableName = (char)(i + 65);
				Table tempTable = new Table(Character.toString(TableName));
				tableList.add(tempTable);
			}
			
			for(int i = 0; i < teamSize; i++) {
				tableList.get((i/MINIMUN_TEAM_NUMBER_IN_ONE_TABLE)%2).addTeam(teamList.get(i));
			}

		}
	}
	
	/**
	 * 
	 */
	public void scheduleTableRound() {
		for(Table table : tableList) {
			
			setMatchTableRound(table);
			
		}
	}
	
	/**
	 * 
	 * @param table
	 */
	public void setMatchTableRound(Table table) {
		int teamNumber = table.getTeamList().size();
		
		for (int i = 0; i < teamNumber - 1; i++)
			for (int j = i + 1; j < teamNumber; j++) {
				Match match = new Match(table.getTeamList().get(i), table.getTeamList().get(j));
				
				matchList.add(match);
			}
	}
	
	/**
	 * 
	 * @param match
	 * @param schedule
	 * @param place
	 */
	public void updateMatch(Match match, Date schedule, String place) {
		int index = matchList.indexOf(match);
		match.setPlace(place);
		match.setSchedule(schedule);
		matchList.set(index, match);
	}
	
	/**
	 * 
	 */
	public void setMatchResult() {
		MatchResult matchResult = null;
		for (Match match: matchList) {
			matchResult = new MatchResult(0, 0, match, match.getTeam1());
			matchResultList.add(matchResult);
			
			matchResult = new MatchResult(0, 0, match, match.getTeam2());
			matchResultList.add(matchResult);
		}
	}
	
	/**
	 * 
	 */
	public void fillTeam() {
		int tableNum = tableList.size();
		
		for (int i = 0; i < tableNum; i++) {
			Table table1 = tableList.get((i%tableNum));
			Table table2 = tableList.get(((i+1)%tableNum));
			
			Team bestTeam = getBestTeamInTable(table1);
			Team secondTeam = getSecondBestTeamInTable(table2);
			
			Match match = new Match(bestTeam, secondTeam);
			matchList.add(match);
		}
	}
	
	/**
	 * 
	 * @param table
	 * @return
	 */
	public Team getBestTeamInTable(Table table) {
		int maxScore=0;
		int score = 0;
		ArrayList<Team> teamList = table.getTeamList();
		Team bestTeam = null;
		
		for (Team team : teamList) {
			if (score > maxScore) {
				bestTeam = team;
				maxScore = score;
			}
		}
		
		return bestTeam;
	}
	
	/**
	 * 
	 * @param table
	 * @return
	 */
	public Team getSecondBestTeamInTable(Table table) {
		int maxScore = 0;
		int secondMaxScore = 0;
		int score = 0;
		ArrayList<Team> teamList = table.getTeamList();
		Team secondBestTeam = null;
		
		for (Team team: teamList) {
			score = team.getScore();
			if (score > maxScore) {
				secondMaxScore = maxScore;
				maxScore = score;
			}
			else if (score < maxScore && score > secondMaxScore) {
				secondMaxScore = score;
				secondBestTeam = team;
			}
		}
		
		return secondBestTeam;
	}
	
	
}

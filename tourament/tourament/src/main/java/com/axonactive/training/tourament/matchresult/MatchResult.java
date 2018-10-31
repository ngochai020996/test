package com.axonactive.training.tourament.matchresult;

import com.axonactive.training.tourament.match.Match;

import com.axonactive.training.tourament.team.Team;

import lombok.Getter;
import lombok.Setter;


public class MatchResult {
	
	private @Getter @Setter int score;
	
	/**
	 * store the goal of team got in this match
	 * @author vhgia
	 */
	private @Getter @Setter int goal;
	
	/**
	 * store the match have this result
	 * @author vhgia
	 */
	private Match match;
	
	/**
	 * store the team which get this result
	 * @author vhgia
	 */
	private Team team;
	
	/**
	 * 
	 * @param score
	 * @param goal
	 * @param match
	 * @param team
	 */
	public MatchResult(int score,int goal,Match match, Team team) {
		this.score = score;
		this.goal = goal;
		this.match = match;
		this.team = team;
	}
}

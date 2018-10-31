package com.axonactive.training.tourament.match;

import java.util.Date;

import com.axonactive.training.tourament.team.Team;

import lombok.Getter;
import lombok.Setter;

//@EqualsAndHashCode(of = {"team1","team2"})
public class Match {
	
	/**
	 * store the time when the match happen
	 * @author vhgia
	 */
	private @Getter @Setter Date schedule;
	
	/**
	 * store the place when the match happen
	 * @author vhgia
	 */
	private @Getter @Setter String place;
	
	/**
	 * store the first team join in this match
	 * @author vhgia
	 */
	private @Getter Team team1;
	
	/**
	 * store the second team join in this match
	 * @author vhgia
	 */
	private @Getter Team team2;
	
	/**
	 * 
	 * @param team1
	 * @param team2
	 */
	public Match(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
	}

}

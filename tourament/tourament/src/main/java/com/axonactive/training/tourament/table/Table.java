package com.axonactive.training.tourament.table;

import java.util.ArrayList;
import com.axonactive.training.tourament.team.*;

import lombok.Getter;

public class Table {
	
	/**
	 * store the name of the table in the tourament
	 * @author vhgia
	 */
	private String name;
	
	/**
	 * store all of team in this table
	 * @author vhgia
	 */
	private @Getter ArrayList<Team> teamList;
	
	/**
	 * @param name
	 */
	public Table(String name) {
		this.name = name;
		this.teamList = new ArrayList<>();
	}

	/**
	 * 
	 * @param team
	 */
	public void addTeam(Team team) {
		/*if(Objects.isNull(team)) {
			throw new IllegalArgumentException("The input Team is null");
		}
		
		if(teamList.contains(team)) {
			throw new IllegalArgumentException("The input Team was Exist");
		}*/
		
		this.teamList.add(team);
	}
	
}

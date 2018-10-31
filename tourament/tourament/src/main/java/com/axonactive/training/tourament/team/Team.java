package com.axonactive.training.tourament.team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.axonactive.training.tourament.player.Player;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_team")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class Team implements Serializable{
	
	/**
	 * 
	 */
	
	public static final int MAXIMUM_PLAYER_IN_TEAM = 12;
	public static final int MINIMUM_PLAYER_IN_TEAM = 7;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * store the name of team
	 * @author vhgia
	 */
	@Column(name = "name", length = 100, nullable = false, unique = false)
	private @Getter String name;
	
	/**
	 * store the score of team
	 * @author vhgia
	 */
	@Column(name = "score", nullable = false, unique = false )
	private @Setter @Getter int score;
	
	/**
	 * @author vhgia
	 */
	@OneToMany()
	@JoinColumn(name = "team_id", nullable = false)
	private List<Player> players;
	
	/**
	 * 
	 * @param name
	 */
	public Team(String name) {
		this.name = name;
		this.score = 0;
		this.players = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param player
	 */
	public void addNewPlayer(Player player) {
//		if(Objects.isNull(player)) {
//			throw new IllegalArgumentException("the input missing player");
//		}
//		
//		if (players.contains(player)) {
//			throw new IllegalArgumentException("the input exist");
//		}
//		
//		if(!checkPlayer(player)){
//			throw new IllegalArgumentException("the input player is invalid");
//		}
//		
//		if (players.size() >= MAXIMUM_PLAYER_IN_TEAM) {
//			throw new IllegalArgumentException("Team is full");
//		}
		
		//players.add(player);
		
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	public boolean checkPlayer(Player player) {
		//Do something
		return true;
	}
	
}

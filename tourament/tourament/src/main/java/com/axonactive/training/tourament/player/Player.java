package com.axonactive.training.tourament.player;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_player")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"socialInsurranceNumber"})
public class Player implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * store the name of player
	 * @author vhgia
	 */
	@Column(name = "name", length = 100, nullable = false, unique = false)
	private String name;
	
	/**
	 * store the social insurrance number of player to know where player is working
	 * @author vhgia
	 */
	@Column(name = "social_isurrance_number", length = 9, nullable = false, unique = true)
	private String socialInsurranceNumber;
	
	/**
	 * store the number in suit of player
	 * @author vhgia
	 */
	@Column(name =  "number", nullable = false, unique = false)
	private int number;
	
	/**
	 * store the birthday of player
	 * @author vhgia
	 */
	@Column(name = "dob", nullable = false, unique = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	/**
	 * store the gender of player
	 * @author vhgia
	 */
	@Column(name = "Gender", nullable = false, unique = false)
	@Convert(converter = GenderConverter.class)
	private Gender gender;
	

	public Player(String name, String socialInsurranceNumber, int number, Date dateOfBirth, Gender gender) {
		this.name = name;
		this.socialInsurranceNumber = socialInsurranceNumber;
		this.number = number;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}

	
}

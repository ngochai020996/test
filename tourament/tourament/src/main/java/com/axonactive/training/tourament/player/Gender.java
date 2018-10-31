package com.axonactive.training.tourament.player;

import lombok.Getter;

public enum Gender {
	MALE(0),
	FEMALE(1),
	UNKNKOWN(2);
	
	private @Getter int value;
	
	private Gender(int value) {
		this.value = value;
	}
	
}

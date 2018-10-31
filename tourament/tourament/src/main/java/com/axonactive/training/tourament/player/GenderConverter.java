package com.axonactive.training.tourament.player;

import javax.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender, Integer>{

	@Override
	public Integer convertToDatabaseColumn(Gender attribute) {
		// TODO Auto-generated method stub
		return attribute.getValue();
	}

	@Override
	public Gender convertToEntityAttribute(Integer dbData) {
		// TODO Auto-generated method stub
		switch (dbData){
		case 0:
			return Gender.MALE;
		case 1:
			return Gender.FEMALE;
		case -1:
			return Gender.UNKNKOWN;
		default:
			throw new IllegalStateException("Value is wrong");
		}
	}

}

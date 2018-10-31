package com.axonactive.training.tourament.team;

import static org.junit.Assert.assertThat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.axonactive.training.tourament.player.Gender;
import com.axonactive.training.tourament.player.Player;
import com.axonactive.training.tourament.team.Team;

public class TeamTest {
	
	private Team team;
	
	@Before
	public void createTeam() {
		team = new Team("Axon Active Can Tho");
		
		/**/
	}
	
	@Test
	public void givenANewTeamWhenInputAPlayerWithFullInfoThenTeamSizeIncreaseByOne() {
		Player player = new Player("Nguyen Hoang Nam", "ST159369", 15, new Date(), Gender.MALE);
		
		team.addNewPlayer(player);
		//assertThat(team.getPlayers().size(), Is.is(1));
	}
	
	@Test( expected = IllegalArgumentException.class)
	public void givenANewTeamWhenInputAPlayerWithoutInfoThenIllegalArgumentExceptionThrown() {
		team.addNewPlayer(null);
	}
	
	@Test( expected = IllegalArgumentException.class)
	public void givenANewTeamWhenInputAPlayerExistThenIllegalArgumentExceptionThrown() {
		Player player = new Player("Nguyen Hoang Nam", "ST159369", 15, new Date(), Gender.MALE);
		
		team.addNewPlayer(player);
		team.addNewPlayer(player);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void givenANewTeamWhenInputAPlayerIntoTheFullTeamThenIllegalArgumentExceptionThrown() throws Exception {
		
			
		Workbook workbook = WorkbookFactory.create(new File("src\\test\\resources\\teams.xlsx"));
		
		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
		
		DataFormatter dataFormatter = new DataFormatter();
		
		if (sheetIterator.hasNext()) {
			Sheet sheet = sheetIterator.next();
			Iterator<Row> rowIterator = sheet.rowIterator();
			
			if (rowIterator.hasNext()) {
				rowIterator.next();
			}
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
            	
            	String name = dataFormatter.formatCellValue(cellIterator.next());
            	String socialNumber = dataFormatter.formatCellValue(cellIterator.next());
            	int num = Integer.parseInt(dataFormatter.formatCellValue(cellIterator.next()));
            	Date date = new SimpleDateFormat("dd-MMM-yyyy").parse(dataFormatter.formatCellValue(cellIterator.next()));
            	Gender gender = Gender.valueOf(dataFormatter.formatCellValue(cellIterator.next()));
            	
            	
            	Player player = new Player(name,socialNumber,num,date,gender);
            	team.addNewPlayer(player);
			
			}
		}
			
		team.addNewPlayer(new Player("Nguyen Hoang Nam", "ST159369", 15, new Date(), Gender.MALE));
		team.addNewPlayer(new Player("Nguyen Hai Hoang", "ST159569", 17, new Date(), Gender.MALE));
		team.addNewPlayer(new Player("Nguyen Hai Hoa", "ST169369", 1, new Date(), Gender.MALE));
		team.addNewPlayer(new Player("Tran Hoang Nam", "ST159366", 5, new Date(), Gender.MALE));
		team.addNewPlayer(new Player("Tran Trung Tin", "ST259369", 11, new Date(), Gender.MALE));
		team.addNewPlayer(new Player("Nguyen Thai Hoa", "ST151369", 10, new Date(), Gender.MALE));
	}
	
	@Test
	public void givenANewTeamWhenUpdateTeamScoreThenTeamScoreUpdated() {
		team.setScore(5);
		assertThat(team.getScore(), Is.is(5));
	}
	
	@After
	public void removeTeam() {
		team = null;
	}
	
}

package com.axonactive.training.tourament.tourament;

import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.axonactive.training.tourament.match.Match;
import com.axonactive.training.tourament.player.Gender;
import com.axonactive.training.tourament.player.Player;
import com.axonactive.training.tourament.table.Table;
import com.axonactive.training.tourament.team.Team;


public class TouramentTest {
	
	private Tourament tourament;
	
	private ArrayList<Team> teamList;
	
	@Before
	public void createTourament() {
		teamList = new ArrayList<>();
		
			try {
////			
//////			Stream<Player> Aplayers = ExcelReader.load(RowToPlayerMapper.mapToPlayer(), true, 0, this.getClass(), "dataset", "teams");
//////			   List<Player> aMembers = Aplayers.collect(Collectors.toList());
//////			   teamA = new Team("Team A", aMembers);
			
			Workbook workbook = WorkbookFactory.create(new File("src\\test\\resources\\teams.xlsx"));
			
			Iterator<Sheet> sheetIterator = workbook.sheetIterator();
			
			DataFormatter dataFormatter = new DataFormatter();
			
			while (sheetIterator.hasNext()) {
	            Sheet sheet = sheetIterator.next();
	            Team team = new Team(sheet.getSheetName());
	            
	            Iterator<Row> rowIterator = sheet.rowIterator();
	            
	            if (rowIterator.hasNext())
	            	rowIterator.next();
	            
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
	            
	            teamList.add(team);
	        }
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tourament = new Tourament("Axon Active Vietnam Football Tourament");
	}
	
	@Test
	public void givenANewTouramentWhenUpdateNameOfTouramentWhenTheTouramentUpdated() {
		tourament.setName("Axon Active Viet Nam Football Day");
		Assert.assertEquals("Axon Active Viet Nam Football Day", tourament.getName());
	}
	
	@Test
	public void givenANewTouramentWhenInputSomeTeamWithFullInfoThenTheNumOfTeamIncrease() {
		Assert.assertEquals(0, tourament.getTeamList().size());
		for (Team team : teamList) {
			tourament.addTeam(team);
		}
		assertThat(tourament.getTeamList().size(), Is.is(teamList.size()));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void givenANewTouramentWhenInputATeamNullThenIllegalArgumentExceptionThrown() {
		for (Team team : teamList) {
			tourament.addTeam(team);
		}
		
		tourament.addTeam(null);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void givenANewTouramentWhenInputATeamExistThenIllegalArgumentExceptionThrown() {
		
		for (Team team : teamList) {
			tourament.addTeam(team);
		}
		
		tourament.addTeam(teamList.get(3));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void givenANewTouramentWhenInputATeamNotEnoughPlayerThenIllegalArgumentExceptionThrown() {
		
		tourament.addTeam(new Team("Team 1"));
		
	}
	
	@Test
	public void givenANewTouramentWhenArrangeTeamToRoundTableThenComplete() {
		
		for (Team team : teamList) {
			tourament.addTeam(team);
		}
		
		tourament.arrangeTeamIntoTable();
		
		int numOfTable= teamList.size()/tourament.MINIMUN_TEAM_NUMBER_IN_ONE_TABLE;
		
		assertThat(tourament.getTableList().size(),Is.is(numOfTable));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenANewTouramentWhenArrangeTeamToRoundWithEnoughtTeamThenIllegalArgumentExceptionThrown() {
		
		for (Team team : teamList) {
			tourament.addTeam(team);
		}

		tourament.setTeamList(new ArrayList<Team>());
		
		tourament.arrangeTeamIntoTable();
	}
	
	@Test
	public void givenANewTouramentWhenSetMatchThenSetMatchComplete() {
		
		for (Team team : teamList) {
			tourament.addTeam(team);
		}
		
		tourament.arrangeTeamIntoTable();
		
		tourament.scheduleTableRound();
		
		int numOfMatch = 0;
		for (Table table : tourament.getTableList()) {
			numOfMatch = numOfMatch + ((table.getTeamList().size()*(table.getTeamList().size()-1))/2);
		}
		
		assertThat(tourament.getMatchList().size(),Is.is(numOfMatch));
	}
	
	@Test
	public void givenANewTouramentWhenUpdateMatchThenMatchUpdated() {

		for (Team team : teamList) {
			tourament.addTeam(team);
		}
		
		tourament.arrangeTeamIntoTable();
		
		tourament.scheduleTableRound();
		
		Date schedule = new Date();
		String place = "Can Tho";
		
		Match match = tourament.getMatchList().get(1);
		tourament.updateMatch(match, schedule, place);
		assertThat(tourament.getMatchList().get(1).getPlace(),Is.is("Can Tho"));
		assertThat(tourament.getMatchList().get(1).getSchedule(),Is.is(schedule));
	}
	
	@Test
	public void givenANewTouramentWhenSetMatchResultThenMatchResultComplete() {
		
		for (Team team : teamList) {
			tourament.addTeam(team);
		}
		
		tourament.arrangeTeamIntoTable();
		
		tourament.scheduleTableRound();
		
		tourament.setMatchResult();
		
		assertThat(tourament.getMatchResultList().size(), Is.is(tourament.getMatchList().size()*2));
	}
	

	@Test
	public void givenANewTouramentWhenUpdateGoalOfMatchResultThenMatchResultUpdated() {
		for (Team team : teamList) {
			tourament.addTeam(team);
		}
		
		tourament.arrangeTeamIntoTable();
		
		tourament.scheduleTableRound();
		
		tourament.setMatchResult();
		
		tourament.getMatchResultList().get(1).setGoal(5);
		
		assertThat(tourament.getMatchResultList().get(1).getGoal(), Is.is(5));
	}
	
	@Test
	public void givenANewTouramentWhenUpdateScoreMatchResultThenMatchResultUpdated() {
		for (Team team : teamList) {
			tourament.addTeam(team);
		}
		
		tourament.arrangeTeamIntoTable();
		
		tourament.scheduleTableRound();
		
		tourament.setMatchResult();
		
		tourament.getMatchResultList().get(1).setScore(5);
		
		assertThat(tourament.getMatchResultList().get(1).getScore(), Is.is(5));
	}
	
	@Test
	public void givenANewTouramentWhenInputScoreForEachTeamThenBestTeamWillContinue() {
		for (Team team: teamList) {
			tourament.addTeam(team);
		}
		
		Random random = new Random();
		
		int count = teamList.size();
		for (int i = 0; i < count; i++) {
			tourament.getTeamList().get(i).setScore(random.nextInt(10)+1);
		}
		
		tourament.arrangeTeamIntoTable();
		
		tourament.fillTeam();
		assertThat(tourament.getTableList().size(), Is.is(2));
	}
	
	@After
	public void removeTourament() {
		teamList = null;
		tourament = null;
	}
	
}

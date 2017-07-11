package com.techelevator.npgeek.dao;
import javax.sql.DataSource;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.Park;



public class JdbcParkDaoTest {
	
	private static SingleConnectionDataSource dataSource;
	private JdbcParkDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dataSource.destroy();
	}

	@Before
	public void setUp() throws Exception {	
		dao = new JdbcParkDao(dataSource);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		jdbcTemplate.update("DELETE FROM weather");
		jdbcTemplate.update("DELETE FROM survey_result");
		jdbcTemplate.update("DELETE FROM park");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('CVNP', 'Cuyahoga Valley National Park', 'Ohio', 32832, 696, 125, 0, 'Woodland', 2000, 2189849, 'Of all the paths you take in life, make sure a few of them are dirt.', 'John Muir', 'Though a short distance from the urban areas of Cleveland and Akron, Cuyahoga Valley National Park seems worlds away. The park is a refuge for native plants and wildlife, and provides routes of discovery for visitors. The winding Cuyahoga River gives way to deep forests, rolling hills, and open farmlands. Walk or ride the Towpath Trail to follow the historic route of the Ohio & Erie Canal', 0, 390);");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('ENP', 'Everglades National Park', 'Florida', 1508538, 0, 35, 0, 'Tropical', 1934, 1110901, 'There are no other Everglades in the world. They are, they have always been, one of the unique regions of the earth; remote, never wholly known. Nothing anywhere else is like them.', 'Marjory Stoneman Douglas', 'The Florida Everglades, located in southern Florida, is one of the largest wetlands in the world. Several hundred years ago, this wetlands was a major part of a 5,184,000 acre watershed that covered almost a third of the entire state of Florida. The Everglades consist of a shallow sheet of fresh water that rolls slowly over the lowlands and through billions of blades of sawgrass. As water moves through the Everglades, it causes the sawgrass to ripple like green waves; this is why the Everglades received the nickname \"River of Grass.\"', 8, 760);");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('GCNP', 'Grand Canyon National Park', 'Arizona', 1217262, 8000, 115, 120, 'Desert', 1919, 4756771, 'It is the one great wonders. . . every American should see.', 'Theodore Roosevelt', 'If there is any place on Earth that puts into perspective the grandiosity of Mother Nature, it is the Grand Canyon. The natural wonder, located in northern Arizona, is a window into the regio''s geological and Native American past. As one of the country''s first national parks, the Grand Canyon has long been considered a U.S. treasure, and continues to inspire scientific study and puzzlement.', 8, 450);");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('GNP', 'Glacier National Park', 'Montana', 1013322, 6646, 745.6, 923, 'Temperate', 1910, 2338528, 'Far away in northwestern Montana, hidden from view by clustering mountain peaks, lies an unmapped corner—the Crown of the Continent.', 'George Bird Grinnell', 'Glacier might very well be the most beautiful of America''s national parks. John Muir called it \"the best care-killing scenery on the continent.\" The mountains are steep, snowcapped, and punctuated by stunning mountain lakes and creeks. Much of the land remains wild and pristine, a result of its remote location and the lack of visitation in the 19th century.  ', 12, 300);");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('GSMNP', 'Great Smoky Mountains National Park', 'Tennessee', 522419, 6500, 850, 939, 'Temperate', 1934, 10099276, 'May your trails be crooked, winding, lonesome, dangerous, leading to the most amazing view. May your mountains rise into and above the clouds.', 'Edward Abbey', 'The Great Smoky Mountains are a mountain range rising along the Tennessee–North Carolina border in the southeastern United States. They are a subrange of the Appalachian Mountains, and form part of the Blue Ridge Physiographic Province. The range is sometimes called the Smoky Mountains and the name is commonly shortened to the Smokies. The Great Smokies are best known as the home of the Great Smoky Mountains National Park, which protects most of the range. The park was established in 1934, and, with over 9 million visits per year, it is the most-visited national park in the United States', 0, 400);");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('GTNP', 'Grand Teton National Park', 'Wyoming', 310000, 6800, 200, 1206, 'Temperate', 1929, 2791392, 'We can not have freedom without wilderness.', 'Edward Abbey', 'The peaks of the Teton Range, regal and imposing as they stand nearly 7,000 feet above the valley floor, make one of the boldest geologic statements in the Rockies. Unencumbered by foothills, they rise through steep coniferous forest into alpine meadows strewn with wildflowers, past blue and white glaciers to naked granite pinnacles. The Grand, Middle, and South Tetons form the heart of the range. But their neighbors, especially Mount Owen, Teewinot Mountain, and Mount Moran, are no less spectacular.', 15, 380);");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('MRNP', 'Mount Rainier National Park', 'Washingto', 236381, 5500, 260, 573, 'Rainforest', 1899, 1038229, 'Of all the fire mountains which like beacons, once blazed along the Pacific Coast, Mount Rainier is the noblest.', 'Unknow', 'Mt. Rainier National Park is one of three national parks in the state of Washington and is one of America''s oldest parks, being one of only five founded in the 19th century. The park was created to preserve one of America''s most spectacular scenic wonders, the snow-capped volcano known as Tahcoma to Indians in ages past and as Mt. Rainier now. While the mountain is unquestionably the centerpiece of the park, its 235,612 acres (378 square miles) also contain mountain ranges, elaborate glaciers, rivers, deep forests, lush meadows covered with wildflowers during the summer, and over 300 miles of trails. 96% of the park is classified as wilderness.', 20, 280);");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('RMNP', 'Rocky Mountain National Park', 'Colorado', 265761, 7800, 300, 660, 'Woodland', 1915, 3176941, 'It''s not the mountain we conquer, but ourselves.', 'Sir Edmund Hillary', 'Rocky Mountain National Park is one of the highest national parks in the nation, with elevations from 7,860 feet to 14,259 feet. Sixty mountain peaks over 12,000 feet high result in world-renowned scenery. The Continental Divide runs north - south through the park, and marks a climatic division. Ancient glaciers carved the topography into an amazing range of ecological zones. What you see within short distances at Rocky is similar to the wider landscape changes seen on a drive from Denver to northern Alaska.', 20, 360);");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('YNP', 'Yellowstone National Park', 'Wyoming', 2219791, 8000, 900, 1900, 'Temperate', 1872, 3394326, 'Yellowstone Park is no more representative of America than is Disneyland.', 'John Steinbeck', 'Yellowstone National Park is a protected area showcasing significant geological phenomena and processes. It is also a unique manifestation of geothermal forces, natural beauty, and wild ecosystems where rare and endangered species thrive. As the site of one of the few remaining intact large ecosystems in the northern temperate zone of earth, Yellowstone’s ecological communities provide unparalleled opportunities for conservation, study, and enjoyment of large-scale wildland ecosystem processes.', 15, 390);");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('YNP2', 'Yosemite National Park', 'California', 747956, 5000, 800, 1720, 'Forest', 1890, 3882642, 'Yosemite Valley, to me, is always a sunrise, a glitter of green and golden wonder in a vast edifice of stone and space.', 'Ansel Adams', 'Yosemite National Park vividly illustrates the effects of glacial erosion of granitic bedrock, creating geologic features that are unique in the world. Repeated glaciations over millions of years have resulted in a concentration of distinctive landscape features, including soaring cliffs, domes, and free-falling waterfalls. There is exceptional glaciated topography, including the spectacular Yosemite Valley, a 914-meter (1/2 mile) deep, glacier-carved cleft with massive sheer granite walls. These geologic features provide a scenic backdrop for mountain meadows and giant sequoia groves, resulting in a diverse landscape of exceptional natural and scenic beauty.', 15, 420);");
	}

	@After
	public void tearDown() throws Exception {
		dataSource.getConnection().rollback();
		
	}

	@Test
	public void testSizeOfGetAllParks() {
		assertEquals("Incorrect Park Size", 10, dao.getAllParks().size());
	}
	
	@Test
	public void testOneParkFromGetAllParks() {
		List<Park> parks = dao.getAllParks();
		Park onePark = null;
		for(Park park : parks) {
			if(park.getParkName().equals("Glacier National Park")) {
				onePark = park;
			}
		}
		assertEquals("GNP",onePark.getParkCode());
		assertEquals("Glacier National Park",onePark.getParkName());
		assertEquals("Montana",onePark.getState());
		assertEquals(1013322,(int)onePark.getAcreage());
		assertEquals(6646,(int)onePark.getElevationInFeet());
		assertEquals(745.6,(double)onePark.getMilesOfTrail(),.001);
		assertEquals(923,(int)onePark.getNumberOfCampsites());
		assertEquals("Temperate",onePark.getClimate());
		assertEquals(1910,(int)onePark.getYearFounded());
		assertEquals(2338528,(int)onePark.getAnnualVisitorCount());
		assertEquals("Far away in northwestern Montana, hidden from view by clustering mountain peaks, lies an unmapped corner—the Crown of the Continent.",onePark.getInspirationalQuote());
		assertEquals("George Bird Grinnell",onePark.getInspirationalQuoteSource());
		assertEquals("Glacier might very well be the most beautiful of America's national parks. John Muir called it \"the best care-killing scenery on the continent.\" The mountains are steep, snowcapped, and punctuated by stunning mountain lakes and creeks. Much of the land remains wild and pristine, a result of its remote location and the lack of visitation in the 19th century.  ",onePark.getParkDescription());
		assertEquals(new BigDecimal("12"),onePark.getEntryFee());
		assertEquals(300,(int)onePark.getNumberOfAnimalSpecies());
		
	}
	
	@Test
	public void testGetParkFromCode() {
		assertEquals("Wrong Park Pulled?","Great Smoky Mountains National Park", dao.getParkByCode("GSMNP").getParkName());
	}
	

}

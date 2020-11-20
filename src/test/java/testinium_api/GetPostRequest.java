package testinium_api;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;

public class GetPostRequest extends TestBase {
    @Test
    public void get01() {
        spec01.pathParams("driversId", "drivers", "alonso", "alonso",
                "constructorsId", "constructors", "renault", "renault", "season", "seasons");

        Response response = given().spec(spec01).when().get("/{driversId}/{alonso}/{constructorsId}/{renault}/{season}.json");
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
        JsonPath json = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("http://ergast.com/mrd/1.4", json.getString("MRData.xmlns"), "They are not equal");
        softAssert.assertEquals("f1", json.getString("MRData.series"), "They are not equal");
        softAssert.assertEquals("http://ergast.com/api/f1/drivers/alonso/constructors/renault/seasons.json", json.getString("MRData.url"), "They are not equal");
        softAssert.assertEquals("30", json.getString("MRData.limit"), "They are not equal");
        softAssert.assertEquals("0", json.getString("MRData.offset"), "They are not equal");
        softAssert.assertEquals("renault", json.getString("MRData.SeasonTable.constructorId"),"They are not equal");
        softAssert.assertEquals("alonso", json.getString("MRData.SeasonTable.driverId"),"They are not equal");
        //  softAssert.assertEquals("2003",json.getString("MRData.SeasonTable.Seasons[0].season"));
        List<Map> actualList = json.getList("MRData.SeasonTable.Seasons");
        System.out.println(actualList);
        Map<Integer, String> expectedMap = new HashMap<>();
        expectedMap.put(0, "2003");
        expectedMap.put(1, "2004");
        expectedMap.put(2, "2005");
        expectedMap.put(3, "2006");
        expectedMap.put(4, "2008");
        expectedMap.put(5, "2009");
        for (int i = 0; i < 6; i++) {
            softAssert.assertEquals(actualList.get(i).get("season"), expectedMap.get(i));
        }
        expectedMap.put(0, "https://en.wikipedia.org/wiki/2003_Formula_One_season");
        expectedMap.put(1, "https://en.wikipedia.org/wiki/2004_Formula_One_season");
        expectedMap.put(2, "https://en.wikipedia.org/wiki/2005_Formula_One_season");
        expectedMap.put(3, "https://en.wikipedia.org/wiki/2006_Formula_One_season");
        expectedMap.put(4, "https://en.wikipedia.org/wiki/2008_Formula_One_season");
        expectedMap.put(5, "https://en.wikipedia.org/wiki/2009_Formula_One_season");
        for (int i = 0; i < 6; i++) {
            softAssert.assertEquals(actualList.get(i).get("url"), expectedMap.get(i));
        }
    }
    @Test
    public void get02(){
        spec01.pathParams("driversId", "drivers", "alonso", "alonso",
                "driverStandings", "driverStandings", "status", "1", "season", "seasons");

        spec01.pathParams("driversId", "drivers", "alonso", "alonso", "driverStandings", "driverStandings", "status", "1", "season", "seasons");
        Response response = given().spec(spec01).when().get("/{driversId}/{alonso}/{driverStandings}/{status}/{season}.json");

        response.then().assertThat().statusCode(200);
        JSONObject expectedValues = new JSONObject();
        expectedValues.put("MRData.xmlns","http://ergast.com/mrd/1.4");
        expectedValues.put("MRData.series","f1");
        expectedValues.put("MRData.url","http://ergast.com/api/f1/drivers/alonso/driverstandings/1/seasons.json");
        expectedValues.put("MRData.limit","30");
        expectedValues.put("MRData.offset","0");
        expectedValues.put("MRData.total","2");
        expectedValues.put("MRData.SeasonTable.driverId","alonso");
        expectedValues.put("MRData.SeasonTable.driverStandings","1");
        expectedValues.put("MRData.SeasonTable.Seasons[0].season","2005");
        expectedValues.put("MRData.SeasonTable.Seasons[0].url","https://en.wikipedia.org/wiki/2005_Formula_One_season");
        expectedValues.put("MRData.SeasonTable.Seasons[1].season","2006");
        expectedValues.put("MRData.SeasonTable.Seasons[1].url","https://en.wikipedia.org/wiki/2006_Formula_One_season");

        JsonPath json = response.jsonPath();
        assertEquals(expectedValues.get("MRData.xmlns"),json.get("MRData.xmlns"));
        assertEquals(expectedValues.get("MRData.series"),json.get("MRData.series"));
        assertEquals(expectedValues.get("MRData.url"),json.get("MRData.url"));
        assertEquals(expectedValues.get("MRData.limit"),json.get("MRData.limit"));
        assertEquals(expectedValues.get("MRData.offset"),json.get("MRData.offset"));
        assertEquals(expectedValues.get("MRData.total"),json.get("MRData.total"));
        assertEquals(expectedValues.get("MRData.SeasonTable.driverId"),json.get("MRData.SeasonTable.driverId"));
        assertEquals(expectedValues.get("MRData.SeasonTable.driverStandings"),json.get("MRData.SeasonTable.driverStandings"));
        assertEquals(expectedValues.get("MRData.SeasonTable.Seasons[0].season"),json.get("MRData.SeasonTable.Seasons[0].season"));
        assertEquals(expectedValues.get("MRData.SeasonTable.Seasons[0].url"),json.get("MRData.SeasonTable.Seasons[0].url"));
        assertEquals(expectedValues.get("MRData.SeasonTable.Seasons[1].season"),json.get("MRData.SeasonTable.Seasons[1].season"));
        assertEquals(expectedValues.get("MRData.SeasonTable.Seasons[1].url"),json.get("MRData.SeasonTable.Seasons[1].url"));
    }

    @Test
    public void get03(){
          spec01.pathParams("season", "2014", "round", "5");
          Response response = given().spec(spec01).when().get("/{season}/{round}.json");
          response.then().assertThat().statusCode(200);
          JSONObject expectedValues = new JSONObject();
          expectedValues.put("MRData.xmlns","http://ergast.com/mrd/1.4");
          expectedValues.put("MRData.series","f1");
          expectedValues.put("MRData.url","http://ergast.com/api/f1/2014/5.json");
          expectedValues.put("MRData.limit","30");
          expectedValues.put("MRData.offset","0");
          expectedValues.put("MRData.total","1");
          expectedValues.put("MRData.RaceTable.season","2014");
          expectedValues.put("MRData.RaceTable.round","5");
          expectedValues.put("MRData.RaceTable.Races[0].season","2014");
          expectedValues.put("MRData.RaceTable.Races[0].round","5");
          expectedValues.put("MRData.RaceTable.Races[0].url","http://en.wikipedia.org/wiki/2014_Spanish_Grand_Prix");
          expectedValues.put("MRData.RaceTable.Races[0].raceName","Spanish Grand Prix");
          expectedValues.put("MRData.RaceTable.Races[0].Circuit.circuitId","catalunya");
          expectedValues.put("MRData.RaceTable.Races[0].Circuit.url","http://en.wikipedia.org/wiki/Circuit_de_Barcelona-Catalunya");
          expectedValues.put("MRData.RaceTable.Races[0].Circuit.circuitName","Circuit de Barcelona-Catalunya");
          expectedValues.put("MRData.RaceTable.Races[0].Circuit.Location.lat","41.57");
          expectedValues.put("MRData.RaceTable.Races[0].Circuit.Location.long","2.26111");
          expectedValues.put("MRData.RaceTable.Races[0].Circuit.Location.locality","Montmeló");
          expectedValues.put("MRData.RaceTable.Races[0].Circuit.Location.country","Spain");
          expectedValues.put("MRData.RaceTable.Races[1].date","2014-05-11");
          expectedValues.put("MRData.RaceTable.Races[1].time","12:00:00Z");

          JsonPath json = response.jsonPath();
          SoftAssert softAssert = new SoftAssert();
          softAssert.assertEquals(expectedValues.get("MRData.xmlns"),json.get("MRData.xmlns"));
          softAssert.assertEquals(expectedValues.get("MRData.series"),json.get("MRData.series"));
          softAssert.assertEquals(expectedValues.get("MRData.url"),json.get("MRData.url"));
          softAssert.assertEquals(expectedValues.get("MRData.limit"),json.get("MRData.limit"));
          softAssert.assertEquals(expectedValues.get("MRData.offset"),json.get("MRData.offset"));
          softAssert.assertEquals(expectedValues.get("MRData.total"),json.get("MRData.total"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.season"),json.get("MRData.RaceTable.season"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.round"),json.get("MRData.RaceTable.round"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].season"),json.get("MRData.RaceTable.Races[0].season"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].round"),json.get("MRData.RaceTable.Races[0].round"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].url"),json.get("MRData.RaceTable.Races[0].url"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].raceName"),json.get("MRData.RaceTable.Races[0].raceName"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.circuitId"),json.get("MRData.RaceTable.Races[0].Circuit.circuitId"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.url"),json.get("MRData.RaceTable.Races[0].Circuit.url"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.circuitName"),json.get("MRData.RaceTable.Races[0].Circuit.circuitName"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.Location.lat"),json.get("MRData.RaceTable.Races[0].Circuit.Location.lat"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.Location.long"),json.get("MRData.RaceTable.Races[0].Circuit.Location.long"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.Location.locality"),json.get("MRData.RaceTable.Races[0].Circuit.Location.locality"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.Location.country"),json.get("MRData.RaceTable.Races[0].Circuit.Location.country"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[1].date"),json.get("MRData.RaceTable.Races[0].date"));
          softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[1].time"),json.get("MRData.RaceTable.Races[0].time"));
          softAssert.assertAll();
      }

    @Test
    public void post01(){
          spec01.pathParams("driversId", "drivers", "alonso", "alonso",
                  "driverStandings", "driverStandings", "status", "1", "season", "seasons");
          Seasons seasons = new Seasons("2005","https://en.wikipedia.org/wiki/2005_Formula_One_season","2006","https://en.wikipedia.org/wiki/2006_Formula_One_season");
          SeasonTable seasonTable = new SeasonTable("1","alonso");
          MRData mrData = new MRData("http://ergast.com/mrd/1.4","f1","http://ergast.com/api/f1/drivers/alonso/driverstandings/1/seasons.json","30",
                  "0","2");
          Response response = given().contentType(ContentType.JSON).spec(spec01).body(mrData).body(seasonTable).body(seasons).when().post("/{driversId}/{alonso}/{driverStandings}/{status}/{season}.json");
          response.then().assertThat().statusCode(200).body("MRData.xmlns",equalTo(mrData.getXmlns()),
                  "MRData.series",equalTo(mrData.getSeries()),"MRData.url",equalTo(mrData.getUrl()),
                  "MRData.limit",equalTo(mrData.getLimit()),"MRData.offset",equalTo(mrData.getOffset()),
                  "MRData.total",equalTo(mrData.getTotal()),"MRData.SeasonTable.driverId",equalTo(seasonTable.getDriverId()),
                  "MRData.SeasonTable.driverStandings",equalTo(seasonTable.getDriverStandings()),"MRData.SeasonTable.Seasons[0].season",equalTo(seasons.getSeason1()),
                  "MRData.SeasonTable.Seasons[0].url",equalTo(seasons.getUrl1()),"MRData.SeasonTable.Seasons[1].season",equalTo(seasons.getSeason2()),
                  "MRData.SeasonTable.Seasons[1].url",equalTo(seasons.getUrl2()));
    }

    @Test
    public void get04(){
        Response response = given().spec(spec02).when().get();
        response.then().assertThat().statusCode(200);
        JSONObject expectedValues = new JSONObject();
        expectedValues.put("MRData.xmlns","http://ergast.com/mrd/1.4");
        expectedValues.put("MRData.series","f1");
        expectedValues.put("MRData.url","http://ergast.com/api/f1/2008/5.json");
        expectedValues.put("MRData.limit","30");
        expectedValues.put("MRData.offset","0");
        expectedValues.put("MRData.total","1");
        expectedValues.put("MRData.RaceTable.season","2008");
        expectedValues.put("MRData.RaceTable.round","5");
        expectedValues.put("MRData.RaceTable.Races[0].season","2008");
        expectedValues.put("MRData.RaceTable.Races[0].round","5");
        expectedValues.put("MRData.RaceTable.Races[0].url","http://en.wikipedia.org/wiki/2008_Turkish_Grand_Prix");
        expectedValues.put("MRData.RaceTable.Races[0].raceName","Turkish Grand Prix");
        expectedValues.put("MRData.RaceTable.Races[0].Circuit.circuitId","istanbul");
        expectedValues.put("MRData.RaceTable.Races[0].Circuit.url","http://en.wikipedia.org/wiki/Istanbul_Park");
        expectedValues.put("MRData.RaceTable.Races[0].Circuit.circuitName","Istanbul Park");
        expectedValues.put("MRData.RaceTable.Races[0].Circuit.Location.lat","40.9517");
        expectedValues.put("MRData.RaceTable.Races[0].Circuit.Location.long","29.405");
        expectedValues.put("MRData.RaceTable.Races[0].Circuit.Location.locality","Istanbul");
        expectedValues.put("MRData.RaceTable.Races[0].Circuit.Location.country","Turkey");
        expectedValues.put("MRData.RaceTable.Races[0].date","2008-05-11");
        expectedValues.put("MRData.RaceTable.Races[0].time","12:00:00Z");

        JsonPath json = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedValues.get("MRData.xmlns"),json.get("MRData.xmlns"));
        softAssert.assertEquals(expectedValues.get("MRData.series"),json.get("MRData.series"));
        softAssert.assertEquals(expectedValues.get("MRData.url"),json.get("MRData.url"));
        softAssert.assertEquals(expectedValues.get("MRData.limit"),json.get("MRData.limit"));
        softAssert.assertEquals(expectedValues.get("MRData.offset"),json.get("MRData.offset"));
        softAssert.assertEquals(expectedValues.get("MRData.total"),json.get("MRData.total"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].season"),json.get("MRData.RaceTable.Races[0].season"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].round"),json.get("MRData.RaceTable.Races[0].round"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].url"),json.get("MRData.RaceTable.Races[0].url"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].raceName"),json.get("MRData.RaceTable.Races[0].raceName"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.circuitId"),json.get("MRData.RaceTable.Races[0].Circuit.circuitId"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.url"),json.get("MRData.RaceTable.Races[0].Circuit.url"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.circuitName"),json.get("MRData.RaceTable.Races[0].Circuit.circuitName"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.Location.lat"),json.get("MRData.RaceTable.Races[0].Circuit.Location.lat"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.Location.long"),json.get("MRData.RaceTable.Races[0].Circuit.Location.long"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.Location.locality"),json.get("MRData.RaceTable.Races[0].Circuit.Location.locality"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].Circuit.Location.country"),json.get("MRData.RaceTable.Races[0].Circuit.Location.country"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].date"),json.get("MRData.RaceTable.Races[0].date"));
        softAssert.assertEquals(expectedValues.get("MRData.RaceTable.Races[0].time"),json.get("MRData.RaceTable.Races[0].time"));
        softAssert.assertAll();
    }

   @Test
   public void get05(){
        spec01.pathParams("circuitId", "circuits", "circuitName", "Istanbul");
        Response response = given().spec(spec01).when().get("/{circuitId}/{circuitName}.json");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        JSONObject expectedValues = new JSONObject();
        expectedValues.put("MRData.xmlns","http://ergast.com/mrd/1.4");
        expectedValues.put("MRData.series","f1");
        expectedValues.put("MRData.url","http://ergast.com/api/f1/circuits/istanbul.json");
        expectedValues.put("MRData.limit","30");
        expectedValues.put("MRData.offset","0");
        expectedValues.put("MRData.total","1");
        expectedValues.put("MRData.CircuitTable.circuitId","istanbul");
        expectedValues.put("MRData.CircuitTable.Circuits[0].circuitId","istanbul");
        expectedValues.put("MRData.CircuitTable.Circuits[0].url","http://en.wikipedia.org/wiki/Istanbul_Park");
        expectedValues.put("MRData.CircuitTable.Circuits[0].Location.lat","40.9517");
        expectedValues.put("MRData.CircuitTable.Circuits[0].Location.long","29.405");
        expectedValues.put("MRData.CircuitTable.Circuits[0].Location.locality","Istanbul");
        expectedValues.put("MRData.CircuitTable.Circuits[0].Location.country","Turkey");
        response.then().assertThat().statusCode(200);
        JsonPath json = response.jsonPath();
        assertEquals(expectedValues.get("MRData.xmlns"),json.get("MRData.xmlns"));
        assertEquals(expectedValues.get("MRData.series"),json.get("MRData.series"));
        assertEquals(expectedValues.get("MRData.url"),json.get("MRData.url"));
        assertEquals(expectedValues.get("MRData.limit"),json.get("MRData.limit"));
        assertEquals(expectedValues.get("MRData.offset"),json.get("MRData.offset"));
        assertEquals(expectedValues.get("MRData.total"),json.get("MRData.total"));
        assertEquals(expectedValues.get("MRData.CircuitTable.circuitId"),json.get("MRData.CircuitTable.circuitId"));
        assertEquals(expectedValues.get("MRData.CircuitTable.Circuits[0].circuitId"),json.get("MRData.CircuitTable.Circuits[0].circuitId"));
        assertEquals(expectedValues.get("MRData.CircuitTable.Circuits[0].url"),json.get("MRData.CircuitTable.Circuits[0].url"));
        assertEquals(expectedValues.get("MRData.CircuitTable.Circuits[0].Location.lat"),json.get("MRData.CircuitTable.Circuits[0].Location.lat"));
        assertEquals(expectedValues.get("MRData.CircuitTable.Circuits[0].Location.long"),json.get("MRData.CircuitTable.Circuits[0].Location.long"));
        assertEquals(expectedValues.get("MRData.CircuitTable.Circuits[0].Location.locality"),json.get("MRData.CircuitTable.Circuits[0].Location.locality"));
        assertEquals(expectedValues.get("MRData.CircuitTable.Circuits[0].Location.country"),json.get("MRData.CircuitTable.Circuits[0].Location.country"));
   }
}
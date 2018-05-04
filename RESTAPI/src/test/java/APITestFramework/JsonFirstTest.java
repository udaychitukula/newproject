package APITestFramework;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import files.ApiPayLoad;
import files.ApiResources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.hamcrest.core.Is;

public class JsonFirstTest {
	
	public String placeID;
	Properties prop;
	FileInputStream fis;
	
  @BeforeSuite
  public void setup() throws IOException
  {
	   prop = new Properties();
	   fis = new FileInputStream("C:\\Users\\uchitukula\\git\\newproject\\RESTAPI\\src\\main\\java\\files\\env.properties");
	  prop.load(fis);
	  prop.getProperty("HOST");
	  
  }
  @Test(priority=0)
  public void validateGet() {
	  
	  RestAssured.baseURI=prop.getProperty("HOST");
	  Response res=given().
	  		param("location","-33.8670522,151.1957362").
	  		param("radius","1500").
	  		param("key",prop.getProperty("KEY")).
	  		when()
	  		.get("/maps/api/place/nearbysearch/json")
	  		.then()
	  		.assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
	  		.body("results[0].geometry.location.lat",is(not(equalTo("-33.8688197")))).and()
	  		.body("results[0].name",equalTo("Sydney")).and()
	  		.body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"))
	  		.header("Server", "scaffolding on HTTPServer2")
	  		.extract().response();
	  		String resStr=res.asString();
	  		//System.out.println(resStr);
	  		JsonPath js=new JsonPath(resStr);
	  		//placeID=js.get("results[0].place_id");
	  		//System.out.println(placeID);
  }
  
  @Test(priority=1)
  public void postTest()
  {
	  
	  RestAssured.baseURI=prop.getProperty("HOST");
	  Response res=given().
	  queryParam("key",prop.getProperty("KEY"))
	  .body(ApiPayLoad.getAddPlaceBodyJson())
	  .when()
	  .post(ApiResources.addPlaceResJson())
	  .then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
	  .and()
	  .body("status", equalTo("OK"))
	  .extract().response();
		String resStr=res.asString();
		System.out.println(resStr);
		JsonPath js=new JsonPath(resStr);
		
  		placeID=js.get("place_id");
  		System.out.println(placeID);
	  
  }
  
  @Test(priority=2)
  public void deleteTest()
  {
	  
	  RestAssured.baseURI=prop.getProperty("HOST");
	  Response res=given().
	  queryParam("key",prop.getProperty("KEY"))
	  .body(ApiPayLoad.getDeletePlaceBodyJson(placeID))
	  .when().post(ApiResources.deletePlaceResJson())
	  .then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
	  .and()
	  .body("status", equalTo("OK"))
	  .extract().response();
	  String resStr=res.asString();
	  JsonPath js=new JsonPath(resStr);
		
		placeID=js.get("status");
		System.out.println(placeID);
  }
  
  
}

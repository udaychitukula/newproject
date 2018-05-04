package APITestFramework;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import files.ApiPayLoad;
import files.ApiResources;
import files.ReusableCode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.hamcrest.core.Is;

public class TestCase_002 {
	
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
	  		param("key",prop.getProperty("KEY"))
	  		.log().all().							//logs request parameters(All)
	  		when()
	  		.get("/maps/api/place/nearbysearch/json")
	  		.then().log().headers()					//logs response header parameters
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
	  		int count=js.get("results.size()");
	  		for(int i=0; i<count; i++)
	  		{
	  			System.out.println((i+1)+" : "+js.get("results["+i+"].name"));
	  		}
	  		
  }
  
  
  
}

package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableCode {
	
	public static XmlPath parseResponseToXml(Response rs)
	{
		String response=rs.asString();
		XmlPath xp=new XmlPath(response);
		return xp;
	}
	public static JsonPath parseResponseToJson(Response rs)
	{
		String response=rs.asString();
		JsonPath jp=new JsonPath(response);
		return jp;
	}

}

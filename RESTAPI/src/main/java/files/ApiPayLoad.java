package files;

public class ApiPayLoad {
	
	public static String getAddPlaceBodyJson()
	{
		String b="{\n" + 
		  		"  \"location\": {\n" + 
		  		"    \"lat\": -33.8669710,\n" + 
		  		"    \"lng\": 151.1958750\n" + 
		  		"  },\n" + 
		  		"  \"accuracy\": 50,\n" + 
		  		"  \"name\": \"Google Shoes!\",\n" + 
		  		"  \"phone_number\": \"(02) 9374 4000\",\n" + 
		  		"  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\n" + 
		  		"  \"types\": [\"shoe_store\"],\n" + 
		  		"  \"website\": \"http://www.google.com.au/\",\n" + 
		  		"  \"language\": \"en-AU\"\n" + 
		  		"}";
		
		return b;
	}
	public static String getDeletePlaceBodyJson(String placeID)
	{
		String b="{\n" + 
		  		"  \"place_id\": \"  "+placeID+"  \"  \n" + 
		  		"}";
		return b;
	}
	
	public static String getAddPlaceBodyXml()
	{
		String b="<PlaceAddRequest>\r\n" + 
				"  <location>\r\n" + 
				"    <lat>-33.8669710</lat>\r\n" + 
				"    <lng>151.1958750</lng>\r\n" + 
				"  </location>\r\n" + 
				"  <accuracy>50</accuracy>\r\n" + 
				"  <name>Google Shoes!</name>\r\n" + 
				"  <phone_number>(02) 9374 4000</phone_number>\r\n" + 
				"  <address>48 Pirrama Road, Pyrmont, NSW 2009, Australia</address>\r\n" + 
				"  <type>shoe_store</type>\r\n" + 
				"  <website>http://www.google.com.au/</website>\r\n" + 
				"  <language>en-AU</language>\r\n" + 
				"</PlaceAddRequest>";
		
		return b;
	}
	public static String getDeletePlaceBodyXml(String placeID)
	{
		String b="<PlaceDeleteRequest>\r\n" + 
				"  <place_id>"+placeID+"</place_id>\r\n" + 
				"</PlaceDeleteRequest>";
		return b;
	}
}

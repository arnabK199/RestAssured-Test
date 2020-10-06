package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CommonMethods {
	
	public static JsonPath JsonParser(Response r) {
		
		JsonPath jPath = new JsonPath(r.asString());
		return jPath;
	}

}

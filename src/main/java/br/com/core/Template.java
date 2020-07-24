package br.com.core;

import java.io.FileReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Template {

	
	public JsonObject montarJson(String pipeline) throws Exception {
		
		
		
		JsonParser parser = new JsonParser();
		
		
		Object obj = parser.parse(new FileReader("files\\"+pipeline+".json"));
		JsonObject js = (JsonObject) obj;
		
		
		
		if(pipeline == "outro") {
			js.remove("nome");
		}
		
		
		return js;
		
	}
	
	
	
	
}

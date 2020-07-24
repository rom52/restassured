package br.com.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.core.BaseTests;
import io.restassured.internal.util.IOUtils;

public class BarrigaTest extends BaseTests{
	
	br.com.core.Template tem = new br.com.core.Template();
	private String TOKEN = "";
	
	@Before
	public void  login() {
		Map<String, String> login = new HashMap<String, String>();
		login.put("email", "wagner@aquino");
		login.put("senha", "123456");
		
		TOKEN =
		given()
			.body(login)
		.when()
			.post("/signin")
		.then()
			.statusCode(200)
			.extract().path("token");
		
		//System.out.println("token: " + TOKEN);
		;		
	}
	
	

	
	@Test
	public void temp() throws Exception {
		
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\Romulo Negri\\eclipse-workspace\\POC\\files\\temp.json"));
		JsonObject js = tem.montarJson("temp");
		js.addProperty("nome", "trocou"+ UUID.randomUUID());
		//js.remove("estado");
		//js.addProperty("pais", "brasil");
		
		
		given()
			.header("Authorization", "JWT "+ TOKEN)
			.body(js)
		.when()
			.post("/contas")
		.then()
			.statusCode(201)
			;
		}
	
	@Test
	public void outro() throws Exception {
		
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\Romulo Negri\\eclipse-workspace\\POC\\files\\temp.json"));
		
		given()
			.header("Authorization", "JWT "+ TOKEN)
			.body(tem.montarJson("outro"))
			//.body("{\"nome\": \"testesrom25\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(400)
			;
		}
	
	@Test
	public void TestdeveAlterarContaComSucesso() {
		
		given()
			.header("Authorization", "JWT "+ TOKEN)
			.body("{\"nome\": \"testesrom25 alterada\"}")
		.when()
			.put("/contas/138708")
		.then()
			.statusCode(404)
			//.body("nome", is("testesrom25 alterada"))
			;
		}
	
	
	
}

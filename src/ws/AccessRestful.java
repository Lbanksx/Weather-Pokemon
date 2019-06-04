package ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;

import model.PokeAPI;
import model.WeatherMap;

public class AccessRestful {
	
	String city;
	String pokeElementType;
	
	public AccessRestful() {
	}
	
	public WeatherMap OpenWeatherMap(String city) throws Exception {
		
		String urlBase = "http://api.openweathermap.org/data/2.5/weather?q=";
		String APIKey = "2576335f357a0851e239fccdae8e3a66";
		
		//Substitui os espaços da cidade na url
		String convertCity = java.net.URLEncoder.encode(city, "UTF-8");
		
		//Monta a url com a cidade e código
		String consultingURL = urlBase + convertCity + "&units=metric" + "&APPID=" + APIKey;
		
		//pega a url e abre uma conexão
		URL obj = new URL(consultingURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Define o método
        con.setRequestMethod("GET");
        
        //Armazena o resultado em buffer, lê e monta em uma string
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String Line;
        StringBuffer rest = new StringBuffer();

        while ((Line = in.readLine()) != null) {
        	rest.append(Line);
        }
        in.close();
        
        Gson gson = new Gson();
        
      //Converte o JSON para um tipo Objeto
		JSONObject jsonObject = new JSONObject(rest.toString());
		
		//Pega somente os atributos do "weather" e do "main"
		JSONArray obj1 = jsonObject.getJSONArray("weather");
		Object obj2 = jsonObject.get("main");
		
		//Pega os atributos específicos e converte em String retirando os "[]" e "{}"
		String subJson1 = String.valueOf(obj1);
		subJson1 = subJson1.substring(subJson1.indexOf('[') + 1, subJson1.lastIndexOf(']')).substring(subJson1.indexOf('{') + 1, subJson1.lastIndexOf('}') - 1);
		
		String subJson2 = String.valueOf(obj2);
		subJson2 = subJson2.substring(subJson2.indexOf('{') + 1, subJson2.lastIndexOf('}'));
		
		//Reestrutura o Json só com os atributos específicos e retorna para o Controller
		String reformRest = "{" + subJson1 + "," + subJson2 + "}";
		
		WeatherMap wm = new WeatherMap();
		wm = gson.fromJson(reformRest, WeatherMap.class);
		
		return wm;
		
	}

	public List<PokeAPI> pokeAPI(String type) throws Exception {
		
		String urlBase = "https://pokeapi.co/api/v2/type/";
		
		//pega a url e abre uma conexão
		URL obj = new URL(urlBase + type);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Define o método
        con.setRequestMethod("GET");
        
        //Define e propriedade
        con.setRequestProperty("User-Agent","cheese");
        
        //Armazena o resultado em buffer, lê e monta em uma string
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String Line;
        StringBuffer rest = new StringBuffer();

        while ((Line = in.readLine()) != null) {
        	rest.append(Line);
        }
        in.close();
        
        List<PokeAPI> list = new ArrayList<>();
        
        //Converte o JSON para um tipo Objeto
		JSONObject jsonObject = new JSONObject(rest.toString());
		
		//Pega somente os atributos do "pokemon"
		JSONArray obj1 = jsonObject.getJSONArray("pokemon");
		
		//Converte o Objeto em JSON somente com os pokemons e adiciona em uma lista
		for(int i = 0; i < obj1.length(); i++) {
			
			JSONObject e = obj1.getJSONObject(i);
			String pokeList = e.getString("pokemon");
			
			JSONObject reform = new JSONObject(pokeList);
			PokeAPI pa = new PokeAPI();
			
			pa.setName(reform.getString("name"));
			pa.setUrl(reform.getString("url"));
			list.add(pa);
		}
		
		return list;
	}
}

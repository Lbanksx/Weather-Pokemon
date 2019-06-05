package entities;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PokeAPI;
import model.WeatherMap;
import ws.AccessRestful;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DecimalFormat df = new DecimalFormat("#,###");
		
		String city = request.getParameter("city");
		
		AccessRestful rest = new AccessRestful();
		
		try {
			
			WeatherMap resultWeather = rest.OpenWeatherMap(city);
			
			//Realiza todos os testes e retorna um pokemon aleatório e manda para o index.jsp
			if(resultWeather.getMain().equals("Rain")) {
				
				ArrayList<PokeAPI> electricList = new ArrayList<>(rest.pokeAPI("electric"));
				Random random = new Random();
				request.setAttribute("temp", df.format(resultWeather.getTemp()) + "°C");
				request.setAttribute("rain", "Sim");
				request.setAttribute("pokemon", electricList.get(random.nextInt(electricList.size())).getName());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			else if(resultWeather.getTemp() >= 10 & resultWeather.getTemp() < 12 || resultWeather.getTemp() >= 21 & resultWeather.getTemp() < 23) {
				
				ArrayList<PokeAPI> normalList = new ArrayList<>(rest.pokeAPI("normal"));
				Random random = new Random();
				request.setAttribute("temp", df.format(resultWeather.getTemp()) + "°C");
				request.setAttribute("rain", "Não");
				request.setAttribute("pokemon", normalList.get(random.nextInt(normalList.size())).getName());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			else if(resultWeather.getTemp() < 5) {
				
				ArrayList<PokeAPI> iceList = new ArrayList<>(rest.pokeAPI("ice"));
				Random random = new Random();
				request.setAttribute("temp", df.format(resultWeather.getTemp()) + "°C");
				request.setAttribute("rain", "Não");
				request.setAttribute("pokemon", iceList.get(random.nextInt(iceList.size())).getName());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			else if(resultWeather.getTemp() >= 5 & resultWeather.getTemp() < 10) {
				
				ArrayList<PokeAPI> waterList = new ArrayList<>(rest.pokeAPI("water"));
				Random random = new Random();
				request.setAttribute("temp", df.format(resultWeather.getTemp()) + "°C");
				request.setAttribute("rain", "Não");
				request.setAttribute("pokemon", waterList.get(random.nextInt(waterList.size())).getName());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			else if(resultWeather.getTemp() >= 12 & resultWeather.getTemp() < 15) {
				
				ArrayList<PokeAPI> grassList = new ArrayList<>(rest.pokeAPI("grass"));
				Random random = new Random();
				request.setAttribute("temp", df.format(resultWeather.getTemp()) + "°C");
				request.setAttribute("rain", "Não");
				request.setAttribute("pokemon", grassList.get(random.nextInt(grassList.size())).getName());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			else if(resultWeather.getTemp() >= 15 & resultWeather.getTemp() < 21) {
				
				ArrayList<PokeAPI> groundList = new ArrayList<>(rest.pokeAPI("ground"));
				Random random = new Random();
				request.setAttribute("temp", df.format(resultWeather.getTemp()) + "°C");
				request.setAttribute("rain", "Não");
				request.setAttribute("pokemon", groundList.get(random.nextInt(groundList.size())).getName());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			else if(resultWeather.getTemp() >= 23 & resultWeather.getTemp() < 27) {
				
				ArrayList<PokeAPI> bugList = new ArrayList<>(rest.pokeAPI("bug"));
				Random random = new Random();
				request.setAttribute("temp", df.format(resultWeather.getTemp()) + "°C");
				request.setAttribute("rain", "Não");
				request.setAttribute("pokemon", bugList.get(random.nextInt(bugList.size())).getName());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			else if(resultWeather.getTemp() >= 27 & resultWeather.getTemp() <= 33) {
				
				ArrayList<PokeAPI> rockList = new ArrayList<>(rest.pokeAPI("rock"));
				Random random = new Random();
				request.setAttribute("temp", df.format(resultWeather.getTemp()) + "°C");
				request.setAttribute("rain", "Não");
				request.setAttribute("pokemon", rockList.get(random.nextInt(rockList.size())).getName());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			else {
				
				ArrayList<PokeAPI> fireList = new ArrayList<>(rest.pokeAPI("fire"));
				Random random = new Random();
				request.setAttribute("temp", df.format(resultWeather.getTemp()) + "°C");
				request.setAttribute("rain", "Não");
				request.setAttribute("pokemon", fireList.get(random.nextInt(fireList.size())).getName());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
	        
		} catch (Exception e) {
			request.setAttribute("erro", "Cidade não encontrada, verifique se foi digitado corretamente.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

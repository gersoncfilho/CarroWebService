package br.com.livro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.domain.ListaCarros;
import br.com.livro.util.JAXBUtil;
import br.com.livro.util.ServletUtil;






@WebServlet ("/carros/*")
public class CarrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarroService carroservice = new CarroService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Carro> carros = carroservice.getCarros();
		ListaCarros lista = new ListaCarros();
		lista.setCarros(carros);
		
//		//Gera o xml
//		String xml = JAXBUtil.toXML(lista);
//		//Escreve o xml no response do servlet com application/xml
//		ServletUtil.writeXML(resp, xml);
		
		//Gera o json
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(lista);
		//Escreve o JSON no response do servlet com application/json
		ServletUtil.writeJSON(resp, json);		
	}
	
}

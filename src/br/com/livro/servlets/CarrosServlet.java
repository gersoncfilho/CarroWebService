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
import br.com.livro.util.RegexUtil;
import br.com.livro.util.ServletUtil;

@WebServlet ("/carros/*")
public class CarrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarroService carroservice = new CarroService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		
		if(id!=null){
			//Informou o id
			Carro carro = carroservice.getCarro(id);
			if (carro!=null){
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(carro);
				ServletUtil.writeJSON(resp, json);
			}else{
				resp.sendError(404,"Carro não encontrado");
			}
		}else{
			//Lista de carros
			List<Carro> carros = carroservice.getCarros();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(carros);
			ServletUtil.writeJSON(resp, json);	
		}
	
//		//Gera o xml
//		String xml = JAXBUtil.toXML(lista);
//		//Escreve o xml no response do servlet com application/xml
//		ServletUtil.writeXML(resp, xml);
//		
//		//Gera o json
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String json = gson.toJson(lista);
//		//Escreve o JSON no response do servlet com application/json
//		ServletUtil.writeJSON(resp, json);		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Cria carro
		Carro carro = getCarroFromRequest(req);
		//Salva carro
		carroservice.save(carro);
		//Escreve o JSON do novo carro
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(carro);
		ServletUtil.writeJSON(resp, json);	
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		if(id!=null){
			carroservice.delete(id);
			resp.sendError(200,"Carro excluído com sucesso!");
		}else{
			resp.sendError(404,"URL inválida!");
		}
	}
	
	//Le os parametros da request e cria o objeto carro
	private Carro getCarroFromRequest(HttpServletRequest request){
		Carro c = new Carro();
		String id = request.getParameter("id");
		if(id!=null){
			//Se informou o id, busca o objeto do banco de dados
			c = carroservice.getCarro(Long.parseLong(id));
		}
		c.setNome(request.getParameter("nome"));
		c.setDesc(request.getParameter("descricao"));
		c.setUrlFoto(request.getParameter("url_foto"));
		c.setUrlVideo(request.getParameter("url_video"));
		c.setLatitude(request.getParameter("latitude"));
		c.setLongitude(request.getParameter("longitude"));
		c.setTipo(request.getParameter("tipo"));
		return c;
	}
	
	
	
	
}

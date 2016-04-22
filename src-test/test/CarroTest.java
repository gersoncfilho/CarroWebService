package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.mysql.jdbc.log.Log;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import junit.framework.TestCase;

public class CarroTest extends TestCase {

	private CarroService carroService = new CarroService();
	
	@Test
	public void testListaCarros() {
		List<Carro> carros = carroService.getCarros();
		assertNotNull(carros);
		//Valida se encontrou algo
		assertTrue(carros.size()>0);
		//Valida se encontrou o Tucker
		Carro tucker = carroService.findByName("Tucker 1948").get(0);
		assertEquals("Tucker 1948", tucker.getNome());
		//Valida se encontrou ferrari
		Carro ferrari = carroService.findByName("Ferrari FF").get(0);
		assertEquals("Ferrari FF", ferrari.getNome());
		//Valida se encontrou Bugatti
		Carro bugatti = carroService.findByName("Bugatti Veyron").get(0);
		assertEquals("Bugatti Veyron", bugatti.getNome());
	}
	
	public void salvarDeletarCarro(){
		Carro c = new Carro();
		c.setNome("Teste");
		c.setDesc("Teste desc");
		c.setUrlFoto("Teste url_foto");
		c.setUrlVideo("Teste url_video");
		c.setLatitude("Teste latitude");
		c.setLongitude("Teste longitude");
		c.setTipo("Teste tipo");
		carroService.save(c);
		//Id do carro salvo
		Long id = c.getId();
		assertNotNull(id);
		//Busca no banco de dados para confirmar que o carro foi salvo
		c = carroService.getCarro(id);
		assertEquals("Teste", c.getNome());
		assertEquals("Teste desc", c.getDesc());
		assertEquals("Teste url_foto", c.getUrlFoto());
		assertEquals("Teste url_video", c.getUrlVideo());
		assertEquals("Teste latitude", c.getLatitude());
		assertEquals("Teste longitude", c.getLongitude());
		assertEquals("Teste tipo", c.getTipo());
		//Atualiza o carro
		c.setNome("Teste UPDATE");
		carroService.save(c);
		//Busca carro novamente
		c = carroService.getCarro(id);
		assertEquals("Teste UPDATE", c.getNome());
		//Deleta carro
		carroService.delete(id);
		//Busca carro novamente
		c = carroService.getCarro(id);
		//Desta vez o carro não existe mais
		assertNull(c);
		
		
		
	}
}

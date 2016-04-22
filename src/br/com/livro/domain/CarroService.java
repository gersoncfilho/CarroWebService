package br.com.livro.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroService {
	private CarroDAO db = new CarroDAO();
	//Lista todos os carros do banco de dados
	public List<Carro> getCarros(){
		try{
			List<Carro> carros = db.getCarros();
			return carros;
		}catch(SQLException ex){
			ex.printStackTrace();
			return new ArrayList<Carro>();
		}
	}
	
	//Busca carro pelo id
	public Carro getCarro(Long id){
		try{
			return db.getCarroById(id);
		}catch(SQLException ex){
			return null;
		}
	}
	
	//Deleta carro por id
	public boolean delete(Long id){
		try{
			return db.delete(id);
		}catch(SQLException ex){
			return false;
		}
	}
	
	//Salva ou atualiza carro
	public boolean save(Carro carro){
		try{
			db.save(carro);
			return true;
		}catch(SQLException ex){
			return false;
		}
	}
	
	//Busca carro pelo nome
	public List<Carro> findByName(String name){
		try{
			return db.findByName(name);
		}catch(SQLException ex){
			return null;
		}
	}
	
	public List<Carro> findByTipo(String tipo){
		try{
			return db.findByTipo(tipo);
		}catch(SQLException ex){
			return null;
		}
	}
}

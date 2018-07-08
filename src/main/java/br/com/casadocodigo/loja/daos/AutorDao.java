package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.casadocodigo.loja.models.Autor;

public class AutorDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Autor> listar() {
		String jpql = "select a from Autor a order by a.nome";
		
		TypedQuery<Autor> query = manager.createQuery(jpql, Autor.class);
		List<Autor> autores = query.getResultList();
		
		return autores;
	}

}

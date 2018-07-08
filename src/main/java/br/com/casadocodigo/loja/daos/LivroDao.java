package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import br.com.casadocodigo.loja.models.Livro;

@Stateful
public class LivroDao {
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	public void salvar(Livro livro) {
		this.manager.persist(livro);
	}

	public List<Livro> listar() {
		String jpql = "select distinct(l) from Livro l"
				+ " join fetch l.autores"; 
		TypedQuery<Livro> query = this.manager.createQuery(jpql, Livro.class);
		List<Livro> livros = query.getResultList();
		return livros;
	}

	public List<Livro> ultimosLancamentos() {
		String jpql = "select l from Livro l order by l.id desc";
		return this.manager.createQuery(jpql, Livro.class)
				.setMaxResults(5)
				.getResultList();
	}

	public List<Livro> demaisLivros() {
		String jpql = "select l from Livro l order by l.id desc";
		return this.manager.createQuery(jpql, Livro.class)
				.setFirstResult(6)
				.getResultList();
	}

	public Livro buscaPorId(Integer id) {
		return this.manager.find(Livro.class, id);
		
//		String jpql = "select distinct(l) from Livro l"
//				+ " join fetch l.autores where l.id = :id";
//		
//		TypedQuery<Livro> query = manager.createQuery(jpql, Livro.class);
//		query.setParameter("id", id);
//		
//		return query.getSingleResult();
	}
	
}

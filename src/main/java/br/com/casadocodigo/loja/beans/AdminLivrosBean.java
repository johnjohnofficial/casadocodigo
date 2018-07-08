package br.com.casadocodigo.loja.beans;

import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {
	private Livro livro = new Livro();
	
	@Inject
	private LivroDao livroDao;
	
//	private List<Integer> autoresId = new ArrayList<>();
	
	@Inject
	private AutorDao autorDao;
	
	@Inject
	FacesContext context;
	
	private Part capaLivro;

	@Transactional
	public String salvar() throws IOException {
		
//		for (Integer autorId : autoresId) {
//			this.livro.getAutores().add(new Autor(autorId));
//		}
		
		this.livroDao.salvar(this.livro);
		System.out.println("Livro cadastrado com sucesso: " + this.livro);
		
		FileSaver fileSaver = new FileSaver();							// Nossa nova classe
		livro.setCapaPath(fileSaver.write(this.capaLivro, "livros"));		// Já chamamos o método write e já retornamos o path direto para o Livro
		
		this.context.getExternalContext().getFlash().setKeepMessages(true);
		this.context.addMessage(null, new FacesMessage("O Livro '" + this.livro.getTitulo() +  "' cadastrado com sucesso"));
		
//		this.livro = new Livro();
//		this.autoresId = new ArrayList<>();
		
		return "lista?faces-redirect=true";
	}
	
	public List<Autor> getAutores() {
//		return Arrays.asList(new Autor(1, "Paulo Silveira"), new Autor(2, "Sérgio Lopes"));
		return this.autorDao.listar();
	}
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}

//	public List<Integer> getAutoresId() {
//		return autoresId;
//	}
//
//	public void setAutoresId(List<Integer> autoresId) {
//		this.autoresId = autoresId;
//	}

}

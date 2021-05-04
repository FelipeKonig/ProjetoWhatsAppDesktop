package br.edu.ifsc.proj2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Chamada")
public class Chamada {

	@Id
	@GeneratedValue
	private int id;
	private String tipoLigacao;
	private String contato;

	public Chamada() {
		super();
	}

	public Chamada(String tipoLigacao, String contato) {
		super();
		this.tipoLigacao = tipoLigacao;
		this.contato = contato;
	}

	public Chamada(int id, String tipoLigacao, String contato) {
		super();
		this.id = id;
		this.tipoLigacao = tipoLigacao;
		this.contato = contato;
	}

	public String getContato() {
		return contato;
	}

	public String getTipoLigacao() {
		return tipoLigacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chamada other = (Chamada) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return contato + "		" + "Tipo da chamada: " + tipoLigacao;
	}

	public boolean verificaContato(String chamadaNome) {
		if(chamadaNome.contentEquals(chamadaNome.toString())) {
			return true;
			
		}
		else { 
			return false;
		}
	}
	
	
}

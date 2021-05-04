package br.edu.ifsc.proj2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "Usuario")
public class Usuario {

	@Id
	@GeneratedValue
	private int id;
	private String numero;
	private String nome;
	private String pais, recado;
	private String somNotificacao, notifPopup, somChamada, backupDadosMoveis, backupWifi, backupRoaming, email;
	private boolean reduzDados, sonsConversa, notifSeguranca, confirmaLeitura, vistoUltimo;
	private String tipoNumero;
	private String comentario;
	private boolean ligacao, mensagem, chamadaVideo, audio;
	private boolean ficheiro;
	private boolean notificacao;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Usuario> contato = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Chamada> chamadas = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Status> status = new ArrayList<>();

	public Usuario() {
		super();
	}

	public Usuario(String nome, String numero) {
		super();
		this.nome = nome;
		this.numero = numero;
	}

	public Usuario(String nome, String numero, String pais) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.pais = pais;
	}

	public Usuario(String numero, String nome, String tipoNumero, String comentario, boolean ligacao, boolean mensagem,
			boolean chamadaVideo, boolean audio, boolean ficheiro, boolean notificacao) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.tipoNumero = tipoNumero;
		this.comentario = comentario;
		this.ligacao = ligacao;
		this.mensagem = mensagem;
		this.chamadaVideo = chamadaVideo;
		this.audio = audio;
		this.ficheiro = ficheiro;
		this.notificacao = notificacao;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public boolean isConfirmaLeitura() {
		return confirmaLeitura;
	}

	public void setConfirmaLeitura(boolean confirmaLeitura) {
		this.confirmaLeitura = confirmaLeitura;
	}

	public boolean isVistoUltimo() {
		return vistoUltimo;
	}

	public void setVistoUltimo(boolean vistoUltimo) {
		this.vistoUltimo = vistoUltimo;
	}

	public String getSomNotificacao() {
		return somNotificacao;
	}

	public String getNotifPopup() {
		return notifPopup;
	}

	public String getSomChamada() {
		return somChamada;
	}

	public String getBackupWifi() {
		return backupWifi;
	}

	public void setBackupWifi(String backupWifi) {
		this.backupWifi = backupWifi;
	}

	public String getBackupGoogle() {
		return backupDadosMoveis;
	}

	public String getBackupRede() {
		return backupWifi;
	}

	public boolean isReduzDados() {
		return reduzDados;
	}

	public boolean isSonsConversa() {
		return sonsConversa;
	}

	public boolean isNotifSeguranca() {
		return notifSeguranca;
	}

	public String getBackupDadosMoveis() {
		return backupDadosMoveis;
	}

	public void setBackupDadosMoveis(String backupDadosMoveis) {
		this.backupDadosMoveis = backupDadosMoveis;
	}

	public String getBackupRoaming() {
		return backupRoaming;
	}

	public void setBackupRoaming(String backupRoaming) {
		this.backupRoaming = backupRoaming;
	}

	public String getRecado() {
		return recado;
	}

	public void setRecado(String recado) {
		this.recado = recado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPais() {
		return pais;
	}

	public String getEmail() {
		return email;
	}

	public void setSomNotificacao(String somNotificacao) {
		this.somNotificacao = somNotificacao;
	}

	public void setNotifPopup(String notifPopup) {
		this.notifPopup = notifPopup;
	}

	public void setSomChamada(String somChamada) {
		this.somChamada = somChamada;
	}

	public void setBackupGoogle(String backupGoogle) {
		this.backupDadosMoveis = backupGoogle;
	}

	public void setBackupRede(String backupRede) {
		this.backupWifi = backupRede;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setReduzDados(boolean reduzDados) {
		this.reduzDados = reduzDados;
	}

	public void setSonsConversa(boolean sonsConversa) {
		this.sonsConversa = sonsConversa;
	}

	public void setNotifSeguranca(boolean notifSeguranca) {
		this.notifSeguranca = notifSeguranca;
	}

	public String getTipoNumero() {
		return tipoNumero;
	}

	public void setTipoNumero(String tipoNumero) {
		this.tipoNumero = tipoNumero;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public boolean isLigacao() {
		return ligacao;
	}

	public void setLigacao(boolean ligacao) {
		this.ligacao = ligacao;
	}

	public boolean isMensagem() {
		return mensagem;
	}

	public void setMensagem(boolean mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isChamadaVideo() {
		return chamadaVideo;
	}

	public void setChamadaVideo(boolean chamadaVideo) {
		this.chamadaVideo = chamadaVideo;
	}

	public boolean isAudio() {
		return audio;
	}

	public void setAudio(boolean audio) {
		this.audio = audio;
	}

	public boolean isFicheiro() {
		return ficheiro;
	}

	public void setFicheiro(boolean ficheiro) {
		this.ficheiro = ficheiro;
	}

	public boolean isNotificacao() {
		return notificacao;
	}

	public void setNotificacao(boolean notificacao) {
		this.notificacao = notificacao;
	}

	public List<Usuario> getContato() {
		return contato;
	}

	public void setContato(List<Usuario> contato) {
		this.contato = contato;
	}

	public List<Chamada> getChamadas() {
		return chamadas;
	}

	public List<Status> getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	public int quantidadeContatos() {
		int quant = 0;
		for (Usuario contatos : contato) {
			quant++;
		}
		return quant;
	}

	public int quantidadeChamadas() {
		int quant = 0;
		for (Chamada chamadas : chamadas) {
			quant++;
		}
		return quant;
	}

	public int quantidadeStatus() {
		int quant = 0;
		for (Status status : status) {
			quant++;
		}
		return quant;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", numero=" + numero + ", nome=" + nome + "]";
	}

}

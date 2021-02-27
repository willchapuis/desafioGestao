package model.bean;

public class SalaEvento {

	private String nome;
	private Integer idSalaEvento, lotacao;
	
	/*
	 * GETTERS
	 */
	public String getNome() {
		return nome;
	}
	public Integer getIdSalaEvento() {
		return idSalaEvento;
	}
	public Integer getLotacao() {
		return lotacao;
	}
	
	/* 
	 * SETTERS
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setIdSalaEvento(Integer idSalaEvento) {
		this.idSalaEvento = idSalaEvento;
	}
	public void setLotacao(Integer lotacao) {
		this.lotacao = lotacao;
	}
}

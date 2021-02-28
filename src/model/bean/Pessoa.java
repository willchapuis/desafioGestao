package model.bean;

public class Pessoa {

	private Integer idPessoa, idSalaEtapa1, idSalaEtapa2, idEspaco;
	private String nome, sobrenome;
	private SalaEvento etapa1, etapa2;
	
	/*
	 * GETTERS
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}
	
	public Integer getIdSalaEtapa1() {
		return idSalaEtapa1;
	}
	public Integer getIdSalaEtapa2() {
		return idSalaEtapa2;
	}
	
	public Integer getIdEspaco() {
		return idEspaco;
	}

	public String getNome() {
		return nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public SalaEvento getEtapa1() {
		return etapa1;
	}
	
	public SalaEvento getEtapa2() {
		return etapa2;
	}
	
	/* 
	 * SETTERS
	 */
	public void setIdPessoa(Integer id) {
		this.idPessoa = id;
	}
	
	public void setIdSalaEtapa1(Integer idSalaEtapa1) {
		this.idSalaEtapa1 = idSalaEtapa1;
	}

	public void setIdSalaEtapa2(Integer idSalaEtapa2) {
		this.idSalaEtapa2 = idSalaEtapa2;
	}

	public void setIdEspaco(Integer idEspaco) {
		this.idEspaco = idEspaco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public void setEtapa1(SalaEvento etapa1) {
		this.etapa1 = etapa1;
	}
	
	public void setEtapa2(SalaEvento etapa2) {
		this.etapa2 = etapa2;
	}
}

package model.vo.telefonia;

public class Telefone {
	
	private Integer id;
	private Integer idCliente; //Usado no lugar Cliente para evitar o looping
	private String ddd;
	private String numero;
	private boolean ativo;
	private boolean movel;
	
	public Telefone() {
		
	}
	
	public Telefone(Integer id, String ddd, String numero, boolean ativo, boolean movel) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
		this.ativo = ativo;
		this.movel = movel;
	}
	
	
	public Telefone(String ddd, String numero, boolean ativo, boolean movel) {
		super();
		this.ddd = ddd;
		this.numero = numero;
		this.ativo = ativo;
		this.movel = movel;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isMovel() {
		return movel;
	}

	public void setMovel(boolean movel) {
		this.movel = movel;
	}

	@Override
	public String toString() {
		return "Telefone [ddd=" + ddd + ", numero=" + numero + ", ativo=" + ativo + ", movel=" + movel + "]";
	}

}

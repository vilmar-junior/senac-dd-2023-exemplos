package model.seletor;

public class TelefoneSeletor extends BaseSeletor {

	private String nomeCliente;
	private String ddd;
	private String numero;
	private Boolean ativo;
	private Boolean movel;
	
	@Override
	public boolean temFiltro() {
		return nomeCliente != null && nomeCliente.trim().length() > 0
				&& ddd != null && ddd.trim().length() == 2
				&& numero != null && numero.trim().length() >= 8
				&& ativo != null
				&& movel != null;
	}
	
}

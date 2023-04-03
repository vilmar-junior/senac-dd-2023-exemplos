package model.exception;

public class EnderecoInvalidoException extends Exception {
	
	private static final long serialVersionUID = -3983419049186701482L;

	public EnderecoInvalidoException(String mensagem) {
		super(mensagem);
	}
}

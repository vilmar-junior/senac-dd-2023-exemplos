package model.gerador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import model.dao.telefonia.ClienteDAO;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Endereco;

/**
 * 
 * @author Vilmar C. Pereira Junior Classe responsável por importar planilhas
 *         xls
 * 
 *         Tutorial disponível em:
 *         http://www.codejava.net/coding/how-to-read-excel-files-in-java-using-apache-poi
 *
 */
public class ImportadorPlanilha {

	public void importar(InputStream fis) {
		try {

			// Create Workbook instance holding reference to .xlsx file
			HSSFWorkbook planilha = new HSSFWorkbook(fis);

			// Pega a primeira aba da planilha
			HSSFSheet abaPlanilha = planilha.getSheetAt(0);

			// Obtém o iterador de linhas da planilha escolhida
			Iterator<Row> iteradorLinha = abaPlanilha.iterator();

			// Pula a primeira linha (cabeçalho da planilha)
			iteradorLinha.next();

			int i = 1;
			while (iteradorLinha.hasNext()) {
				Row linhaAtual = iteradorLinha.next();

				Endereco endereco = criarEndereco(linhaAtual);
				Cliente cliente = criarCliente(linhaAtual);

				if (cliente != null && endereco != null) {
					cliente.setEndereco(endereco);

					ClienteDAO clienteDAO = new ClienteDAO();
					clienteDAO.inserir(cliente);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return Response.ok().build();
	}

	private Endereco criarEndereco(Row linhaAtual) {
		Endereco e = null;

		if (linhaAtual.getCell(2) != null && linhaAtual.getCell(3) != null && linhaAtual.getCell(4) != null) {
			// Lê somente as células não-nulas
			Cell celulaRua = linhaAtual.getCell(2);
			Cell celulaBairro = linhaAtual.getCell(3);
			Cell celulaNumero = linhaAtual.getCell(4);

			String numero = celulaNumero.toString();			
			String rua = celulaRua.toString();
			String bairro = celulaBairro.toString();

			e = new Endereco();
			e.setRua(rua);
			e.setBairro(bairro);
			e.setNumero(numero);
		}
		return e;
	}

	private Cliente criarCliente(Row linhaAtual) {
		Cliente c = null;

		if (linhaAtual.getCell(0) != null && linhaAtual.getCell(1) != null) {
			Cell celulaNome = linhaAtual.getCell(0);
			Cell celulaSobrenome = linhaAtual.getCell(1);

			//TODO incluir mais campos da planilha
			c = new Cliente();
			c.setNome(celulaNome.toString() + celulaSobrenome.toString());
		}

		return c;
	}
}
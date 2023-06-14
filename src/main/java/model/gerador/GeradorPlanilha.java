package model.gerador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.util.DateUtil;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Endereco;

public class GeradorPlanilha {
	
	public String gerarPlanilhaClientes(List<Cliente> clientes, String destinoArquivo) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Clientes");
		
		HSSFRow linhaCabecalho = abaPlanilha.createRow(0);
		linhaCabecalho.createCell(0).setCellValue("Nome");
		linhaCabecalho.createCell(1).setCellValue("CPF");
		linhaCabecalho.createCell(2).setCellValue("Data de Nascimento");
		linhaCabecalho.createCell(3).setCellValue("Endereço resumido (Cidade - UF)");
		linhaCabecalho.createCell(4).setCellValue("Quantidade de telefones");
		linhaCabecalho.createCell(5).setCellValue("Ativo?");
		
		int contadorLinhas = 1;
		for(Cliente c: clientes) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(c.getNome());
			novaLinha.createCell(1).setCellValue(c.getCpf());
			novaLinha.createCell(2).setCellValue(DateUtil.formatarDataPadraoBrasil(c.getDataNascimento()));
			novaLinha.createCell(3).setCellValue(c.getEndereco().getCidade() + " - " + c.getEndereco().getEstado());
			novaLinha.createCell(4).setCellValue(c.getTelefones().size());
			novaLinha.createCell(5).setCellValue(c.isAtivo() ? "Sim" : "Não");
			contadorLinhas++;
		}
		
		return salvarNoDisco(arquivoExcel, destinoArquivo);
	}
	
	public String gerarPlanilhaEnderecos(List<Endereco> enderecos, String destinoArquivo) {
		//TODO implementar
		
		//Criar arquivo da planilha (workbook)
		HSSFWorkbook arquivoExcel = null;
		
		//Criar aba da planilha (sheet)
		
		//Criar linha de cabeçalho
		
		//Criar linhaS com os dados dos endereços
		
		return salvarNoDisco(arquivoExcel, destinoArquivo);
	}
	
	private String salvarNoDisco(HSSFWorkbook planilha, String caminhoArquivo) {
		String mensagem = "";
		FileOutputStream saida = null;
		String extensao = ".xls";

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha (sem acesso): " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro de I/O ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}
}

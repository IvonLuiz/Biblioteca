package Biblioteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
	
	private Usuario usuario;
	private Livro livro;
	LocalDate dataDevolucao;
	LocalDate dataAlugado; 
	
	public Emprestimo(Usuario usuario, Livro livro) {
		this.livro = livro;
		this.usuario = usuario;
		this.dataAlugado = LocalDate.now();
        this.dataDevolucao = getDataAlugado().plusDays(usuario.getDiasEmprestimo());
	}

	public String getEmprestimo() {
		String ret = "";

		ret += usuario.getNome();
		ret += " alugado em: " + this.getDataAlugado();
		ret += " data limite retorno: " + this.getDataDevolucao();
		ret += "\n";
		
		return ret;
	}

	public LocalDate getDataDevolucao() {
		return this.dataDevolucao;
	}

	public LocalDate getDataAlugado() {
		return this.dataAlugado;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public Livro getLivro() {
		return this.livro;
	}

     
    public boolean verificarAtraso() {
		LocalDate data = LocalDate.now();
		return data.isAfter(getDataDevolucao());
    }
    
    
    public String verificarAtrasoString() {
    	
    	int diasAtraso = calcularDiasAtraso();

        if (diasAtraso > 0) {
            return "O empréstimo está atrasado por " + diasAtraso + " dias.";
        } 
        return "O empréstimo está em dia.";			
    }
    	
	
	public int calcularDiasAtraso() {
		LocalDate dataAtual = LocalDate.now();
		
        if (dataAtual.isBefore(this.getDataDevolucao())) {
            return 0; // Não há atraso
        }

        return (int) ChronoUnit.DAYS.between(this.getDataDevolucao(), dataAtual);
    }
}

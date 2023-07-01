package Biblioteca;

import java.time.LocalDate;

public class Usuario {
    private String codigo;
    private String nome;
    private LocalDate dataDevolucao;
    private int quantLivrosEmprestados;
    private int quantLivrosReservados;
	private int limiteEmprestimo;
	private int limiteReservas;
    private int diasEmprestimo;
    private boolean passeLivreEmprestimo;
    
    public Usuario(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantLivrosEmprestados = 0;
        this.quantLivrosReservados = 0;
        this.limiteEmprestimo = Integer.MAX_VALUE;
        this.limiteReservas = 3;
        this.diasEmprestimo = 7;
        this.setPasseLivreEmprestimo(false);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
    
    
    public boolean verificarAtrasoDevolucao() {
    	if (getDataDevolucao() != null) {
    		LocalDate data = LocalDate.now();
    		return data.isAfter(getDataDevolucao());
    	}
    	System.out.println("O usuario não possui data de devolucao.");
    	return false;
    }
    
    
    
    public boolean atingiuLimiteEmprestimos() {
        return quantLivrosEmprestados >= limiteEmprestimo;
    }
    
    public void incrementarQuantLivrosEmprestados() {
    	quantLivrosEmprestados++;
    }

    public void decrementarQuantLivrosEmprestados() {
    	quantLivrosEmprestados--;
    }


    
    public boolean atingiuLimiteReservas() {
        return quantLivrosReservados >= limiteReservas;
    }
    
    public void incrementarQuantLivrosReservados() {
    	quantLivrosReservados++;
    }

    public void decrementarQuantLivrosReservados() {
    	quantLivrosReservados--;
    }
    
    
    // getters e setters
	public String getNome() {
		return nome;
	}

	public String getCodigo() {
		return codigo;
	}


	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}
	
	


	
	
	public void setLimiteEmprestimos(int limiteEmprestimo) {
        this.limiteEmprestimo = limiteEmprestimo;
    }
	
	public int getLimiteEmprestimo() {
		return limiteEmprestimo;
	}

	public int getDiasEmprestimo() {
		return diasEmprestimo;
	}

	public void setDiasEmprestimo(int diasEmprestimo) {
		this.diasEmprestimo = diasEmprestimo;
	}

	
	
	public boolean isPasseLivreEmprestimo() {
		return passeLivreEmprestimo;
	}

	public void setPasseLivreEmprestimo(boolean passeLivreEmprestimo) {
		this.passeLivreEmprestimo = passeLivreEmprestimo;
	}
}
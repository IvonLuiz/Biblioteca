package Biblioteca;

public abstract class Usuario {
    private String codigo;
    private String nome;
    private boolean atrasado;
    private int quantLivrosEmprestados;
    private int quantLivrosReservados;
	private int limiteEmprestimo;							// Quantidade limite de livros		 
	private int limiteReservas;								// Quantidade limite de reservas
    private int diasEmprestimo;								// Quantidade limite de dias que pode alugar
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
        this.atrasado = false;
    }

    @Override
    public String toString() {
    	return	"Nome = " + nome + '\n' +
                "Codigo = " + codigo + '\n';
    }
    
    public boolean verificarAtraso() {
    	return this.atrasado;
    }
    
    
    public String verificarStatusString() {
    	if (verificarAtraso()) {
    		return "atrasado";
    	}
    	return "em curso";
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
    
    

	public String getNome() {
		return nome;
	}

	public String getCodigo() {
		return codigo;
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
package Biblioteca;

//AlunoPosGraduacao tem limite de emprestimo 4 e dias de emprestimo 4

public class AlunoPosGraduacao extends Usuario {

	public AlunoPosGraduacao(String codigo, String nome) {
        super(codigo, nome);
        setLimiteEmprestimos(4);
        setDiasEmprestimo(4);
    }
}
package Biblioteca;

// AlunoGraduacao tem limite de emprestimo 3 e dias de emprestimo 3
public class AlunoGraduacao extends Usuario{

    public AlunoGraduacao(String codigo, String nome) {
        super(codigo, nome);
        setLimiteEmprestimos(3);
        setDiasEmprestimo(3);
    }
}

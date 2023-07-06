package Biblioteca;

import java.util.List;
import java.util.ArrayList;

public class VerificacaoEmprestimoComposta implements Verificacao{

	private List<Verificacao> estrategias;
	
	 public VerificacaoEmprestimoComposta() {
	        estrategias = new ArrayList<>();
	        estrategias.add(new VerificacaoLimiteEmprestimos());
	        estrategias.add(new VerificacaoAtrasoDevolucao());
	        estrategias.add(new VerificacaoEmprestimoLivro());
	        estrategias.add(new VerificacaoQuantidadeDisponivel());
	        estrategias.add(new VerificacaoReserva());
	 }
	
	@Override
	public boolean podeRealizarAcao(Usuario usuario, Livro livro) {
        for (Verificacao estrategia : estrategias) {
            if (estrategia.podeRealizarAcao(usuario, livro) == false) {
                return false;
            }
        }
        return true;
	}

}

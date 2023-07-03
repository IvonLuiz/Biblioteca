package Biblioteca;

import java.util.List;
import java.util.ArrayList;

public class VerificacaoReservaComposta implements Verificacao{

	private List<Verificacao> estrategias;
	
	 public VerificacaoReservaComposta() {
	        estrategias = new ArrayList<>();
	        estrategias.add(new VerificacaoLimiteReservas());
	        estrategias.add(new VerificacaoEmprestimoLivro());
	        estrategias.add(new VerificacaoReservaLivro());
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

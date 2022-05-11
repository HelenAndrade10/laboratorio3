package br.com.TeachCoins.TCoins.Controller;

import br.com.TeachCoins.TCoins.Repository.AlunoRepository;
import br.com.TeachCoins.TCoins.Repository.InstituicaoRepository;
import org.springframework.stereotype.Controller;

@Controller
public class InstituicaoController {

    private InstituicaoRepository instituicaoRepo;

    public InstituicaoController(InstituicaoRepository instituicaoRepo){
        this.instituicaoRepo = instituicaoRepo;
    }
}

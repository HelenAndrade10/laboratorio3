package br.com.TeachCoins.TCoins.Controller;

import br.com.TeachCoins.TCoins.Modal.Aluno;
import br.com.TeachCoins.TCoins.Modal.Extrato;
import br.com.TeachCoins.TCoins.Modal.Professor;
import br.com.TeachCoins.TCoins.Modal.Transacao;
import br.com.TeachCoins.TCoins.Repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExtratoController {

    private AlunoRepository alunoRepo;
    private InstituicaoRepository instituicaoRepo;
    private ProfessorRepository professorRepo;
    private ParceiroRepository parceiroRepo;
    private ExtratoRepository extratoRepo;
    private TransacaoRepository transacaoRepo;
    private CarteiraRepository carteiraRepo;
    private List<Aluno> actualList;
    private List<Professor> actualListProf;

    public ExtratoController(AlunoRepository alunoRepo, InstituicaoRepository instituicaoRepo, ProfessorRepository professorRepo,
                           ParceiroRepository parceiroRepo, ExtratoRepository extratoRepo, CarteiraRepository carteiraRepo, TransacaoRepository transacaoRepo){
        this.alunoRepo = alunoRepo;
        this.instituicaoRepo = instituicaoRepo;
        this.professorRepo = professorRepo;
        this.parceiroRepo = parceiroRepo;
        this.extratoRepo = extratoRepo;
        this.carteiraRepo = carteiraRepo;
        this.transacaoRepo = transacaoRepo;
    }

    @GetMapping("/extrato/{id}")
    public String mostrarExtrato(@PathVariable("id") long id, Model model){
        List<Extrato> extratos = new ArrayList<>();
        List<Transacao> transacoes = new ArrayList<>();

        for (Transacao tr: transacaoRepo.findAll()) {
            if(tr.getExtrato().getCliente_id() == id){
                transacoes.add(tr);
            }
        }
        long idExtrato = 0;
        for (Extrato ext: extratoRepo.findAll()) {
            if(ext.getCliente_id() == id){
                idExtrato = ext.getId();
            }
        }

        Extrato unico = extratoRepo.getById(idExtrato);
        model.addAttribute("aluno", alunoRepo.getById(id));
        model.addAttribute("listaTransacoes", transacoes);

        return "IExtrato";
    }

    @GetMapping("/extratoProf/{id}")
    public String mostrarExtratoProf(@PathVariable("id") long id, Model model){
        List<Extrato> extratos = new ArrayList<>();
        List<Transacao> transacoes = new ArrayList<>();

        for (Transacao tr: transacaoRepo.findAll()) {
            if(tr.getExtrato().getCliente_id() == id){
                transacoes.add(tr);
            }
        }
        long idExtrato = 0;
        for (Extrato ext: extratoRepo.findAll()) {
            if(ext.getCliente_id() == id){
                idExtrato = ext.getId();
            }
        }

        Extrato unico = extratoRepo.getById(idExtrato);
        model.addAttribute("professor", professorRepo.getById(id));
        model.addAttribute("listaTransacoes", transacoes);

        return "IExtratoProf";
    }
}

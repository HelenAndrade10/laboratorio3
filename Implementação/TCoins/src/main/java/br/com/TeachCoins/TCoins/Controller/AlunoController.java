package br.com.TeachCoins.TCoins.Controller;

import br.com.TeachCoins.TCoins.Modal.*;
import br.com.TeachCoins.TCoins.Repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AlunoController {

    private AlunoRepository alunoRepo;
    private InstituicaoRepository instituicaoRepo;
    private CarteiraRepository carteiraRepo;
    private ExtratoRepository extratoRepo;
    private TransacaoRepository transacaoRepo;

    public AlunoController(AlunoRepository alunoRepo, InstituicaoRepository instituicaoRepo, CarteiraRepository carteiraRepo,  ExtratoRepository extratoRepo, TransacaoRepository transacaoRepo) {
        this.alunoRepo = alunoRepo;
        this.instituicaoRepo = instituicaoRepo;
        this.carteiraRepo = carteiraRepo;
        this.extratoRepo = extratoRepo;
        this.transacaoRepo = transacaoRepo;
    }

    @GetMapping("/alunos")
    public String mostrarAlunos(Model model, @ModelAttribute("aluno") Aluno aluno) {
        model.addAttribute("listaAlunos", alunoRepo.findAll());
        model.addAttribute("listaInstituicoes", instituicaoRepo.findAll());
        return "AlunosViewAdm";
    }

    @PostMapping("/aluno/salvar")
    public String salvarAluno(@ModelAttribute("aluno") Aluno aluno) {
        alunoRepo.save(aluno);
        Carteira carteira = new Carteira(aluno.getId());
        Extrato extrato = new Extrato(aluno.getId());
        carteiraRepo.save(carteira);
        extratoRepo.save(extrato);
        return "redirect:/alunos";
    }

    @PostMapping("/aluno/atualizar/{id}")
    public String atualizarAluno(@ModelAttribute("aluno") Aluno aluno, @PathVariable("id") long id) {
        Aluno alunoTemp = alunoRepo.getById(id);
        alunoTemp.setNome(aluno.getNome());
        alunoTemp.setEmail(aluno.getEmail());
        alunoTemp.setCpf(aluno.getCpf());
        alunoTemp.setEndereco(aluno.getEndereco());
        alunoTemp.setInstituicao(aluno.getInstituicao());
        alunoRepo.save(alunoTemp);
        return "redirect:/alunos";
    }

    @GetMapping("/aluno/excluir/{id}")
    public String excluirAluno(@PathVariable("id") long id) {
        Optional<Aluno> alunoOpt = alunoRepo.findById(id);
        if (alunoOpt.isEmpty()) {
            throw new IllegalArgumentException("Aluno inválido");
        }
        alunoRepo.delete(alunoOpt.get());

        return "redirect:/alunos";
    }

    @GetMapping("/alunos/buscar/{id}")
    @ResponseBody
    public Map<String, Object> pesquisarAluno(@PathVariable("id") long id) {
        Aluno aluno = alunoRepo.getById(id);
        Map<String, Object> rtn = new LinkedHashMap<>();

        rtn.put("id", aluno.getId());
        rtn.put("nome", aluno.getNome());
        rtn.put("email", aluno.getEmail());
        rtn.put("cpf", aluno.getCpf());
        rtn.put("endereco", aluno.getEndereco());
        rtn.put("instituicao", aluno.getInstituicao());

        return rtn;
    }

    @GetMapping("/aluno/personificar/{id}")
    public String personificarAluno(@PathVariable("id") long id, Model model){
        Aluno personificado = alunoRepo.getById(id);
        model.addAttribute("aluno", personificado);
        return "IAluno";
    }

    @GetMapping("/aluno/carteira/{id}")
    public String verCarteira(@PathVariable("id") long id, Model model, @ModelAttribute("transacao")Transacao transacao){
        List<Carteira> tmp = carteiraRepo.findAll();
        long carteira_id = 0;
        for (Carteira carteira: tmp) {
            if(carteira.getCliente_id()==id){
                carteira_id = carteira.getId();
            }
        }

        List<Aluno> tmp2 = new ArrayList<>();

        for (Aluno aln : alunoRepo.findAll()) {
            if(aln.getId()!=id){
                tmp2.add(aln);
            }
        }

        Carteira carteira = carteiraRepo.getById(carteira_id);

        Aluno personificado = alunoRepo.getById(id);
        model.addAttribute("carteira", carteira);
        model.addAttribute("aluno", personificado);
        model.addAttribute("listaAlunos", tmp2);

        return "ICarteira";
    }

}
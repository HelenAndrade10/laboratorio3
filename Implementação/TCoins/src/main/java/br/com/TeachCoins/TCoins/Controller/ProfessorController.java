package br.com.TeachCoins.TCoins.Controller;

import br.com.TeachCoins.TCoins.Modal.*;
import br.com.TeachCoins.TCoins.Repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ProfessorController {

    private AlunoRepository alunoRepo;
    private ProfessorRepository professorRepo;
    private InstituicaoRepository instituicaoRepo;
    private DepartamentoRepository departamentoRepo;
    private CarteiraRepository carteiraRepo;
    private ExtratoRepository extratoRepo;
    private TransacaoRepository transacaoRepo;

    public ProfessorController(ProfessorRepository professorRepo,DepartamentoRepository departamentoRepo  , InstituicaoRepository instituicaoRepo,CarteiraRepository carteiraRepo,  ExtratoRepository extratoRepo, TransacaoRepository transacaoRepo, AlunoRepository alunoRepo) {
        this.professorRepo = professorRepo;
        this.departamentoRepo = departamentoRepo;
        this.instituicaoRepo = instituicaoRepo;
        this.carteiraRepo = carteiraRepo;
        this.extratoRepo = extratoRepo;
        this.transacaoRepo = transacaoRepo;
        this.alunoRepo = alunoRepo;
    }

    @GetMapping("/professores")
    public String mostrarProfessores(Model model, @ModelAttribute("professor")Professor professor){
        model.addAttribute("listaProfessores", professorRepo.findAll());
        model.addAttribute("listaInstituicoes", instituicaoRepo.findAll());
        model.addAttribute("listaDepartamentos", departamentoRepo.findAll());
        return "ProfessoresViewAdm";
    }

    @PostMapping("/professor/salvar")
    public String salvarProfessor(@ModelAttribute("professor") Professor professor){
        professorRepo.save(professor);
        Carteira carteira = new Carteira(professor.getId());
        carteira.receber(500);
        Extrato extrato = new Extrato(professor.getId());
        carteiraRepo.save(carteira);
        extratoRepo.save(extrato);
        return "redirect:/professores";
    }

    @PostMapping("/professor/atualizar/{id}")
    public String atualizarAluno(@ModelAttribute("professor") Professor professor, @PathVariable("id")long id){
        Professor profTmp = professorRepo.getById(id);
        profTmp.setNome(professor.getNome());
        profTmp.setCpf(professor.getCpf());
        profTmp.setDepartamento(professor.getDepartamento());
        profTmp.setInstituicao(professor.getInstituicao());
        professorRepo.save(profTmp);
        return "redirect:/professores";
    }

    @GetMapping("/professor/excluir/{id}")
    public String excluirProfessor(@PathVariable("id") long id){
        Optional<Professor> professorOpt = professorRepo.findById(id);
        if(professorOpt.isEmpty()){
            throw new IllegalArgumentException("Professor inválido");
        }
        professorRepo.delete(professorOpt.get());

        return "redirect:/professores";
    }

    @GetMapping("/professores/buscar/{id}")
    @ResponseBody
    public Map<String, Object> pesquisarProfessor(@PathVariable("id") long id){
        Professor professor = professorRepo.getById(id);
        Map<String, Object> rtn = new LinkedHashMap<>();

        rtn.put("id", professor.getId());
        rtn.put("nome", professor.getNome());
        rtn.put("cpf", professor.getCpf());
        rtn.put("departamento", professor.getDepartamento());
        rtn.put("instituicao", professor.getInstituicao());

        return rtn;
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/professor/personificar/{id}")
    public String personificarProfessor(@PathVariable("id") long id, Model model){
        Professor personificado = professorRepo.getById(id);
        model.addAttribute("professor", personificado);
        return "IProfessor";
    }

    @GetMapping("/professor/carteira/{id}")
    public String verCarteira(@PathVariable("id") long id, Model model, @ModelAttribute("transacao") Transacao transacao){
        List<Carteira> tmp = carteiraRepo.findAll();
        long carteira_id = 0;
        for (Carteira carteira: tmp) {
            if(carteira.getCliente_id()==id){
                carteira_id = carteira.getId();
            }
        }

        Carteira carteira = carteiraRepo.getById(carteira_id);

        Professor personificado = professorRepo.getById(id);
        model.addAttribute("carteira", carteira);
        model.addAttribute("professor", personificado);
        model.addAttribute("listaAlunos", alunoRepo.findAll());

        return "ICarteiraProf";
    }
}

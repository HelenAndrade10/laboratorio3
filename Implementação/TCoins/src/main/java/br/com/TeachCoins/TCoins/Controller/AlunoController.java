package br.com.TeachCoins.TCoins.Controller;

import br.com.TeachCoins.TCoins.Modal.Aluno;
import br.com.TeachCoins.TCoins.Modal.Parceiro;
import br.com.TeachCoins.TCoins.Repository.AlunoRepository;
import br.com.TeachCoins.TCoins.Repository.InstituicaoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class AlunoController {

    private AlunoRepository alunoRepo;
    private InstituicaoRepository instituicaoRepo;

    public AlunoController(AlunoRepository alunoRepo, InstituicaoRepository instituicaoRepo){
        this.alunoRepo = alunoRepo;
        this.instituicaoRepo = instituicaoRepo;
    }

    @GetMapping("/admin")
    public String painelAdm(){

        return "adminView";
    }

    @GetMapping("/alunos")
    public String mostrarAlunos(Model model, @ModelAttribute("aluno") Aluno aluno){
        model.addAttribute("listaAlunos", alunoRepo.findAll());
        model.addAttribute("listaInstituicoes", instituicaoRepo.findAll());
        return "AlunosViewAdm";
    }

    @PostMapping("/aluno/salvar")
    public String salvarAluno(@ModelAttribute("aluno") Aluno aluno){
        alunoRepo.save(aluno);
        return "redirect:/alunos";
    }

    @PostMapping("/aluno/atualizar/{id}")
    public String atualizarAluno(@ModelAttribute("aluno") Aluno aluno, @PathVariable("id")long id){
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
    public String excluirAluno(@PathVariable("id") long id){
        Optional<Aluno> alunoOpt = alunoRepo.findById(id);
        if(alunoOpt.isEmpty()){
            throw new IllegalArgumentException("Aluno inválido");
        }
        alunoRepo.delete(alunoOpt.get());

        return "redirect:/alunos";
    }

    @GetMapping("/alunos/buscar/{id}")
    @ResponseBody
    public Map<String, Object> pesquisarAluno(@PathVariable("id") long id){
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
}

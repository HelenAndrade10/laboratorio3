package br.com.TeachCoins.TCoins.Controller;

import br.com.TeachCoins.TCoins.Modal.Aluno;
import br.com.TeachCoins.TCoins.Modal.Professor;
import br.com.TeachCoins.TCoins.Repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    private AlunoRepository alunoRepo;
    private InstituicaoRepository instituicaoRepo;
    private ProfessorRepository professorRepo;
    private ParceiroRepository parceiroRepo;
    private ExtratoRepository extratoRepo;
    private CarteiraRepository carteiraRepo;
    private List<Aluno> actualList;
    private List<Professor> actualListProf;

    public AdminController(AlunoRepository alunoRepo, InstituicaoRepository instituicaoRepo, ProfessorRepository professorRepo,
                           ParceiroRepository parceiroRepo, ExtratoRepository extratoRepo, CarteiraRepository carteiraRepo){
        this.alunoRepo = alunoRepo;
        this.instituicaoRepo = instituicaoRepo;
        this.professorRepo = professorRepo;
        this.parceiroRepo = parceiroRepo;
        this.extratoRepo = extratoRepo;
        this.carteiraRepo = carteiraRepo;
    }
    @GetMapping("/admin")
    public String painelAdm(){

        return "adminView";
    }

    @GetMapping("/admin/personificar/alunos")
    public String personificarAlunos(Model model, @ModelAttribute("aluno") Aluno aluno){
        model.addAttribute("listaAlunos", alunoRepo.findAll());
        actualList = alunoRepo.findAll();
        return "/PersonificarAlunosAdm";
    }

    @GetMapping("/admin/personificar/professores")
    public String personificarProfessores(Model model, @ModelAttribute("professor") Professor professor){
        model.addAttribute("listaProfessores", professorRepo.findAll());
        actualListProf = professorRepo.findAll();
        return "PersonificarProfessorAdm";
    }


    @GetMapping("/admin/personificar/alunos/pesquisar/{string}")
    public String pesquisarAluno(Model model, @PathVariable("string")String string, @ModelAttribute("aluno")Aluno aluno){
        String url = "redirect:/admin/personificar/alunos";
        if(!(string.equals("returnBackHome"))) {
            List<Aluno> ultimate = new ArrayList<Aluno>();
            List<Aluno> tempNome = new ArrayList<Aluno>();

            string = string.toLowerCase();

            int M = string.length();
            int N = 0;

            for (Aluno aln : alunoRepo.findAll()) {
//                aln.setNome((aln.getNome()).toLowerCase());
                N = (aln.getNome()).length();

                /* A loop to slide pat one by one */
                for (int i = 0; i <= N - M; i++) {

                    int j;

                    /* For current index i, check for pattern
                      match */
                    for (j = 0; j < M; j++)
                        if (((aln.getNome().toLowerCase()).charAt(i + j) != string.charAt(j)))
                            break;

                    if (j == M) // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
                        tempNome.add(aln);
                }
            }

            if (string.length() > 0) {
                model.addAttribute("listaAlunos", tempNome);
                actualList = tempNome;
                url = "/PersonificarAlunosAdm";
            }else{
                actualList = alunoRepo.findAll();
            }

        }
    return url;
    }

    @GetMapping("/admin/personificar/professores/pesquisar/{string}")
    public String pesquisarProfessor(Model model, @PathVariable("string")String string, @ModelAttribute("professor")Professor professor){
        String url = "redirect:/admin/personificar/professores";
        if(!(string.equals("returnBackHome"))) {
            List<Professor> tempNome = new ArrayList<Professor>();

            string = string.toLowerCase();

            int M = string.length();
            int N = 0;

            for (Professor prof : professorRepo.findAll()) {
//                aln.setNome((aln.getNome()).toLowerCase());
                N = (prof.getNome()).length();

                /* A loop to slide pat one by one */
                for (int i = 0; i <= N - M; i++) {

                    int j;

                    /* For current index i, check for pattern
                      match */
                    for (j = 0; j < M; j++)
                        if (((prof.getNome().toLowerCase()).charAt(i + j) != string.charAt(j)))
                            break;

                    if (j == M) // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
                        tempNome.add(prof);
                }
            }

            if (string.length() > 0) {
                model.addAttribute("listaProfessores", tempNome);
                actualListProf = tempNome;
                url = "PersonificarProfessorAdm";
            }else{
                actualListProf = professorRepo.findAll();
            }

        }
        return url;
    }

}

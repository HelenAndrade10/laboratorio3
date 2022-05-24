package br.com.TeachCoins.TCoins.Controller;

import br.com.TeachCoins.TCoins.Modal.Aluno;
import br.com.TeachCoins.TCoins.Modal.Carteira;
import br.com.TeachCoins.TCoins.Modal.Vantagem;
import br.com.TeachCoins.TCoins.Repository.AlunoRepository;
import br.com.TeachCoins.TCoins.Repository.CarteiraRepository;
import br.com.TeachCoins.TCoins.Repository.ParceiroRepository;
import br.com.TeachCoins.TCoins.Repository.VantagemRepository;
import br.com.TeachCoins.TCoins.TCoinsApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class VantagemController {

    private VantagemRepository vantagemRepo;

    private ParceiroRepository parceiroRepo;

    private AlunoRepository alunoRepo;

    private CarteiraRepository carteiraRepo;

    public VantagemController(VantagemRepository vantagemRepo, ParceiroRepository parceiroRepo, AlunoRepository alunoRepo, CarteiraRepository carteiraRepo) {
        this.vantagemRepo = vantagemRepo;
        this.parceiroRepo = parceiroRepo;
        this.alunoRepo = alunoRepo;
        this.carteiraRepo = carteiraRepo;
    }

    @GetMapping("/vantagem/{id_aluno}")
    public String verOfertas(@PathVariable("id_aluno") Long id_aluno, Model model){
        Aluno alunoPersonificado = alunoRepo.getById(id_aluno);
        Carteira carteiraAluno = new Carteira();
        for (Carteira crtr: carteiraRepo.findAll()) {
            if(crtr.getCliente_id().equals(id_aluno)){
                carteiraAluno = crtr;
            }
        }
        model.addAttribute("carteira", carteiraAluno);
        model.addAttribute("aluno", alunoPersonificado);
        model.addAttribute("ofertas", vantagemRepo.findAll());

        return "IAlunoVerOfertas";
    }

    @PostMapping("/vantagem/salvar/{id}")
    public RedirectView salvarVantagem(Vantagem vantagem, @RequestParam("image") MultipartFile multipartFile, @PathVariable("id") Long id_parceiro) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        vantagem.setPhotos(fileName);
        vantagem.setFornecedor(parceiroRepo.getById(id_parceiro));
//        vantagem.setPath("/vantagem-photos/" + vantagem.getId() + "/" + fileName);
        Vantagem savedVantagem = vantagemRepo.save(vantagem);
//
        savedVantagem.setPath("/images/" + fileName);
        vantagemRepo.save(savedVantagem);
//      savedVantagem.setPath("/vantagem-photos/" + vantagem.getId() + "/" + fileName);
//       vantagemRepo.save(savedVantagem);
        String uploadDir = "src/main/resources/static/images/";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/parceiro/personificar/" + id_parceiro, true);
//        return "redirect:/restart";
    }
}

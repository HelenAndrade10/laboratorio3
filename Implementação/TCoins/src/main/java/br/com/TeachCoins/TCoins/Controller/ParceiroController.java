package br.com.TeachCoins.TCoins.Controller;

import br.com.TeachCoins.TCoins.Modal.Parceiro;
import br.com.TeachCoins.TCoins.Repository.AlunoRepository;
import br.com.TeachCoins.TCoins.Repository.ParceiroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ParceiroController {

    private ParceiroRepository parceiroRepo;

    public ParceiroController(ParceiroRepository parceiroRepo){
        this.parceiroRepo = parceiroRepo;
    }

    @GetMapping("/parceiros")
    public String mostrarParceiros(Model model,@ModelAttribute("parceiro") Parceiro parceiro){
        model.addAttribute("listaParceiros", parceiroRepo.findAll());
        return "ParceirosViewAdm";
    }

    @PostMapping("/parceiro/salvar")
    public String salvarParceiro(@ModelAttribute("parceiro") Parceiro parceiro){
        parceiroRepo.save(parceiro);
        return "redirect:/parceiros";
    }

    @PostMapping("/parceiro/atualizar/{id}")
    public String atualizarParceiro(@ModelAttribute("parceiro") Parceiro parceiro, @PathVariable("id")long id){
        Parceiro parceiroTemp = parceiroRepo.getById(id);
        parceiroTemp.setNome(parceiro.getNome());
        parceiroTemp.setCnpj(parceiro.getCnpj());
        parceiroRepo.save(parceiroTemp);
        return "redirect:/parceiros";
    }

    @GetMapping("/parceiro/excluir/{id}")
    public String excluirParceiro(@PathVariable("id") long id){
        Optional<Parceiro> parceiroOpt = parceiroRepo.findById(id);
        if(parceiroOpt.isEmpty()){
            throw new IllegalArgumentException("Parceiro inválido");
        }
        parceiroRepo.delete(parceiroOpt.get());

        return "redirect:/parceiros";
    }

    @GetMapping("/parceiros/buscar/{id}")
    @ResponseBody
    public Map<String, Object> pesquisarParceiro(@PathVariable("id") long id){
        Parceiro parceiro = parceiroRepo.getById(id);
        Map<String, Object> rtn = new LinkedHashMap<>();

        rtn.put("id", parceiro.getId());
        rtn.put("nome", parceiro.getNome());
        rtn.put("cnpj", parceiro.getCnpj());

        return rtn;
    }
}

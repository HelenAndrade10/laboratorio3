package br.com.TeachCoins.TCoins.Controller;

import br.com.TeachCoins.TCoins.Modal.Aluno;
import br.com.TeachCoins.TCoins.Modal.Parceiro;
import br.com.TeachCoins.TCoins.Modal.Vantagem;
import br.com.TeachCoins.TCoins.Repository.AlunoRepository;
import br.com.TeachCoins.TCoins.Repository.ParceiroRepository;
import br.com.TeachCoins.TCoins.Repository.VantagemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ParceiroController {

    private ParceiroRepository parceiroRepo;

    private VantagemRepository vantagemRepo;

    public ParceiroController(ParceiroRepository parceiroRepo, VantagemRepository vantagemRepo) {
        this.parceiroRepo = parceiroRepo;
        this.vantagemRepo = vantagemRepo;
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

    @GetMapping("/parceiro/personificar/{id}")
    public String personificarParceiro(@PathVariable("id") long id, Model model){
        Parceiro personificado = parceiroRepo.getById(id);
        model.addAttribute("parceiro", personificado);
        return "IParceiro";
    }

    @GetMapping("/parceiro/ofertas/{id}")
    public String verOfertas(Model model, @PathVariable("id") Long id){

        List<Vantagem> vantagens = new ArrayList<>();

        for (Vantagem vntg: vantagemRepo.findAll()) {
            if(vntg.getFornecedor().getId().equals(id)){
                vantagens.add(vntg);
            }
        }
        model.addAttribute("parceiro", parceiroRepo.getById(id));
        model.addAttribute("vantagens", vantagens);
        return "IOfertas";
    }

    @GetMapping("/parceiro/criar-oferta/{id}")
    public String criarOferta(@PathVariable("id")Long id, Model model, @ModelAttribute("vantagem")Vantagem vantagem){
        model.addAttribute("parceiro", parceiroRepo.getById(id));
        return "CadastroVantagens";
    }
}

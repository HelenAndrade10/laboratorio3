package br.com.TeachCoins.TCoins.Controller;

import br.com.TeachCoins.TCoins.Modal.Carteira;
import br.com.TeachCoins.TCoins.Modal.Extrato;
import br.com.TeachCoins.TCoins.Modal.Transacao;
import br.com.TeachCoins.TCoins.Modal.Vantagem;
import br.com.TeachCoins.TCoins.Repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransacaoController {

    private CarteiraRepository carteiraRepo;
    private ExtratoRepository extratoRepo;
    private TransacaoRepository transacaoRepo;
    private AlunoRepository alunoRepo;
    private ProfessorRepository professorRepo;
    private VantagemRepository vantagemRepo;
    private ParceiroRepository parceiroRepo;

    public TransacaoController(CarteiraRepository carteiraRepo, ExtratoRepository extratoRepo, TransacaoRepository transacaoRepo, AlunoRepository alunoRepo, ProfessorRepository professorRepo, VantagemRepository vantagemRepo, ParceiroRepository parceiroRepo) {
        this.carteiraRepo = carteiraRepo;
        this.extratoRepo = extratoRepo;
        this.transacaoRepo = transacaoRepo;
        this.alunoRepo = alunoRepo;
        this.professorRepo = professorRepo;
        this.vantagemRepo = vantagemRepo;
        this.parceiroRepo = parceiroRepo;
    }

    @RequestMapping(value = "/transacao/criar/{remetente}/{destinatario}/{valor}", method = RequestMethod.POST)
    public String fazerTransacao(Model model, @PathVariable("remetente") long remetente_id, @PathVariable("destinatario") long destinatario_id, @PathVariable("valor") int valor){
        List<Extrato> tmp = extratoRepo.findAll();
        List<Carteira> tmp1 = carteiraRepo.findAll();

        String destinatarioNome = alunoRepo.getById(destinatario_id).getNome();



        long extrato_id_dest = 0;
        long extrato_id_remt = 0;
        long carteira_id_dest = 0;
        long carteira_id_remt = 0;


        for (Extrato extr: tmp) {
            if(extr.getCliente_id() == destinatario_id){
                extrato_id_dest= extr.getId();
            }else if(extr.getCliente_id() == remetente_id){
                extrato_id_remt = extr.getId();
            }
        }
        for (Carteira carteira: tmp1) {
            if(carteira.getCliente_id() == destinatario_id){
                carteira_id_dest= carteira.getId();
            }else if(carteira.getCliente_id() == remetente_id){
                carteira_id_remt = carteira.getId();
            }
        }

        Carteira carteira_rementente = carteiraRepo.getById(carteira_id_remt);
        Carteira carteira_destinatario = carteiraRepo.getById(carteira_id_dest);

        Transacao transacaoDestinatario = new Transacao(remetente_id, destinatario_id, valor, "Eu", destinatarioNome,extratoRepo.getById(extrato_id_dest));
        Transacao transacaoRementente = new Transacao(remetente_id, destinatario_id, (valor), destinatarioNome, "Eu",extratoRepo.getById(extrato_id_remt));

        if(carteira_rementente.getSaldo() >= valor){
            transacaoRepo.save(transacaoDestinatario);
            transacaoRepo.save(transacaoRementente);

            carteira_rementente.debitar(valor);
            carteira_destinatario.receber(valor);

            carteiraRepo.save(carteira_destinatario);
            carteiraRepo.save(carteira_rementente);



            return ("redirect:/aluno/carteira/" + remetente_id);
        }else{
            return ("redirect:/aluno/carteira/" + remetente_id + "?error=produto%20ja%20existe");
        }
    }

    @GetMapping("/transacao/criar/{id_remetente}/{id_destinatario}/{vantagem}")
    public String fazerCompra(Model model, @PathVariable("id_remetente") long remetente_id, @PathVariable("id_destinatario") long destinatario_id, @PathVariable("vantagem") long vantagem_id){
        List<Extrato> tmp = extratoRepo.findAll();
        List<Carteira> tmp1 = carteiraRepo.findAll();
        Vantagem vtng = vantagemRepo.getById(vantagem_id);
        String destinatarioNome = parceiroRepo.getById(destinatario_id).getNome();



//        long extrato_id_dest = 0;
        long extrato_id_remt = 0;
//        long carteira_id_dest = 0;
        long carteira_id_remt = 0;


        for (Extrato extr: tmp) {
            if(extr.getCliente_id() == destinatario_id){

            }else if(extr.getCliente_id() == remetente_id){
                extrato_id_remt = extr.getId();
            }
        }
        for (Carteira carteira: tmp1) {
            if(carteira.getCliente_id() == destinatario_id){

            }else if(carteira.getCliente_id() == remetente_id){
                carteira_id_remt = carteira.getId();
            }
        }

        Carteira carteira_rementente = carteiraRepo.getById(carteira_id_remt);
//        Carteira carteira_destinatario = carteiraRepo.getById(carteira_id_dest);

//        Transacao transacaoDestinatario = new Transacao(remetente_id, destinatario_id, vtng.getCusto(), "Eu", destinatarioNome,extratoRepo.getById(extrato_id_dest));
        Transacao transacaoRementente = new Transacao(remetente_id, vantagem_id, destinatario_id ,(vtng.getCusto()), destinatarioNome, "Eu",extratoRepo.getById(extrato_id_remt));

        if(carteira_rementente.getSaldo() >= vtng.getCusto()){
//            transacaoRepo.save(transacaoDestinatario);
            transacaoRepo.save(transacaoRementente);

            carteira_rementente.debitar(vtng.getCusto());
//            carteira_destinatario.receber(vtng.getCusto());

//            carteiraRepo.save(carteira_destinatario);
            carteiraRepo.save(carteira_rementente);


            return ("redirect:/vantagem/" + remetente_id + "?sucess=true");
        }else{
            return ("redirect:/vantagem/" + remetente_id + "?error=saldo%20insuficiente");
        }
    }


    @RequestMapping(value = "/transacaoprof/criar/{remetente}/{destinatario}/{valor}", method = RequestMethod.POST)
    public String fazerTransacaoProf(Model model, @PathVariable("remetente") long remetente_id, @PathVariable("destinatario") long destinatario_id, @PathVariable("valor") int valor){
        List<Extrato> tmp = extratoRepo.findAll();
        List<Carteira> tmp1 = carteiraRepo.findAll();

        String destinatarioNome = alunoRepo.getById(destinatario_id).getNome();
        String remetenteNome = professorRepo.getById(remetente_id).getNome();



        long extrato_id_dest = 0;
        long extrato_id_remt = 0;
        long carteira_id_dest = 0;
        long carteira_id_remt = 0;


        for (Extrato extr: tmp) {
            if(extr.getCliente_id() == destinatario_id){
                extrato_id_dest= extr.getId();
            }else if(extr.getCliente_id() == remetente_id){
                extrato_id_remt = extr.getId();
            }
        }
        for (Carteira carteira: tmp1) {
            if(carteira.getCliente_id() == destinatario_id){
                carteira_id_dest= carteira.getId();
            }else if(carteira.getCliente_id() == remetente_id){
                carteira_id_remt = carteira.getId();
            }
        }

        Carteira carteira_rementente = carteiraRepo.getById(carteira_id_remt);
        Carteira carteira_destinatario = carteiraRepo.getById(carteira_id_dest);

        Transacao transacaoDestinatario = new Transacao(remetente_id, destinatario_id, valor, "Eu", remetenteNome,extratoRepo.getById(extrato_id_dest));
        Transacao transacaoRementente = new Transacao(remetente_id, destinatario_id, (valor), destinatarioNome,"Eu",extratoRepo.getById(extrato_id_remt));

        if(carteira_rementente.getSaldo() >= valor){
            transacaoRepo.save(transacaoDestinatario);
            transacaoRepo.save(transacaoRementente);

            carteira_rementente.debitar(valor);
            carteira_destinatario.receber(valor);

            carteiraRepo.save(carteira_destinatario);
            carteiraRepo.save(carteira_rementente);



            return ("redirect:/professor/carteira/" + remetente_id);
        }else{
            return ("redirect:/professor/carteira/" + remetente_id + "?error=produto%20ja%20existe");
        }
    }
}

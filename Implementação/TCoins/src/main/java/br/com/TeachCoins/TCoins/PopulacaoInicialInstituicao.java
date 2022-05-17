package br.com.TeachCoins.TCoins;

import br.com.TeachCoins.TCoins.Modal.Aluno;
import br.com.TeachCoins.TCoins.Modal.Departamento;
import br.com.TeachCoins.TCoins.Modal.Instituicao;
import br.com.TeachCoins.TCoins.Repository.AlunoRepository;
import br.com.TeachCoins.TCoins.Repository.DepartamentoRepository;
import br.com.TeachCoins.TCoins.Repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class PopulacaoInicialInstituicao implements CommandLineRunner {

    @Autowired
    private DepartamentoRepository departamentoRepo;

    @Autowired
    private InstituicaoRepository instituicaoRepo;

    @Autowired
    private AlunoRepository alunoRepo;

    @Override
    public void run(String... args) throws Exception {

        Departamento dpt1 = new Departamento("Ciência da Computação");
        Departamento dpt2 = new Departamento("Direito");
        Departamento dpt3 = new Departamento("Medicina");
        Departamento dpt4 = new Departamento("Administração");
        Departamento dpt5 = new Departamento("Ciências Contábeis");
        Departamento dpt6 = new Departamento("Engenharia Civil");
        Departamento dpt7 = new Departamento("Engenharia De Produção");
        Departamento dpt8 = new Departamento("Nutrição");
        Departamento dpt9 = new Departamento("Psicologia");
        Departamento dpt10 = new Departamento("Sistemas De Informação");
        Departamento dpt11 = new Departamento("Enfermagem");
        Departamento dpt12 = new Departamento("Biomedicina");
        Departamento dpt13 = new Departamento("Ciências Econômicas");





        departamentoRepo.save(dpt1);
        departamentoRepo.save(dpt2);
        departamentoRepo.save(dpt3);
        departamentoRepo.save(dpt4);
        departamentoRepo.save(dpt5);
        departamentoRepo.save(dpt6);
        departamentoRepo.save(dpt7);
        departamentoRepo.save(dpt8);
        departamentoRepo.save(dpt9);
        departamentoRepo.save(dpt10);
        departamentoRepo.save(dpt11);
        departamentoRepo.save(dpt12);
        departamentoRepo.save(dpt13);


        Instituicao inst1 = new Instituicao("PUC-MG");
        Instituicao inst2 = new Instituicao("PUC-RS");
        Instituicao inst3 = new Instituicao("PUC-RN");
        Instituicao inst4 = new Instituicao("PUC-SP");
        Instituicao inst5 = new Instituicao("PUC-RJ");
        Instituicao inst6 = new Instituicao("PUC-ES");
        Instituicao inst7 = new Instituicao("PUC-SC");
        Instituicao inst8 = new Instituicao("PUC-DF");
        Instituicao inst9 = new Instituicao("PUC-GO");
        Instituicao inst10 = new Instituicao("PUC-CE");
        Instituicao inst11 = new Instituicao("PUC-PE");
        Instituicao inst12 = new Instituicao("PUC-BA");
        Instituicao inst13 = new Instituicao("PUC-AM");
        int i = 14;
        long l = i;
//        Aluno aluno = new Aluno("Pedro Henrique", "pbarcelos56@gmail.com", "08597213613", "Rua rio de janeiro 1980", instituicaoRepo.getById((long)14));
//        alunoRepo.save(aluno);
//        instituicaoRepo.save(inst1);
//        instituicaoRepo.save(inst2);
//        instituicaoRepo.save(inst3);
//        instituicaoRepo.save(inst4);
//        instituicaoRepo.save(inst5);
//        instituicaoRepo.save(inst6);
//        instituicaoRepo.save(inst7);
//        instituicaoRepo.save(inst8);
//        instituicaoRepo.save(inst9);
//        instituicaoRepo.save(inst10);
//        instituicaoRepo.save(inst11);
//        instituicaoRepo.save(inst12);
//        instituicaoRepo.save(inst13);


//
    }


}


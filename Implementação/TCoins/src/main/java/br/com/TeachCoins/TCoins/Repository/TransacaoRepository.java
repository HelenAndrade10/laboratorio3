package br.com.TeachCoins.TCoins.Repository;

import br.com.TeachCoins.TCoins.Modal.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}

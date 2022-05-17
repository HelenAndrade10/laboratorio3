package br.com.TeachCoins.TCoins.Repository;

import br.com.TeachCoins.TCoins.Modal.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtratoRepository extends JpaRepository<Extrato, Long> {

}

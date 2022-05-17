package br.com.TeachCoins.TCoins.Repository;

import br.com.TeachCoins.TCoins.Modal.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}

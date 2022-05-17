package br.com.TeachCoins.TCoins.Repository;

import br.com.TeachCoins.TCoins.Modal.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}

package br.com.TeachCoins.TCoins.Repository;

import br.com.TeachCoins.TCoins.Modal.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}

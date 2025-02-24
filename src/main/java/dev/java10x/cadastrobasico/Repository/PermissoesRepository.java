package dev.java10x.cadastrobasico.Repository;

import dev.java10x.cadastrobasico.Entity.Permissoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissoesRepository extends JpaRepository<Permissoes, Long> {
}

package dev.java10x.cadastrobasico.Repository;

import dev.java10x.cadastrobasico.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

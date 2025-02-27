package dev.java10x.cadastrobasico.Service;

import dev.java10x.cadastrobasico.Entity.Usuario;
import dev.java10x.cadastrobasico.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Pegar Todos Os Usuarios
    public List<Usuario> pegarUsuarios() {
        return usuarioRepository.findAll();
    }

    //Pegar Usuario por ID
    public Usuario pegarUsuarioId(Long id) {
        Optional<Usuario> usuarioId = usuarioRepository.findById(id);
        return usuarioId.orElse(null); //ou me mostra o usuario, ou passa null
    }

    //Criar Usuario
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //Deletar Todos Usuarios
    public void excluirTodosUsuario() {
        usuarioRepository.deleteAll();
    }

    //Deletar Usuario Por ID
    public void excluirUsuario(Long Id){
        usuarioRepository.deleteById(Id);
    }

    //Alterar Usuario
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        if (usuarioRepository.existsById(id)) {
            usuarioAtualizado.setId(id);
            return usuarioRepository.save(usuarioAtualizado);
        } else {
            return null;
        }
    }




}

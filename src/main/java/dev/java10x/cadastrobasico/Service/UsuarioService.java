package dev.java10x.cadastrobasico.Service;

import dev.java10x.cadastrobasico.Entity.Usuario;
import dev.java10x.cadastrobasico.Entity.UsuarioDTO;
import dev.java10x.cadastrobasico.Entity.UsuarioMapper;
import dev.java10x.cadastrobasico.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

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
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.map(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.map(usuario);
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

package dev.java10x.cadastrobasico.Service;

import dev.java10x.cadastrobasico.Entity.Usuario;
import dev.java10x.cadastrobasico.Entity.UsuarioDTO;
import dev.java10x.cadastrobasico.Entity.UsuarioMapper;
import dev.java10x.cadastrobasico.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    //Pegar Todos Os Usuarios
    public List<UsuarioDTO> pegarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::map)
                .collect(Collectors.toList());
        //retornando minha lista, poderia ser com if, for, mas usamos o stream para introduzir pois é melhor para percorrer uma lista.
    }

    //Pegar Usuario por ID
    public UsuarioDTO pegarUsuarioId(Long id) {
        Optional<Usuario> usuarioId = usuarioRepository.findById(id);
        return usuarioId.map(usuarioMapper::map) //percorrendo a lista pelo mapper
                .orElse(null); //ou me mostra o usuario, ou passa null
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

    //Deletar Usuario Por ID - nao precisa passar para o DTO, pois so precisa do ID.
    public void excluirUsuario(Long Id){
        usuarioRepository.deleteById(Id);
    }

    //Alterar Usuario
    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO ) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id); //verifica se existe o usuario
        if (usuarioExistente.isPresent()) { //se existir ele faz =
            Usuario usuarioAtualizado = usuarioMapper.map(usuarioDTO); //atualiza com o mapper navegando pelo codigo
            usuarioAtualizado.setId(id); //seta o ID
            Usuario usuarioSalvo = usuarioRepository.save(usuarioAtualizado); //salva o usuario dentro do usuario salvo
            return usuarioMapper.map(usuarioSalvo); //retorna o usuario salvo
        } else {
            return null;
        }

    }




}

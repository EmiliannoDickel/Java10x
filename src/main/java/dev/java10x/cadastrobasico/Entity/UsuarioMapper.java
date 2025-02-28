package dev.java10x.cadastrobasico.Entity;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario map (UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setPermissoes(usuarioDTO.getPermissoes());
        return usuario;
    }

    public UsuarioDTO map (Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setPermissoes(usuario.getPermissoes());
        return usuarioDTO;
    }

}

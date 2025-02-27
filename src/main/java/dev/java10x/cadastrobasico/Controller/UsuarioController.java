package dev.java10x.cadastrobasico.Controller;

import dev.java10x.cadastrobasico.Entity.Usuario;
import dev.java10x.cadastrobasico.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Pegar Lista De Usuarios
    @GetMapping("/all")
    public List<Usuario> pegarUsuarios () {
        return usuarioService.pegarUsuarios();
    }

    //Pegar pelo ID
    @GetMapping("/{id}")
    public Usuario pegarUsuarioId (@PathVariable Long id) {
        return usuarioService.pegarUsuarioId(id);
    }

    //Criar Usuario
    @PostMapping("/new")
    public Usuario criarUsuario (@RequestBody Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    //Deletar Todos Usuarios
    @DeleteMapping("/all")
    public void excluirTodosUsuario () {
         usuarioService.excluirTodosUsuario();
    }

    //Deletar Usuario por ID
    @DeleteMapping("/{id}")
    public void removerUsuarioId (@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
    }

    @PutMapping("/alterar/{id}")
    public Usuario alterarUsuario (@PathVariable Long id, @RequestBody Usuario usuarioAtualizado ) {
        return usuarioService.atualizarUsuario(id, usuarioAtualizado);
    }





}

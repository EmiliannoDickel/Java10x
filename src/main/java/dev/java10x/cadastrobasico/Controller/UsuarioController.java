package dev.java10x.cadastrobasico.Controller;

import dev.java10x.cadastrobasico.Entity.Usuario;
import dev.java10x.cadastrobasico.Entity.UsuarioDTO;
import dev.java10x.cadastrobasico.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {


    private UsuarioService usuarioService;

    //Pegar Lista De Usuarios
    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>> pegarUsuarios () {
        List<UsuarioDTO> usuariosLista = usuarioService.pegarUsuarios();
        return ResponseEntity.ok(usuariosLista);
    }

    //Pegar pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> pegarUsuarioId (@PathVariable Long id) { //ponto de pergunta faz com que o usuario possa ser devolvido em JSON
       UsuarioDTO usuario = usuarioService.pegarUsuarioId(id);
       if (usuario != null) {
           return ResponseEntity.ok(usuario);
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário: " + id + " não Encontrado!");
       }
    }

    //Criar Usuario
    @PostMapping("/new")
    public ResponseEntity<String> criarUsuario (@RequestBody UsuarioDTO usuario) {
        UsuarioDTO novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuário Criado! Seja Bem Vindo(a) " + novoUsuario.getNome() + " (ID): " + novoUsuario.getId()) ;
    }

    //Deletar Todos Usuarios
    @DeleteMapping("/all")
    public void excluirTodosUsuario () {
         usuarioService.excluirTodosUsuario();
    }

    //Deletar Usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirUsuario (@PathVariable Long id) {
        if (usuarioService.pegarUsuarioId(id) != null) {
            usuarioService.excluirUsuario(id);
            return ResponseEntity.ok("Usuário: " + id + " Excluido!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário Não Encontrado!");
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarUsuario (@PathVariable Long id, @RequestBody UsuarioDTO usuarioAtualizado ) {
        if (usuarioService.pegarUsuarioId(id) != null) {
            usuarioService.atualizarUsuario(id, usuarioAtualizado);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuário: " + usuarioAtualizado.getNome() + " Alterado!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário Não Encontrado!");
        }


    }





}

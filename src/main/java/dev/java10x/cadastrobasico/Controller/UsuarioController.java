package dev.java10x.cadastrobasico.Controller;

import dev.java10x.cadastrobasico.Entity.Usuario;
import dev.java10x.cadastrobasico.Entity.UsuarioDTO;
import dev.java10x.cadastrobasico.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Pegar Lista De Usuarios
    @GetMapping("/all")
    @Operation(summary = "Pegar lista com todos os usuários", description = "essa rota pega todos os usuários do meu banco de dados em lista.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista Encontrado com Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Lista não encontrada")
    })
    public ResponseEntity<List<UsuarioDTO>> pegarUsuarios () {
        List<UsuarioDTO> usuariosLista = usuarioService.pegarUsuarios();
        return ResponseEntity.ok(usuariosLista);
    }

    //Pegar pelo ID
    @GetMapping("/{id}")
    @Operation (summary = "Pegar pelo ID", description = "pega o usuário no banco pelo ID que for colocado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário Encontrado"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    })
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
    @Operation (summary = "Criar Usuário", description = "Cria um usuário no meu banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usúário Criado Com Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na Criação do Usuário")
    })
    public ResponseEntity<String> criarUsuario (@RequestBody UsuarioDTO usuario) {
        UsuarioDTO novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuário Criado! Seja Bem Vindo(a) " + novoUsuario.getNome() + " (ID): " + novoUsuario.getId()) ;
    }

    //Deletar Todos Usuarios
    @DeleteMapping("/all")
    @Operation (summary = "Deletar todos Usuários", description = "Deleta todos usuário no meu banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deletados Com Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro no deletar todos os Usuários")
    })
    public void excluirTodosUsuario () {
         usuarioService.excluirTodosUsuario();
    }

    //Deletar Usuario por ID
    @DeleteMapping("/{id}")
    @Operation (summary = "Deleta um Usuário pelo ID", description = "Deleta o usuário pelo ID no meu banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usúário Deletado Com Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro no Deletar o Usuário")
    })
    public ResponseEntity<String> excluirUsuario (@PathVariable Long id) {
        if (usuarioService.pegarUsuarioId(id) != null) {
            usuarioService.excluirUsuario(id);
            return ResponseEntity.ok("Usuário: " + id + " Excluido!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário Não Encontrado!");
        }
    }

    @PutMapping("/alterar/{id}")
    @Operation (summary = "Alterar Usuário", description = "Altera o usuário no meu banco de dados com uso do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usúário Alterado Com Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro no Alterar o Usuário")
    })
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

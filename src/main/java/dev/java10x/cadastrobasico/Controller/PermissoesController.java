package dev.java10x.cadastrobasico.Controller;

import dev.java10x.cadastrobasico.Entity.Permissoes;
import dev.java10x.cadastrobasico.Service.PermissoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissao")
public class PermissoesController {
    @Autowired
    private PermissoesService permissoesService;

    //Pegar Lista De Permissao
    @GetMapping("/all")
    @Operation (summary = "Pegar lista com todos as Permissões", description = "essa rota pega todos as Permissões do meu banco de dados em lista.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista Encontrado com Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Lista não encontrada")
    })
    public ResponseEntity<List<Permissoes>> pegarPermissao () {
        List<Permissoes> permissoesList = permissoesService.pegarPermissoes();
        return ResponseEntity.ok(permissoesList);
    }

    //Pegar pelo ID
    @GetMapping("/{id}")
    @Operation (summary = "Pegar pelo ID", description = "pega as Permissão no banco pelo ID que for colocado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permissão Encontrada"),
            @ApiResponse(responseCode = "400", description = "Permissão não encontrada")
    })
    public ResponseEntity<?> pegarPermissaoId (@PathVariable Long id) {
        Permissoes permissoes = permissoesService.pegarPermissaoId(id);
        if (permissoes != null) {
            return ResponseEntity.ok(permissoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permissão: " + id + " não Encontrada!");
        }

    }

    //Criar Permissao
    @PostMapping("/new")
    @Operation (summary = "Criar Permissão", description = "Cria uma Permissão no meu banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permissão Criada Com Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na Criação da Permissão")
    })
    public ResponseEntity<String> criarPermissao (@RequestBody Permissoes permissoes) {
        Permissoes permissoesNova = permissoesService.criarPermissao(permissoes);
        return ResponseEntity.status(HttpStatus.CREATED).body("Permissão Criada! Nova Função: " + permissoesNova.getNome() +" adicionada ao sistema.");
    }

    //Deletar Todos Permissao
    @DeleteMapping("/all")
    @Operation (summary = "Deletar todas Permissões", description = "Deleta todas Permissões no meu banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deletadas Com Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro no deletar todas as Permissões")
    })
    public void excluirTodasPermissoes () {
        permissoesService.excluirTodasPermissoes();
    }

    //Deletar Permissao por ID
    @DeleteMapping("/{id}")
    @Operation (summary = "Deleta uma Permissão pelo ID", description = "Deleta a Permissão pelo ID no meu banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permissão Deletada Com Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro no Deletar a Permissão")
    })
    public ResponseEntity<String> excluirPermissaoId (@PathVariable Long id) {
        if (permissoesService.pegarPermissaoId(id) != null) {
            permissoesService.excluirPermissaoId(id);
            return ResponseEntity.ok("Permissão " + id + " removida com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permissão (ID): " + id + " Não encontrada.");
        }
    }

    @PutMapping("/alterar/{id}")
    @Operation (summary = "Alterar Permissão", description = "Altera a Permissão no meu banco de dados com uso do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permissão Alterada Com Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro no Alterar a Permissão")
    })
    public ResponseEntity<String> atualizarPermissao(
            @Parameter(description = "Usuário manda o ID no caminho da requisição") //documenta os parametros
            @PathVariable Long id,
            @Parameter(description = "Usuário manda os dados da Permissão Atualizado no corpo da requisição")
            @RequestBody Permissoes permissaoAtualizada ) {

        if (permissoesService.pegarPermissaoId(id) != null) {
            permissoesService.atualizarPermissao(id, permissaoAtualizada);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Permissão " + permissaoAtualizada.getNome() + " Alterada!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permissão não encontrada!");
        }
    }
}

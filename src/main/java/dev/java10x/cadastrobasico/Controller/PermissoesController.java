package dev.java10x.cadastrobasico.Controller;

import dev.java10x.cadastrobasico.Entity.Permissoes;
import dev.java10x.cadastrobasico.Service.PermissoesService;
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

    //Pegar Lista De Usuarios
    @GetMapping("/all")
    public ResponseEntity<List<Permissoes>> pegarPermissao () {
        List<Permissoes> permissoesList = permissoesService.pegarPermissoes();
        return ResponseEntity.ok(permissoesList);
    }

    //Pegar pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> pegarPermissaoId (@PathVariable Long id) {
        Permissoes permissoes = permissoesService.pegarPermissaoId(id);
        if (permissoes != null) {
            return ResponseEntity.ok(permissoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permissão: " + id + " não Encontrada!");
        }

    }

    //Criar Usuario
    @PostMapping("/new")
    public ResponseEntity<String> criarPermissao (@RequestBody Permissoes permissoes) {
        Permissoes permissoesNova = permissoesService.criarPermissao(permissoes);
        return ResponseEntity.status(HttpStatus.CREATED).body("Permissão Criada! Nova Função: " + permissoesNova.getNome() +" adicionada ao sistema.");
    }

    //Deletar Todos Usuarios
    @DeleteMapping("/all")
    public void excluirTodasPermissoes () {
        permissoesService.excluirTodasPermissoes();
    }

    //Deletar Usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPermissaoId (@PathVariable Long id) {
        if (permissoesService.pegarPermissaoId(id) != null) {
            permissoesService.excluirPermissaoId(id);
            return ResponseEntity.ok("Permissão " + id + " removida com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permissão (ID): " + id + " Não encontrada.");
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> atualizarPermissao (@PathVariable Long id, @RequestBody Permissoes permissaoAtualizada ) {
        if (permissoesService.pegarPermissaoId(id) != null) {
            permissoesService.atualizarPermissao(id, permissaoAtualizada);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Permissão " + permissaoAtualizada.getNome() + " Alterada!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permissão não encontrada!");
        }
    }
}

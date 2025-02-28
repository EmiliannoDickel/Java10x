package dev.java10x.cadastrobasico.Controller;

import dev.java10x.cadastrobasico.Entity.Permissoes;
import dev.java10x.cadastrobasico.Service.PermissoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/permissao")
public class PermissoesController {
    @Autowired
    private PermissoesService permissoesService;

    //Pegar Lista De Usuarios
    @GetMapping("/all")
    public List<Permissoes> pegarPermissao () {
        return permissoesService.pegarPermissao();
    }

    //Pegar pelo ID
    @GetMapping("/{id}")
    public Permissoes pegarPermissaoId (@PathVariable Long id) {
        return permissoesService.pegarPermissaoId(id);
    }

    //Criar Usuario
    @PostMapping("/new")
    public Permissoes criarPermissao (@RequestBody Permissoes permissoes) {
        return permissoesService.criarPermissao(permissoes);
    }

    //Deletar Todos Usuarios
    @DeleteMapping("/all")
    public void excluirTodasPermissoes () {
        permissoesService.excluirTodasPermissoes();
    }

    //Deletar Usuario por ID
    @DeleteMapping("/{id}")
    public void excluirPermissaoId (@PathVariable Long id) {
        permissoesService.excluirPermissaoId(id);
    }

    @PutMapping("/alterar/{id}")
    public Permissoes atualizarPermissao (@PathVariable Long id, @RequestBody Permissoes permissaoAtualizada ) {
        return permissoesService.atualizarPermissao(id, permissaoAtualizada);
    }
}

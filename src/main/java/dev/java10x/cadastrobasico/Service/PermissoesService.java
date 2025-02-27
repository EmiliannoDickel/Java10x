package dev.java10x.cadastrobasico.Service;

import dev.java10x.cadastrobasico.Entity.Permissoes;
import dev.java10x.cadastrobasico.Entity.Usuario;
import dev.java10x.cadastrobasico.Repository.PermissoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PermissoesService {
    @Autowired
    private PermissoesRepository permissoesRepository;

    //Pegar Permissoes
    public List<Permissoes> pegarPermissao() {
        return permissoesRepository.findAll();
    }

    //Pegar Permissao por ID
    public Permissoes pegarPermissaoId(Long id) {
        Optional<Permissoes> permissaoId = permissoesRepository.findById(id);
        return permissaoId.orElse(null); //ou me mostra a permissao, ou passa null
    }

    //Criar Permissao
    public Permissoes criarPermissao(Permissoes permissoes) {
        return permissoesRepository.save(permissoes);
    }

    //Deletar Todas Permissoes
    public void excluirTodasPermissoes() {
        permissoesRepository.deleteAll();
    }

    //Deletar permissao Por ID
    public void excluirPermissaoId(Long Id){
        permissoesRepository.deleteById(Id);
    }

    //Alterar permissao
    public Permissoes atualizarPermissao(Long id, Permissoes permissaoAtualizada) {
        if (permissoesRepository.existsById(id)) {
            permissaoAtualizada.setId(id);
            return permissoesRepository.save(permissaoAtualizada);
        } else {
            return null;
        }
    }




}

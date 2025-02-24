package dev.java10x.cadastrobasico.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_permissoes")
public class Permissoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String permissao;

    @OneToMany (mappedBy = "permissoes")//1 permissao para varios usuarios e mapear ela na tabela usuario.
    private List<Usuario> usuario;
}

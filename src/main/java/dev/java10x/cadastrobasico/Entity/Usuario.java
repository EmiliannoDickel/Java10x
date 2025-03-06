package dev.java10x.cadastrobasico.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity //transforma uma classe em entity no Banco de Dados
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_usuario") //boa prática colocar TB_
@ToString(exclude = "permissoes")
public class Usuario {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // qual estratégia usar para lidar com ID, seja numeros aleatorio, manual ou sequencial
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;

    @Column(unique = true) //faco com que o email seja unico
    private String email;
    @Column(name = "idade")
    private int idade;

    @ManyToOne //quero que na tabela do usuario, tenha uma unica permissao
    @JoinColumn (name = "permissao_id") //juntar as duas colunas com chave estrangeira
    private Permissoes permissoes;

    @Column (name = "senha")
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Permissoes getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Permissoes permissoes) {
        this.permissoes = permissoes;
    }
}

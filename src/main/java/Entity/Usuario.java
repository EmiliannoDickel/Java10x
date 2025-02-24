package Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity //transforma uma classe em entity no Banco de Dados
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_usuario") //boa prática colocar TB_
public class Usuario {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // qual estratégia usar para lidar com ID, seja numeros aleatorio, manual ou sequencial
    private Long id;

    private String nome;

    @Column(unique = true) //faco com que o email seja unico
    private String email;

    private int idade;

    @ManyToOne //quero que na tabela do usuario, tenha uma unica permissao
    @JoinColumn (name = "permissao_id") //juntar as duas colunas com chave estrangeira
    private Permissoes permissoes;

}

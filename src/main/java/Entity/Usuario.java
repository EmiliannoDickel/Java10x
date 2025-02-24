package Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String email;
    private int idade;

}

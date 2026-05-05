import jakarta.persistence.*;
import lombok.*;

@Serdeable
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    private String senha;
    private String nome;
    @Column(unique = true)
    private String email;
}
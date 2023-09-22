package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
// @PrimaryKeyJoinColumn(name = "administrador_id")
public class Administrador extends Usuario implements Serializable {

}

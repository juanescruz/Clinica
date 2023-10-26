package evovital.uniquindio.edu.co.domain;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
// @PrimaryKeyJoinColumn(name = "administrador_id")
public class Administrador extends Usuario implements Serializable {

}

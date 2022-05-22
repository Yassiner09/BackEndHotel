package ma.authentification.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "services")
@Data
@NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class Service implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idService;
    private String name;
    private String desc;
    private Double price;
}

package ma.authentification.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data @EqualsAndHashCode
public class Fidelity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true,nullable = false)
    private String name;
    @Column(nullable = false)
    private Double percentageRoom;
    @Column(nullable = false)
    private Double percentageServices;

    @OneToMany(mappedBy = "fidelity",orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Client> clients;
}

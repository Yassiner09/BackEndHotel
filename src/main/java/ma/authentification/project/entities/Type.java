package ma.authentification.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "type")
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idType;
    private String name;
    private String desc;
    @OneToMany(mappedBy = "type",fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST},orphanRemoval = true)
    //@JsonIgnore
    private Set<Room> rooms;
}

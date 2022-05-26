package ma.authentification.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idRoom;
    private String number;
    private Integer floor;
    private Boolean availability;
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "type_id")
    //@JsonBackReference
    private Type type;
    private String phoneNumber;


}

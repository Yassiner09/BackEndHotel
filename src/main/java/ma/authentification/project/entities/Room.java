package ma.authentification.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "room")
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
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "type_id")
    private Type type;
    private String phoneNumber;

}

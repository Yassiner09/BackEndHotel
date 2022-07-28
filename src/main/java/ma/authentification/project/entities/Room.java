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
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roomId;

    @Column(unique = true,nullable = false)
    private String num;
    @Column(nullable = false)
    private Integer floors;
    @Column(nullable = false)
    private Boolean availability;
    @Column(nullable = false)
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    //@JsonBackReference
    private Type types;
    @Column(unique = true,nullable = false)
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "room",cascade = CascadeType.ALL)
    private List<Reservation> reservations;


}

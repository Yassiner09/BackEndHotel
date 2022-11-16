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
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idClt;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true,nullable = false)
    private String cin;
    @Column(nullable = false)
    private String nationality;
    @Column(unique = true,nullable = false)
    private String cardNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fidelity_id")
    private Fidelity fidelity;
    @OneToMany(mappedBy = "client",cascade = {CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reservation> reservations;

    //reservations impact
    public void addReservationToClient(Reservation reservation){
        this.getReservations().add(reservation);
    }
    public void removeReservationFromClient(Reservation reservation){
        this.getReservations().remove(reservation);
    }
}

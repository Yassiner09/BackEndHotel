package ma.authentification.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idClt;
    private String firstName;
    private String lastName;
    private String cin;
    private String nationality;
    private String cardNumber;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private Set<Reservation> reservations;

    //reservations impact
    public void addReservationToClient(Reservation reservation){
        this.getReservations().add(reservation);
    }
    public void removeReservationFromClient(Reservation reservation){
        this.getReservations().remove(reservation);
    }
}

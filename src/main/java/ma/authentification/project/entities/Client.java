package ma.authentification.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Boolean fidelity;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,orphanRemoval = true)
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

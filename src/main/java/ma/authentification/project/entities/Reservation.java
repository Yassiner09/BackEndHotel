package ma.authentification.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idRes;
    private LocalDate date;
    private LocalTime time;
    private int duree;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "id_client")
    //@JsonBackReference(value = "clientReference")
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "reservation_service",
    joinColumns = @JoinColumn(name = "id_reservation"),
    inverseJoinColumns = @JoinColumn(name = "id_service"))
    private List<Service> services;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,optional = false,orphanRemoval = true)
    @JoinColumn(name = "id_facture")
    private Facture facture;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "id_user")
    //@JsonBackReference(value = "userReference")
    private User user;


    //service and reservation
    public void addServiceToReservation(Service service){
        this.getServices().add(service);
    }
    public void addServicesToReservation(List<Service> services){
        this.getServices().addAll(services);
    }
    public void removeServiceFromReservation(Service service){
        this.getServices().remove(service);
    }
    public void removeServicesFromReservation(List<Service> services){
        this.getServices().removeAll(services);
    }
}

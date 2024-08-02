package uluru.uluruspringbackend.data.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Calendar {

    @Id
    @Column(name = "calendar_id")
    private Long id;

}

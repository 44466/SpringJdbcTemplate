package dev.dia.model;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author dia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="personne_type")
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long pers_id;
    public String prenom;
    public String nom;
    public String adresse;
}


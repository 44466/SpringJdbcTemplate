package dev.dia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
/**
 *
 * @author dia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("0")

public class Ouvrier extends Personne {
   public Date datedenaissance;

   @ManyToMany
   @JoinTable(name = "ouvrier_competance",
           joinColumns = @JoinColumn(name = "id"),
           inverseJoinColumns = @JoinColumn(name = "comptce_id"))
   private Set<Competance> competances;

   @ManyToMany
   @JoinTable(name = "ouvrier_machine",
           joinColumns = @JoinColumn(name = "id"),
           inverseJoinColumns = @JoinColumn(name = "machine_id" ))//new HashSet<>();
   public Set<Machine> machinesList;


}

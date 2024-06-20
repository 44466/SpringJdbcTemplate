package dev.dia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 *
 * @author dia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Competance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long comptce_id;
    public String nom;
    @ManyToMany(mappedBy ="competances",fetch = FetchType.LAZY)
    private Set<Ouvrier> ouvrierSet;


}

package dev.dia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Set;
/**
 * @author dia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long produit_id;
    public String nom;
    public String quantite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_pro_id")
    public Typeproduit typeproduit;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "produit_commande",
            joinColumns = {@JoinColumn(name = "produit_id")},
            inverseJoinColumns = {@JoinColumn(name = "command_id")})
    private Set<Commande> commandes;
}
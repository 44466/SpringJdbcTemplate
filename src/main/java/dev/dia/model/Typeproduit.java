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

public class Typeproduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long type_pro_id;
    public String materiau;
    public String poids;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id")
    public Machine machine;

    @OneToMany(mappedBy = "typeproduit")
    public Set<Produit> produits;
}

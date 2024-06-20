package dev.dia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author dia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long machine_id;
    public String n_serie;
    public String marque;
    public String model;
    public Date Date_achat;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "machine")
    public Set<Typeproduit> typeProduit;

    @ManyToMany(mappedBy = "machinesList", fetch = FetchType.LAZY)
    public List<Ouvrier> ouvrierList;

}

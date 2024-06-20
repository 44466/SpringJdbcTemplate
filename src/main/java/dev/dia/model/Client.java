package dev.dia.model;

import jakarta.persistence.*;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity

@DiscriminatorValue("C")
public class Client extends Personne {

    public String telephone;
    public String contact_personne;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "client")
    public Set<Commande> commandeList;

}

package dev.dia.Controllers;

import dev.dia.model.Commande;

import dev.dia.model.Produit;
import dev.dia.services.CommandeService;
import dev.dia.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@ComponentScan
public class CommandeControlleures {
    @Autowired
    private ProduitService produitService;
    @Autowired
    private CommandeService commandeService;

        @PostMapping("/commande")
        @ResponseStatus(HttpStatus.CREATED)
        public void Add_New_Commande(@RequestBody Commande commande) {
            commandeService.Add_New_Commande(commande);
        }


        @GetMapping("/commandes")
        public List<Commande> getAllcommandes() {
            return commandeService.find_All_Commandes();
        }


    @PutMapping("/produits/{command_id}/produits/{produid_id}")
    Commande addPoduitToCommande(
            @PathVariable String command_id,
            @PathVariable String produid_id
    ) {
        Commande commande = commandeService.findByCommande_Id(command_id);
        Produit produit = produitService.findByProduit_Id(produid_id);
        commande.getProduits().add(produit);
        return commandeService.updateCommande(commande);


    }


    }



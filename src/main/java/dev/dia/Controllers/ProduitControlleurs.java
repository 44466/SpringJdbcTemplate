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

public class ProduitControlleurs {
    @Autowired
    private ProduitService produitService;
    @Autowired
    private CommandeService commandeService;


    @PostMapping("/produit")
    @ResponseStatus(HttpStatus.CREATED)
    public void AddNewProduit(@RequestBody Produit produit) {
        produitService.AddNewProduit(produit);
    }

    @GetMapping("/produits")
    public List<Produit> getAllProduits() {
        return produitService.findAllProduitsV_2();
    }

    @GetMapping("/produitss")
    public List<Produit> getAllProduitsv2() {
        return produitService.findAllProduits();
    }



}
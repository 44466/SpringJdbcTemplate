package dev.dia.services;

import dev.dia.mappers.ProduitResultExtractor;
import dev.dia.model.Produit;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class ProduitService {

    public static final String SQL_FIND_ALL_ONETOMANY = """
            SELECT produit.produit_id, produit.nom,produit.quantite, typeproduit.type_pro_id
            FROM produit
            JOIN typeproduit ON produit.produit_id = typeproduit.type_pro_id
            """;
    public static final String SQL_FIND_PRODUIT_BY_ID = """
            SELECT * FROM produit where produit_id = ?;
            """;

    public static final String SQL_FIND_ALL_AND_COMM = """
            SELECT *
            FROM produit
            JOIN commande ON produit.produit_id = commande.command_id
            """;

    public static final String SQL_FIND_ALL_PROS_MANYTOMANY = """
                 SELECT m.name, cp.id_category
                FROM manufacturer as m
                INNER JOIN product as p
                ON m.id_manufacturer = p.id_manufacturer
                INNER JOIN category_product as cp
                ON p.id_product = cp.id_product
                WHERE cp.id_category = 'some value'
            """;

    private static final String SQL_FIND_ALL_PRODUITS = """
            SELECT * FROM produit;
            """;

    private static final String SQL_CREATE_PRODUIT = """
            INSERT INTO produit(produit_id, nom, quantite)
            VALUES(?, ?, ?);
            """;

    private final JdbcClient jdbcClient;

    private final JdbcTemplate jdbcTemplate;



    public Long AddNewProduit(Produit produit) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(SQL_CREATE_PRODUIT)
                .params(produit.produit_id, produit.nom, produit.quantite)
                .update();
        return keyHolder.getKeyAs(Long.class);
        //return (long) keyHolder.getKey();
    }


    public List<Produit> findAllProduits() {
        return jdbcClient.sql(SQL_FIND_ALL_PRODUITS)
                .query(Produit.class)
                .list();
    }



    public Produit findByProduit_Id(String produit_id) {
        try {
            Produit produit = jdbcTemplate.queryForObject("SELECT * FROM produit WHERE produit_id=?",
                    BeanPropertyRowMapper.newInstance(Produit.class), produit_id);
            return produit;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public List<Produit> findAllProduitsV_2() {
        return jdbcClient.sql(SQL_FIND_ALL_PRODUITS)
                //SQL_FIND_ALL_PRODUITS  SQL_FIND_ALL_ONETOMANY
       // SQL_FIND_ALL_AND_COMM
                .query(new ProduitResultExtractor());
    }
}
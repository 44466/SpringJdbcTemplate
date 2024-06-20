package dev.dia.mappers;

import dev.dia.model.Commande;
import dev.dia.model.Produit;
import dev.dia.model.Typeproduit;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author dia
 */
public class ProduitResultExtractor implements ResultSetExtractor<List<Produit>> {
    /**
     *
     * @param rs
     * @return
     * @throws SQLException
     * @throws DataAccessException
     */
    @Override
    public List<Produit> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Produit> produitMap = new LinkedHashMap<>();
        while (rs.next()) {
            Long id = (rs.getLong("produit_id"));
            Produit produit1 = produitMap.get(id);

            if (produit1 == null) {

                produit1 = new Produit(id,
                        rs.getString("nom"),
                        rs.getString("quantite"),
                        new Typeproduit(rs.getLong("type_pro_id" ),
                                null,
                                null                                                               ,
                                null,
                                new HashSet<>()),
                        new HashSet<>());
                produitMap.put(String.valueOf(id), produit1);
            }
            produit1.getCommandes().add((Commande) rs.getObject("commandes"));
        }
        return  new ArrayList<>(produitMap.values());
    }
}

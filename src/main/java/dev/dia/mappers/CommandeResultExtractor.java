package dev.dia.mappers;

import dev.dia.model.Commande;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author dia
 */
public class CommandeResultExtractor implements ResultSetExtractor<List<Commande>> {
    /**
     *
     * @param rs
     * @return
     * @throws SQLException
     * @throws DataAccessException
     */
    @Override
    public List<Commande> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Commande> commandeMap = new LinkedHashMap<>();
        while (rs.next()) {
            String commande_id = (rs.getString("command_id"));
            Commande commande1 = commandeMap.get(commande_id);



        }
        return  new ArrayList<>(commandeMap.values());
    }
}

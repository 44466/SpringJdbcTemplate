package dev.dia.services;

import dev.dia.model.Commande;
import lombok.AllArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class CommandeService {

    private JdbcTemplate jdbcTemplate;
    private JdbcClient jdbcClient;
    private static final String SQL_FIND_ALL_COMMANDE = """
            SELECT * FROM COMMANDE;
            """;

    public static final String SQL_FIND_COMMANDE_BY_ID = """
            SELECT * FROM commande where command_id = ? ;
            """;

    private static final String SQL_CREATE_COMMANDE = """
             UPDATE commande set   command_id= ? where command_id=?
            """;
    private static final String SQL_UPDATE_COMMANDE= """               
             UPDATE COMMANDE SET nom = ? WHERE command_id = ?;
             """;


    public int Add_New_Commande(Commande commande) {
        return jdbcTemplate.update(SQL_CREATE_COMMANDE, new Object[]{
                commande.command_id, commande.getLivraisonPrevu(),
                commande.getLivraisonReel(), commande.getDate(), commande.getNom()});
    }

    public Commande updateCommande(Commande commande) {
        Integer updated = jdbcTemplate.update(SQL_UPDATE_COMMANDE,new Object[]{
     commande.getNom(),commande.getCommand_id()});

        if (updated == 0) {
            throw new RuntimeException("User not found");

        }
        return commande;
    }



    public List<Commande> find_All_Commandes() {
        // return jdbcTemplate.query("SELECT * from patient",new CommandeResultExtractor());
        return jdbcTemplate.query(SQL_FIND_ALL_COMMANDE,
                BeanPropertyRowMapper.newInstance(Commande.class));
    }

    public Commande findByCommande_Id(String command_id) {
        try {
            Commande commande = jdbcTemplate.queryForObject("SELECT * FROM commande WHERE command_id=?",
                    BeanPropertyRowMapper.newInstance(Commande.class), command_id);
            return commande;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

}

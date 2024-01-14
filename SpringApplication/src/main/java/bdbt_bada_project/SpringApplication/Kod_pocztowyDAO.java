package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Kod_pocztowyDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Kod_pocztowyDAO(JdbcTemplate jdbcTemplate)
    {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    /* Import java.util.List */
    public List<Kod_pocztowy> list()
    {
        String sql =  "SELECT * FROM \"C##BADAGRB15\".KODY_POCZTOWE";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Kod_pocztowy.class));
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Kod_pocztowy kod_pocztowy)
    {
    }

    /* Read – odczytywanie danych z bazy */
    public Kod_pocztowy get(String kod_pocztowy)
    {
        String sql = String.format("SELECT * FROM KODY_POCZTOWE WHERE KOD_POCZTOWY LIKE '%s'", kod_pocztowy);
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Kod_pocztowy.class));
    }

    /* Update – aktualizacja danych */
    public void update(Kod_pocztowy kod_pocztowy)
    {
    }

    /* Delete – wybrany rekord z danym id */
    public void delete(String kod_pocztowy)
    {
    }
}

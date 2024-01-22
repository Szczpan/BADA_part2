package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PunktPoboruEnergiiDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PunktPoboruEnergiiDAO(JdbcTemplate jdbcTemplate)
    {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    /* Import java.util.List */
    public List<PunktPoboruEnergii> list()
    {
        String sql =  "SELECT * FROM PUNKTY_POBORU_ENERGII";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PunktPoboruEnergii.class));
    }

    public List<PunktPoboruEnergii> listByNrKlienta(int nr_klienta)
    {
        String sql = String.format("SELECT * FROM PUNKTY_POBORU_ENERGII WHERE NR_KLIENTA = %d", nr_klienta);
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PunktPoboruEnergii.class));
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(PunktPoboruEnergii punktPoboruEnergii)
    {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("PUNKTY_POBORU_ENERGII").usingColumns("NR_KLIENTA", "NR_ADRESU", "DOSTARCZANE_NAPIECIE");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(punktPoboruEnergii);
        simpleJdbcInsert.execute(param);
    }

    /* Read – odczytywanie danych z bazy */
    public PunktPoboruEnergii get(int nr_punktu)
    {
        String sql = String.format("SELECT * FROM PUNKTY_POBORU_ENERGII WHERE NR_PUNKTU = %d", nr_punktu);
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(PunktPoboruEnergii.class));
    }


    /* Update – aktualizacja danych */
    public void update(PunktPoboruEnergii punktPoboruEnergii)
    {
    }

    /* Delete – wybrany rekord z danym id */
    public void delete(int nr_punktu)
    {
    }
}

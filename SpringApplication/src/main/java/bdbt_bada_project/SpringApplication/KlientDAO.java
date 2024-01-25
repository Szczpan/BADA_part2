package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.net.CookieHandler;
import java.util.Date;
import java.util.List;

@Repository
public class KlientDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public KlientDAO(JdbcTemplate jdbcTemplate)
    {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Klient> list()
    {
        String sql = "SELECT * FROM \"C##BADAGRB15\".KLIENCI";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Klient.class));
    }

    public void save(Klient klient)
    {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("KLIENCI").usingColumns("IMIE", "NAZWISKO", "ADRES_EMAIL", "NR_TELEFONU", "NR_KONTA", "NR_CENTRALI", "NR_ADRESU");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klient);
        simpleJdbcInsert.execute(param);
    }

    public Klient get(String email)
    {
        String sql = String.format("SELECT * FROM KLIENCI WHERE ADRES_EMAIL LIKE '%s'", email);

        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Klient.class));
    }

    public void update(Klient klient)
    {

    }

    public void delete(int nr_klienta)
    {
        String sql = "DELETE FROM KLIENCI WHERE NR_KLIENTA = ?";
        jdbcTemplate.update(sql, nr_klienta);
    }
}

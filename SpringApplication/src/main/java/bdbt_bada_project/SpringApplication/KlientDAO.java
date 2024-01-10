package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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

    }

    public Klient get(int nr_klienta)
    {
        return null;
    }

    public void update(Klient klient)
    {

    }

    public void delete(int nr_klienta)
    {

    }
}

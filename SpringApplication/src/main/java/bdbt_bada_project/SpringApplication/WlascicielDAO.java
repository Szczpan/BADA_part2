package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WlascicielDAO
{
     @Autowired
    private JdbcTemplate jdbcTemplate;

    public WlascicielDAO(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wlasciciel> list()
    {
        String sql = "SELECT * FROM \"C##BADAGRB15\".WLASCICIELE";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Wlasciciel.class));
    }

    public void save(Wlasciciel wlasciciel)
    {

    }

    public Wlasciciel get(int nr_wlasciciela)
    {
        return null;
    }

    public void update(Wlasciciel wlasciciel)
    {

    }

    public void delete(int nr_wlasciciela)
    {

    }
}

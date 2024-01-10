package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CentralaDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CentralaDAO(JdbcTemplate jdbcTemplate)
    {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Centrala> list()
    {
        String sql = "SELECT * FROM \"C##BADAGRB15\".CENTRALE";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Centrala.class));
    }

    public void save(Centrala centrala)
    {

    }

    public Centrala get(int nr_centrali)
    {
        return null;
    }

    public void update(Centrala centrala)
    {

    }

    public void delete(int nr_centrali)
    {

    }
}

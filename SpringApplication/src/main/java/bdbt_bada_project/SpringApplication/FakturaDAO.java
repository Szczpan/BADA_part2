package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FakturaDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public FakturaDAO(JdbcTemplate jdbcTemplate)
    {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Faktura> list()
    {
        String sql = "SELECT * FROM FAKTURY";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Faktura.class));
    }

    public void save(Faktura faktura)
    {

    }

    public Faktura get(int nr_faktury)
    {
        String sql = String.format("SELECT * FROM FAKTURY WHERE NR_FAKTURY = %d", nr_faktury);

        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Faktura.class));
    }

    public List<Faktura> listByClient(int client_nr)
    {
        String sql = String.format("SELECT * FROM FAKTURY WHERE NR_KLIENTA = %d", client_nr);

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Faktura.class));
    }

    public void update(Faktura faktura)
    {

    }

    public void delete(int nr_faktury)
    {

    }
}

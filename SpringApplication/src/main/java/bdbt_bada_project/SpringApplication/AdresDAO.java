package bdbt_bada_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdresDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AdresDAO(JdbcTemplate jdbcTemplate)
    {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Adres> list()
    {
        String sql = "SELECT * FROM \"C##BADAGRB15\".ADRESY";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Adres.class));
    }

    public void save(Adres adres)
    {

    }

    public Adres get(int nr_adresu)
    {
        String sql = String.format("SELECT * FROM ADRESY WHERE NR_ADRESU = %d", nr_adresu);
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Adres.class));
    }

    public void update(Adres adres)
    {

    }

    public void delete(int nr_adresu)
    {

    }

}

package bdbt_bada_project.SpringApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Kod_pocztowyDAOTest
{
    private Kod_pocztowyDAO dao;
    @BeforeEach
    void setUp()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("C##BADAGRB15");
        dataSource.setPassword("BADAGRB15");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new Kod_pocztowyDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list()
    {
        List<Kod_pocztowy> kod_pocztowyList = dao.list();

        assertTrue(kod_pocztowyList.isEmpty());
    }

    @Test
    void save()
    {
        Kod_pocztowy kod_pocztowy = new Kod_pocztowy("00-000", "mazowieckie", "Warszawa");
        dao.save(kod_pocztowy);
    }

    @Test
    void get()
    {
    }

    @Test
    void update()
    {
    }

    @Test
    void delete()
    {
    }
}
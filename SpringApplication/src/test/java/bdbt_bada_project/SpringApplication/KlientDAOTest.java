package bdbt_bada_project.SpringApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KlientDAOTest
{
    private KlientDAO klientDAO;

    @BeforeEach
    void setUp()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("C##BADAGRB15");
        dataSource.setPassword("BADAGRB15");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        klientDAO = new KlientDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void get()
    {
        String email = "jerzy@gmail.com";

        Klient klient = klientDAO.get(email);

        assertNotNull(klient);
    }
}

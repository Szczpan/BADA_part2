package bdbt_bada_project.SpringApplication;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PunktPoboruEnergiiDAOTest
{
    PunktPoboruEnergiiDAO punktPoboruEnergiiDAO;
    @BeforeEach
    void setUp()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("C##BADAGRB15");
        dataSource.setPassword("BADAGRB15");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        punktPoboruEnergiiDAO = new PunktPoboruEnergiiDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list()
    {
        List<PunktPoboruEnergii> punktPoboruEnergiiList = punktPoboruEnergiiDAO.list();
        assertNotNull(punktPoboruEnergiiList);
    }
    @Test
    void listByNrKlienta()
    {
        List<PunktPoboruEnergii> punktPoboruEnergiiList = punktPoboruEnergiiDAO.listByNrKlienta(2);
        assertNotNull(punktPoboruEnergiiList);
    }
}

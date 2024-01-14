package bdbt_bada_project.SpringApplication;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Faktura
{
    private int nr_faktury;
    private float kwota;
    private java.sql.Date data;
    private String status_faktury;
    private int nr_klienta;
    private int nr_pomiaru;
    private int nr_umowy;

    public Faktura()
    {
    }

    public Faktura(int nr_faktury, float kwota, Date data, String status_faktury, int nr_klienta, int nr_pomiaru, int nr_umowy)
    {
        this.nr_faktury = nr_faktury;
        this.kwota = kwota;
        this.data = data;
        this.status_faktury = status_faktury;
        this.nr_klienta = nr_klienta;
        this.nr_pomiaru = nr_pomiaru;
        this.nr_umowy = nr_umowy;
    }

    @Override
    public String toString()
    {
        return "Faktura{" +
                "nr_faktury=" + nr_faktury +
                ", kwota=" + kwota +
                ", data=" + data +
                ", status_faktury='" + status_faktury + '\'' +
                ", nr_klienta=" + nr_klienta +
                ", nr_pomiaru=" + nr_pomiaru +
                ", nr_umowy=" + nr_umowy +
                '}';
    }
}

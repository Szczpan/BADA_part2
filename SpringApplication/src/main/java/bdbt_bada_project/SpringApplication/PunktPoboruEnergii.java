package bdbt_bada_project.SpringApplication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PunktPoboruEnergii
{
    private int nr_punktu;
    private int nr_klienta;
    private int nr_adresu;
    private int dostarczane_napiecie;

    public PunktPoboruEnergii()
    {
    }

    public PunktPoboruEnergii(int nr_punktu, int nr_klienta, int nr_adresu, int dostarczane_napiecie)
    {
        this.nr_punktu = nr_punktu;
        this.nr_klienta = nr_klienta;
        this.nr_adresu = nr_adresu;
        this.dostarczane_napiecie = dostarczane_napiecie;
    }

    @Override
    public String toString()
    {
        return "PunktPoboruEnergii{" +
                "numer_punktu=" + nr_punktu +
                ", numer_klienta=" + nr_klienta +
                ", numer_adresu=" + nr_adresu +
                ", napiecie=" + dostarczane_napiecie +
                '}';
    }
}

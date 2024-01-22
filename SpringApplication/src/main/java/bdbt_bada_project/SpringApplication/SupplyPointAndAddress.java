package bdbt_bada_project.SpringApplication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplyPointAndAddress
{
    public PunktPoboruEnergii punktPoboruEnergii;
    public Adres adres;
    public Kod_pocztowy kod_pocztowy;

    public SupplyPointAndAddress(PunktPoboruEnergii punktPoboruEnergii, Adres adres, Kod_pocztowy kod_pocztowy)
    {
        this.punktPoboruEnergii = punktPoboruEnergii;
        this.adres = adres;
        this.kod_pocztowy = kod_pocztowy;
    }

    public SupplyPointAndAddress()
    {
        punktPoboruEnergii = new PunktPoboruEnergii();
        adres = new Adres();
        kod_pocztowy = new Kod_pocztowy();
    }
}

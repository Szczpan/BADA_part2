package bdbt_bada_project.SpringApplication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplyPointAndAddress
{
    private PunktPoboruEnergii punktPoboruEnergii;
    private Adres adres;
    private Kod_pocztowy kod_pocztowy;

    public SupplyPointAndAddress(PunktPoboruEnergii punktPoboruEnergii, Adres adres, Kod_pocztowy kod_pocztowy)
    {
        this.punktPoboruEnergii = punktPoboruEnergii;
        this.adres = adres;
        this.kod_pocztowy = kod_pocztowy;
    }

    public SupplyPointAndAddress()
    {
    }
}

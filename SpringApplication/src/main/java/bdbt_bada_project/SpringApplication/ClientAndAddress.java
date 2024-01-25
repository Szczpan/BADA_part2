package bdbt_bada_project.SpringApplication;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientAndAddress
{
    public Klient klient;
    public Adres adres;
    public Kod_pocztowy kod_pocztowy;

    public ClientAndAddress(Klient klient, Adres adres, Kod_pocztowy kod_pocztowy)
    {
        this.klient = klient;
        this.adres = adres;
        this.kod_pocztowy = kod_pocztowy;
    }


    public ClientAndAddress()
    {
        this.klient = new Klient();
        this.adres = new Adres();
        this.kod_pocztowy = new Kod_pocztowy();
        klient.setNr_centrali(1);
    }
}

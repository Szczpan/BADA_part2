package bdbt_bada_project.SpringApplication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wlasciciel
{
    private int nr_wlasciciela;
    private String imie;
    private String nazwisko;
    private String nr_telefonu;
    private String adres_email;
    private int numer_adresu;

    public Wlasciciel()
    {
    }

    public Wlasciciel(int nr_wlasciciela, String imie, String nazwisko, String nr_telefonu, String adres_email, int numer_adresu)
    {
        this.nr_wlasciciela = nr_wlasciciela;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_telefonu = nr_telefonu;
        this.adres_email = adres_email;
        this.numer_adresu = numer_adresu;
    }

    @Override
    public String toString()
    {
        return "Wlasciciel{" +
                "nr_wlasciciela=" + nr_wlasciciela +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", nr_telefonu='" + nr_telefonu + '\'' +
                ", adres_email='" + adres_email + '\'' +
                ", numer_adresu=" + numer_adresu +
                '}';
    }
}

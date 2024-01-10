package bdbt_bada_project.SpringApplication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Klient
{
    private int nr_klienta;
    private String imie;
    private String nazwisko;
    private String adres_email;
    private String nr_telefonu;
    private String nr_konta;
    private int nr_centrali;
    private int nr_adresu;

    public Klient()
    {
    }

    public Klient(int nr_klienta, String imie, String nazwisko, String adres_email, String nr_telefonu, String nr_konta, int nr_centrali, int nr_adresu)
    {
        this.nr_klienta = nr_klienta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres_email = adres_email;
        this.nr_telefonu = nr_telefonu;
        this.nr_konta = nr_konta;
        this.nr_centrali = nr_centrali;
        this.nr_adresu = nr_adresu;
    }

    @Override
    public String toString()
    {
        return "Klient{" +
                "nr_klienta=" + nr_klienta +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", adres_email='" + adres_email + '\'' +
                ", nr_telefonu='" + nr_telefonu + '\'' +
                ", nr_konta='" + nr_konta + '\'' +
                ", nr_centrali=" + nr_centrali +
                ", nr_adresu=" + nr_adresu +
                '}';
    }
}

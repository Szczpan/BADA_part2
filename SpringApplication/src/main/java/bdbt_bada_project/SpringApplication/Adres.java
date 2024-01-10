package bdbt_bada_project.SpringApplication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adres
{
    private int nr_adresu;
    private String ulica;
    private String nr_domu;
    private String nr_mieszkania;
    private String kraj;
    private String kod_pocztowy;

    public Adres()
    {
    }

    public Adres(int nr_adresu, String ulica, String nr_domu, String nr_mieszkania, String kraj, String kod_pocztowy)
    {
        super();
        this.nr_adresu = nr_adresu;
        this.ulica = ulica;
        this.nr_domu = nr_domu;
        this.nr_mieszkania = nr_mieszkania;
        this.kraj = kraj;
        this.kod_pocztowy = kod_pocztowy;
    }

    @Override
    public String toString()
    {
        return "Adres{" +
                "nr_adresu=" + nr_adresu +
                ", ulica='" + ulica + '\'' +
                ", nr_domu='" + nr_domu + '\'' +
                ", nr_mieszkania='" + nr_mieszkania + '\'' +
                ", kraj='" + kraj + '\'' +
                ", kod_pocztowy='" + kod_pocztowy + '\'' +
                '}';
    }
}

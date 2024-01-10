package bdbt_bada_project.SpringApplication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Centrala
{
    private int nr_centrali;
    private int nr_adresu;
    private String nazwa;

    public Centrala()
    {
    }

    public Centrala(int nr_centrali, int nr_adresu, String nazwa)
    {
        this.nr_centrali = nr_centrali;
        this.nr_adresu = nr_adresu;
        this.nazwa = nazwa;
    }

    @Override
    public String toString()
    {
        return "Centrala{" +
                "nr_centrali=" + nr_centrali +
                ", nr_adresu=" + nr_adresu +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}

package bdbt_bada_project.SpringApplication;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Kod_pocztowy
{
    private String kod_pocztowy;
    private String wojewodztwo;
    private String miasto;

    public Kod_pocztowy()
    {
    }

    public Kod_pocztowy(String kod_pocztowy, String wojewodztwo, String miasto)
    {
        super();
        this.kod_pocztowy = kod_pocztowy;
        this.wojewodztwo = wojewodztwo;
        this.miasto = miasto;
    }

    @Override
    public String toString()
    {
        return "Kod_pocztowy{" +
                "kod_pocztowy='" + kod_pocztowy + '\'' +
                ", wojewodztwo='" + wojewodztwo + '\'' +
                ", miasto='" + miasto + '\'' +
                '}';
    }
}

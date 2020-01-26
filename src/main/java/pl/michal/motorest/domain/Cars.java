package pl.michal.motorest.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id","nazwa", "dataZakupu","kolor"})
public class Cars {
    Long Id;
    String Nazwa;
    String dataZakupu;
    String kolor;

    @Override
    public String toString() {
        return Id +"," + Nazwa + "," + dataZakupu + "," +kolor;
    }
}

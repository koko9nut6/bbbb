package caps.testing.dto;


import caps.testing.domain.Stock;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockUpdateDto {

    private Timestamp date;
    private int price;
    private int quantity;

    public Stock toUpdate() {
        return Stock.builder()
                .date(date)
                .price(price)
                .quantity(quantity)
                .build();
    }
}

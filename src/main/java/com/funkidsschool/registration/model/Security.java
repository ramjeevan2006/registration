import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "securities") // Replace with actual table name
public class Security {

    @Id
    @Column(name = "cusip")
    private String cusip;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "strategy_source")
    private String strategySource;

    @Column(name = "as_of_date")
    private LocalDate asOfDate;

    @Column(name = "shares_quantity")
    private BigDecimal sharesQuantity;

    @Column(name = "shares_outstanding")
    private BigDecimal sharesOutstanding;

    @Column(name = "market_value")
    private BigDecimal marketValue;
}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Snapshot class
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Snapshot {
    private String entityId;
    private String snapshotSourceCode;
    private String snapshotPointCode;
    private String snapshotTypeCode;
    private String snapshotFrequencyCode;
    private String restatementIndicator;
    private String taxableIndicator;
    private String asofDate;
    private String effectiveDate;
    private String valuationDate;
    private List<Position> positions;
}

// Position class
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    private String entityId;
    private int quantity;
    private int schwabSecurityId;
    private double marketValueLocal;
    private Security security;
}

// Security class
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Security {
    private String cusip;
    private int schwabSecurityId;
    private String ticker;
}

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SnapshotRowMapper implements RowMapper<Snapshot> {

    @Override
    public Snapshot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Snapshot snapshot = new Snapshot();
        snapshot.setEntityId(rs.getString("ENTITYID"));
        snapshot.setSnapshotSourceCode(rs.getString("SNAPSHOT_SOURCE_CODE"));
        snapshot.setSnapshotPointCode(rs.getString("SNAPSHOT_POINT_CODE"));
        snapshot.setSnapshotTypeCode(rs.getString("SNAPSHOT_TYPE_CODE"));
        snapshot.setSnapshotFrequencyCode(rs.getString("SNAPSHOT_FREQUENCY_CODE"));
        snapshot.setRestatementIndicator(rs.getString("RESTATEMENT_INDICATOR"));
        snapshot.setTaxableIndicator(rs.getString("TAXABLE_INDICATOR"));
        snapshot.setAsofDate(rs.getString("ASOF_DATE"));
        snapshot.setEffectiveDate(rs.getString("EFFECTIVE_DATE"));
        snapshot.setValuationDate(rs.getString("VALUATION_DATE"));

        // Set Positions (Will be mapped separately)
        List<Position> positions = new ArrayList<>();
        // Add logic to retrieve and map positions
        snapshot.setPositions(positions);
        
        return snapshot;
    }
}

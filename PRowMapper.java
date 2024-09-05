import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionRowMapper implements RowMapper<Position> {

    @Override
    public Position mapRow(ResultSet rs, int rowNum) throws SQLException {
        Position position = new Position();
        position.setEntityId(rs.getString("ENTITYID"));
        position.setQuantity(rs.getInt("quantity"));
        position.setSchwabSecurityId(rs.getInt("schwabSecurityId"));
        position.setMarketValueLocal(rs.getDouble("MarketValueLocal"));

        // Map Security object
        Security security = new Security();
        security.setCusip(rs.getString("cusip"));
        security.setSchwabSecurityId(rs.getInt("schwabSecurityId"));
        security.setTicker(rs.getString("ticker"));
        position.setSecurity(security);

        return position;
    }
}

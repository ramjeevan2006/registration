package com.example.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.StandardBasicTypes;

import java.sql.Types;

public class SnowflakeDialect extends Dialect {

    public SnowflakeDialect() {
        super();

        // Registering Snowflake-specific column types
        registerColumnType(Types.BOOLEAN, "BOOLEAN");
        registerColumnType(Types.BIGINT, "NUMBER(19,0)");
        registerColumnType(Types.BINARY, "BINARY");
        registerColumnType(Types.BLOB, "BINARY");
        registerColumnType(Types.CHAR, "CHAR(1)");
        registerColumnType(Types.DATE, "DATE");
        registerColumnType(Types.DOUBLE, "DOUBLE");
        registerColumnType(Types.FLOAT, "FLOAT");
        registerColumnType(Types.INTEGER, "NUMBER(10,0)");
        registerColumnType(Types.NUMERIC, "NUMBER($p,$s)");
        registerColumnType(Types.TIME, "TIME");
        registerColumnType(Types.TIMESTAMP, "TIMESTAMP_LTZ");  // Snowflake uses TIMESTAMP_LTZ for time zones
        registerColumnType(Types.TINYINT, "NUMBER(3,0)");
        registerColumnType(Types.VARBINARY, "BINARY");
        registerColumnType(Types.VARCHAR, "VARCHAR($l)");
        registerColumnType(Types.LONGVARCHAR, "TEXT");
        registerColumnType(Types.CLOB, "TEXT");
        registerColumnType(Types.DECIMAL, "NUMBER($p,$s)");

        // Registering Snowflake SQL functions
        registerFunction("concat", new VarArgsSQLFunction(StandardBasicTypes.STRING, "", "||", ""));
        registerFunction("substring", new StandardSQLFunction("substring", StandardBasicTypes.STRING));
        registerFunction("locate", new StandardSQLFunction("position", StandardBasicTypes.INTEGER));  // Snowflake uses POSITION instead of LOCATE
        registerFunction("trim", new SQLFunctionTemplate(StandardBasicTypes.STRING, "trim(?1)"));
        registerFunction("length", new StandardSQLFunction("length", StandardBasicTypes.INTEGER));
        registerFunction("bit_length", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "octet_length(?1) * 8"));  // Snowflake uses OCTET_LENGTH
        registerFunction("coalesce", new StandardSQLFunction("coalesce"));
        registerFunction("nullif", new StandardSQLFunction("nullif"));
        registerFunction("mod", new StandardSQLFunction("mod", StandardBasicTypes.INTEGER));
        registerFunction("current_date", new StandardSQLFunction("current_date", StandardBasicTypes.DATE));
        registerFunction("current_time", new StandardSQLFunction("current_time", StandardBasicTypes.TIME));
        registerFunction("current_timestamp", new StandardSQLFunction("current_timestamp", StandardBasicTypes.TIMESTAMP));
        registerFunction("str", new StandardSQLFunction("to_varchar", StandardBasicTypes.STRING));  // Snowflake uses TO_VARCHAR for string conversion
    }

    // Snowflake does not support dropping constraints separately from dropping tables
    @Override
    public boolean dropConstraints() {
        return false;
    }

    // Snowflake does not support ALTER TABLE for modifying columns in the same way as other databases
    @Override
    public boolean hasAlterTable() {
        return false;
    }

    // Snowflake does not require qualifying index names with table names
    @Override
    public boolean qualifyIndexName() {
        return false;
    }

    // Snowflake does not support sequences
    @Override
    public boolean supportsSequences() {
        return false;
    }

    // Snowflake does not support identity columns as other databases do
    @Override
    public String getIdentityColumnString() {
        return "not null";  // Identity columns are managed differently in Snowflake
    }

    @Override
    public String getIdentitySelectString() {
        return "select last_value from identifier('_identity')";
    }

    // Snowflake supports LIMIT and OFFSET for pagination
    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String sql, boolean hasOffset) {
        return sql + (hasOffset ? " limit ? offset ?" : " limit ?");
    }

    // Snowflake supports "IF EXISTS" syntax before and after table names
    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    @Override
    public boolean supportsIfExistsAfterTableName() {
        return true;
    }

    // Snowflake supports UNION ALL
    @Override
    public boolean supportsUnionAll() {
        return true;
    }

    // Snowflake supports temporary tables
    @Override
    public boolean supportsTemporaryTables() {
        return true;
    }

    @Override
    public String getCreateTemporaryTableString() {
        return "create temporary table";
    }

    @Override
    public boolean dropTemporaryTableAfterUse() {
        return true;
    }

    // Snowflake uses CURRENT_TIMESTAMP for getting the current time
    @Override
    public String getCurrentTimestampSelectString() {
        return "select current_timestamp()";
    }

    @Override
    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    @Override
    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }
}

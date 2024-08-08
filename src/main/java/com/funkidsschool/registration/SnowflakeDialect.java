package com.example.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.function.SqlFunction;
import org.hibernate.dialect.function.CastFunction;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.StandardBasicTypes;

import java.util.Map;

public class SnowflakeDialect extends Dialect {

    public SnowflakeDialect() {
        super(DatabaseVersion.make(1, 0));  // Specify the version if necessary

        // Registering Snowflake-specific column types
        registerColumnType(SqlTypes.BOOLEAN, "BOOLEAN");
        registerColumnType(SqlTypes.BIGINT, "NUMBER(19,0)");
        registerColumnType(SqlTypes.BINARY, "BINARY");
        registerColumnType(SqlTypes.BLOB, "BINARY");
        registerColumnType(SqlTypes.CHAR, "CHAR(1)");
        registerColumnType(SqlTypes.DATE, "DATE");
        registerColumnType(SqlTypes.DOUBLE, "DOUBLE");
        registerColumnType(SqlTypes.FLOAT, "FLOAT");
        registerColumnType(SqlTypes.INTEGER, "NUMBER(10,0)");
        registerColumnType(SqlTypes.NUMERIC, "NUMBER($p,$s)");
        registerColumnType(SqlTypes.TIME, "TIME");
        registerColumnType(SqlTypes.TIMESTAMP, "TIMESTAMP_LTZ");
        registerColumnType(SqlTypes.TINYINT, "NUMBER(3,0)");
        registerColumnType(SqlTypes.VARBINARY, "BINARY");
        registerColumnType(SqlTypes.VARCHAR, "VARCHAR($l)");
        registerColumnType(SqlTypes.LONGVARCHAR, "TEXT");
        registerColumnType(SqlTypes.CLOB, "TEXT");
        registerColumnType(SqlTypes.DECIMAL, "NUMBER($p,$s)");

        // Registering SQL functions
        registerFunction("concat", new SqlFunction("concat", StandardBasicTypes.STRING));
        registerFunction("substring", new SqlFunction("substring", StandardBasicTypes.STRING));
        registerFunction("position", new SqlFunction("position", StandardBasicTypes.INTEGER));
        registerFunction("trim", new SqlFunction("trim", StandardBasicTypes.STRING));
        registerFunction("length", new SqlFunction("length", StandardBasicTypes.INTEGER));
        registerFunction("octet_length", new SqlFunction("octet_length", StandardBasicTypes.INTEGER));
        registerFunction("coalesce", new SqlFunction("coalesce"));
        registerFunction("nullif", new SqlFunction("nullif"));
        registerFunction("mod", new SqlFunction("mod", StandardBasicTypes.INTEGER));
        registerFunction("current_date", new SqlFunction("current_date", StandardBasicTypes.DATE));
        registerFunction("current_time", new SqlFunction("current_time", StandardBasicTypes.TIME));
        registerFunction("current_timestamp", new SqlFunction("current_timestamp", StandardBasicTypes.TIMESTAMP));
        registerFunction("to_varchar", new SqlFunction("to_varchar", StandardBasicTypes.STRING));
    }

    // Snowflake does not support dropping constraints separately from dropping tables
    @Override
    public boolean dropConstraints() {
        return false;
    }

    @Override
    public boolean hasAlterTable() {
        return false;
    }

    @Override
    public boolean qualifyIndexName() {
        return false;
    }

    @Override
    public boolean supportsSequences() {
        return false;
    }

    @Override
    public String getIdentityColumnString() {
        return "not null";
    }

    @Override
    public String getIdentitySelectString() {
        return "select last_value from identifier('_identity')";
    }

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String sql, boolean hasOffset) {
        return sql + (hasOffset ? " limit ? offset ?" : " limit ?");
    }

    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    @Override
    public boolean supportsIfExistsAfterTableName() {
        return true;
    }

    @Override
    public boolean supportsUnionAll() {
        return true;
    }

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

    @Override
    public Map<String, SqlFunction> getFunctions() {
        return super.getFunctions();  // Return registered functions
    }
}

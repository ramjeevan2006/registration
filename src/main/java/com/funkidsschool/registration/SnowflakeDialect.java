package com.example.config;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.StandardFunction;
import org.hibernate.query.spi.QueryEngine;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.sql.spi.SqlTypeDescriptorRegistry;

public class SnowflakeDialect extends Dialect {

    public SnowflakeDialect() {
        super(DatabaseVersion.make(1, 0)); // Set the version if necessary
    }

    @Override
    public void initializeFunctionRegistry(QueryEngine queryEngine) {
        super.initializeFunctionRegistry(queryEngine);
        queryEngine.getSqmFunctionRegistry().register(
                "concat",
                new StandardFunction("concat", SqlTypes.STRING)
        );
        queryEngine.getSqmFunctionRegistry().register(
                "substring",
                new StandardFunction("substring", SqlTypes.STRING)
        );
        queryEngine.getSqmFunctionRegistry().register(
                "position",
                new StandardFunction("position", SqlTypes.INTEGER)
        );
        queryEngine.getSqmFunctionRegistry().register(
                "trim",
                new StandardFunction("trim", SqlTypes.STRING)
        );
        queryEngine.getSqmFunctionRegistry().register(
                "length",
                new StandardFunction("length", SqlTypes.INTEGER)
        );
        queryEngine.getSqmFunctionRegistry().register(
                "octet_length",
                new StandardFunction("octet_length", SqlTypes.INTEGER)
        );
        queryEngine.getSqmFunctionRegistry().register(
                "coalesce",
                new StandardFunction("coalesce")
        );
        queryEngine.getSqmFunctionRegistry().register(
                "nullif",
                new StandardFunction("nullif")
        );
        queryEngine.getSqmFunctionRegistry().register(
                "mod",
                new StandardFunction("mod", SqlTypes.INTEGER)
        );
        queryEngine.getSqmFunctionRegistry().register(
                "current_date",
                new StandardFunction("current_date", SqlTypes.DATE)
        );
        queryEngine.getSqmFunctionRegistry().register(
                "current_time",
                new StandardFunction("current_time", SqlTypes.TIME)
        );
        queryEngine.getSqmFunctionRegistry().register(
                "current_timestamp",
                new StandardFunction("current_timestamp", SqlTypes.TIMESTAMP)
        );
        queryEngine.getSqmFunctionRegistry().register(
                "to_varchar",
                new StandardFunction("to_varchar", SqlTypes.STRING)
        );
    }

    @Override
    public void initializeTypeContributions(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        super.initializeTypeContributions(typeContributions, serviceRegistry);
        SqlTypeDescriptorRegistry sqlTypeDescriptorRegistry = typeContributions.getSqlTypeDescriptorRegistry();

        // Registering Snowflake-specific SQL types
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.BOOLEAN, "BOOLEAN");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.BIGINT, "NUMBER(19,0)");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.BINARY, "BINARY");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.BLOB, "BINARY");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.CHAR, "CHAR(1)");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.DATE, "DATE");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.DOUBLE, "DOUBLE");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.FLOAT, "FLOAT");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.INTEGER, "NUMBER(10,0)");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.NUMERIC, "NUMBER($p,$s)");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.TIME, "TIME");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.TIMESTAMP, "TIMESTAMP_LTZ");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.TINYINT, "NUMBER(3,0)");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.VARBINARY, "BINARY");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.VARCHAR, "VARCHAR($l)");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.LONGVARCHAR, "TEXT");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.CLOB, "TEXT");
        sqlTypeDescriptorRegistry.addDescriptor(SqlTypes.DECIMAL, "NUMBER($p,$s)");
    }

    @Override
    public boolean dropConstraints() {
        return false; // Snowflake does not support dropping constraints separately from dropping tables
    }

    @Override
    public boolean hasAlterTable() {
        return false; // Snowflake does not support ALTER TABLE for modifying columns in the same way as other databases
    }

    @Override
    public boolean qualifyIndexName() {
        return false; // Snowflake does not require qualifying index names with table names
    }

    @Override
    public boolean supportsSequences() {
        return false; // Snowflake does not support sequences
    }

    @Override
    public String getIdentityColumnString() {
        return "not null"; // Identity columns are managed differently in Snowflake
    }

    @Override
    public String getIdentitySelectString() {
        return "select last_value from identifier('_identity')";
    }

    @Override
    public boolean supportsLimit() {
        return true; // Snowflake supports LIMIT and OFFSET for pagination
    }

    @Override
    public String getLimitString(String sql, boolean hasOffset) {
        return sql + (hasOffset ? " limit ? offset ?" : " limit ?");
    }

    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true; // Snowflake supports "IF EXISTS" syntax before table names
    }

    @Override
    public boolean supportsIfExistsAfterTableName() {
        return true; // Snowflake supports "IF EXISTS" syntax after table names
    }

    @Override
    public boolean supportsUnionAll() {
        return true; // Snowflake supports UNION ALL
    }

    @Override
    public boolean supportsTemporaryTables() {
        return true; // Snowflake supports temporary tables
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
}

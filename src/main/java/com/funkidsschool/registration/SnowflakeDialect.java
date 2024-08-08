package com.example.config;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.CommonFunctionFactory;
import org.hibernate.dialect.function.FunctionContributor;
import org.hibernate.query.sqm.function.SqmFunctionRegistry;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.JdbcTypeRegistry;

public class SnowflakeDialect extends Dialect {

    public SnowflakeDialect() {
        super(DatabaseVersion.make(1, 0));
    }

    @Override
    public void initializeFunctionRegistry(FunctionContributor functionContributor, SqmFunctionRegistry functionRegistry) {
        super.initializeFunctionRegistry(functionContributor, functionRegistry);

        // Registering common functions with Hibernate's CommonFunctionFactory
        CommonFunctionFactory.concat(functionRegistry);
        CommonFunctionFactory.substring(functionRegistry);
        CommonFunctionFactory.position(functionRegistry);
        CommonFunctionFactory.trim(functionRegistry);
        CommonFunctionFactory.length(functionRegistry);
        CommonFunctionFactory.octetLength(functionRegistry);
        CommonFunctionFactory.coalesce(functionRegistry);
        CommonFunctionFactory.nullif(functionRegistry);
        CommonFunctionFactory.mod(functionRegistry);
        CommonFunctionFactory.currentDate(functionRegistry);
        CommonFunctionFactory.currentTime(functionRegistry);
        CommonFunctionFactory.currentTimestamp(functionRegistry);
        CommonFunctionFactory.str(functionRegistry);  // Equivalent of to_varchar in Snowflake
    }

    @Override
    public void initializeTypeContributions(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        super.initializeTypeContributions(typeContributions, serviceRegistry);
        JdbcTypeRegistry jdbcTypeRegistry = typeContributions.getTypeConfiguration().getJdbcTypeRegistry();

        // Registering Snowflake-specific SQL types
        jdbcTypeRegistry.addDescriptor(SqlTypes.BOOLEAN, jdbcTypeRegistry.getDescriptor(SqlTypes.BOOLEAN));
        jdbcTypeRegistry.addDescriptor(SqlTypes.BIGINT, jdbcTypeRegistry.getDescriptor(SqlTypes.NUMERIC));
        jdbcTypeRegistry.addDescriptor(SqlTypes.BINARY, jdbcTypeRegistry.getDescriptor(SqlTypes.BINARY));
        jdbcTypeRegistry.addDescriptor(SqlTypes.BLOB, jdbcTypeRegistry.getDescriptor(SqlTypes.BLOB));
        jdbcTypeRegistry.addDescriptor(SqlTypes.CHAR, jdbcTypeRegistry.getDescriptor(SqlTypes.CHAR));
        jdbcTypeRegistry.addDescriptor(SqlTypes.DATE, jdbcTypeRegistry.getDescriptor(SqlTypes.DATE));
        jdbcTypeRegistry.addDescriptor(SqlTypes.DOUBLE, jdbcTypeRegistry.getDescriptor(SqlTypes.DOUBLE));
        jdbcTypeRegistry.addDescriptor(SqlTypes.FLOAT, jdbcTypeRegistry.getDescriptor(SqlTypes.FLOAT));
        jdbcTypeRegistry.addDescriptor(SqlTypes.INTEGER, jdbcTypeRegistry.getDescriptor(SqlTypes.INTEGER));
        jdbcTypeRegistry.addDescriptor(SqlTypes.NUMERIC, jdbcTypeRegistry.getDescriptor(SqlTypes.NUMERIC));
        jdbcTypeRegistry.addDescriptor(SqlTypes.TIME, jdbcTypeRegistry.getDescriptor(SqlTypes.TIME));
        jdbcTypeRegistry.addDescriptor(SqlTypes.TIMESTAMP, jdbcTypeRegistry.getDescriptor(SqlTypes.TIMESTAMP));
        jdbcTypeRegistry.addDescriptor(SqlTypes.TINYINT, jdbcTypeRegistry.getDescriptor(SqlTypes.TINYINT));
        jdbcTypeRegistry.addDescriptor(SqlTypes.VARBINARY, jdbcTypeRegistry.getDescriptor(SqlTypes.VARBINARY));
        jdbcTypeRegistry.addDescriptor(SqlTypes.VARCHAR, jdbcTypeRegistry.getDescriptor(SqlTypes.VARCHAR));
        jdbcTypeRegistry.addDescriptor(SqlTypes.LONGVARCHAR, jdbcTypeRegistry.getDescriptor(SqlTypes.LONGVARCHAR));
        jdbcTypeRegistry.addDescriptor(SqlTypes.CLOB, jdbcTypeRegistry.getDescriptor(SqlTypes.CLOB));
        jdbcTypeRegistry.addDescriptor(SqlTypes.DECIMAL, jdbcTypeRegistry.getDescriptor(SqlTypes.DECIMAL));
    }

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
}

package com.example.config;

import org.hibernate.dialect.Dialect;
import java.sql.Types;

public class SnowflakeDialect extends Dialect {

    public SnowflakeDialect() {
        super();
        registerColumnType(Types.VARCHAR, "STRING");
        registerColumnType(Types.BIGINT, "NUMBER(19,0)");
        registerColumnType(Types.BIT, "BOOLEAN");
        registerColumnType(Types.BOOLEAN, "BOOLEAN");
        registerColumnType(Types.CHAR, "CHAR(1)");
        registerColumnType(Types.DATE, "DATE");
        registerColumnType(Types.DOUBLE, "DOUBLE");
        registerColumnType(Types.FLOAT, "FLOAT");
        registerColumnType(Types.INTEGER, "NUMBER(10,0)");
        registerColumnType(Types.NUMERIC, "NUMBER($p,$s)");
        registerColumnType(Types.TIME, "TIME");
        registerColumnType(Types.TIMESTAMP, "TIMESTAMP_LTZ");
        registerColumnType(Types.TINYINT, "NUMBER(3,0)");
        registerColumnType(Types.VARBINARY, "BINARY");
        registerColumnType(Types.VARCHAR, "VARCHAR($l)");
    }
}

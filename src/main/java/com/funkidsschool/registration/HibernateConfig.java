package com.example.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory() {

        // Snowflake-specific connection string built from environment variables
        String driverConnectionString = "jdbc:snowflake://" +
                System.getenv("SNOWFLAKE_TEST_ACCOUNT") + ".snowflakecomputing.com/?" +
                "database=" + System.getenv("SNOWFLAKE_TEST_DATABASE") +
                "&schema=" + System.getenv("SNOWFLAKE_TEST_SCHEMA") +
                "&warehouse=" + System.getenv("SNOWFLAKE_TEST_WAREHOUSE") +
                "&AUTOCOMMIT=false" +
                "&JDBC_USE_SESSION_TIMEZONE=false" +
                "&TIMEZONE=UTC" +
                "&TIMESTAMP_TYPE_MAPPING=TIMESTAMP_NTZ";

        // Set Hibernate settings as per the onboarding doc
        Map<String, Object> settings = new HashMap<>();
        settings.put(Environment.DIALECT, "net.snowflake.hibernate.SnowflakeDialect");
        settings.put("hibernate.dialect.snowflake.table_type", "HYBRID");
        settings.put("hibernate.dialect.snowflake.development_mode", "false");
        settings.put(Environment.DRIVER, "net.snowflake.client.jdbc.SnowflakeDriver");
        settings.put(Environment.URL, driverConnectionString);
        settings.put(Environment.USER, System.getenv("SNOWFLAKE_USER"));
        settings.put(Environment.PASS, System.getenv("SNOWFLAKE_PASSWORD"));
        settings.put(Environment.SHOW_SQL, "false");
        settings.put(Environment.FORMAT_SQL, "false");
        settings.put("hibernate.highlight_sql", "true");
        settings.put("hibernate.use_sql_comments", "false");
        settings.put("hibernate.hbm2ddl.auto", "validate");
        settings.put("hibernate.auto_quote_keyword", "true");

        // Auto-commit and connection provider settings as per onboarding doc
        settings.put(Environment.AUTOCOMMIT, "false");
        settings.put("hibernate.connection.provider_disables_autocommit", "true");
        settings.put(Environment.GENERATE_STATISTICS, "false");

        // ServiceRegistry and MetadataSources setup
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings)
                .build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        // Add all your entity classes here (e.g., Security.class)
        metadataSources.addAnnotatedClass(Security.class);

        Metadata metadata = metadataSources.buildMetadata();

        return metadata.buildSessionFactory();
    }
}

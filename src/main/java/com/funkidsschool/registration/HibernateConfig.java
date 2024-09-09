import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory() {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.connection.autocommit", "false")
                .applySetting("hibernate.connection.provider_disables_autocommit", "true")
                .applySetting("hibernate.dialect", "net.snowflake.hibernate.SnowflakeDialect")
                .applySetting("hibernate.dialect.snowflake.table_type", "HYBRID")
                .applySetting("hibernate.dialect.snowflake.development_mode", "false")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Security.class)  // Add your annotated entity class
                .buildMetadata();

        return metadata.buildSessionFactory();
    }
}

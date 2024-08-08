import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("classpath:${spring.datasource.private-key-file}")
    private Resource privateKeyFile;

    @Value("classpath:${spring.datasource.passphrase-file}")
    private Resource passphraseFile;

    @Bean
    public DataSource dataSource() throws IOException, GeneralSecurityException {
        String privateKeyContent = new String(Files.readAllBytes(privateKeyFile.getFile().toPath()));
        System.out.println("Raw Private Key Content: " + privateKeyContent); // Debugging statement
        privateKeyContent = privateKeyContent
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", ""); // Remove all whitespace characters

        System.out.println("Processed Private Key Content: " + privateKeyContent); // Debugging statement

        String passphrase = new String(Files.readAllBytes(passphraseFile.getFile().toPath())).trim();
        System.out.println("Passphrase: " + passphrase); // Debugging statement

        // Decode the private key
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // Setup properties
        Properties properties = new Properties();
        properties.put("user", username);
        properties.put("privateKey", privateKey);
        properties.put("passphrase", passphrase);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setConnectionProperties(properties);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

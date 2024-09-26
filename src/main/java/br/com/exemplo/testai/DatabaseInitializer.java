package br.com.exemplo.testai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             InputStream inputStream = getClass().getResourceAsStream("/data.sql");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            StringBuilder sqlBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sqlBuilder.append(line).append("\n");
            }

            statement.executeUpdate(sqlBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
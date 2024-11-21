package dev.biddan.awssecretmanager;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class AwsSecretmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsSecretmanagerApplication.class, args);
    }

    @Service
    static class DBConfigService implements CommandLineRunner {
        private final JdbcTemplate jdbcTemplate;

        @Autowired
        public DBConfigService(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public void run(String... args) {
            try {
                System.out.println("데이터베이스 연결 테스트:");

                String sql = "SELECT 1";
                List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);;

                results.forEach(System.out::println);
            } catch (Exception e) {
                System.err.println("데이터베이스 조회 실패: " + e.getMessage());
            }
        }
    }
}

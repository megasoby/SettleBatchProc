package io.megasoby.settlebatchproc;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SettleBatchProcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SettleBatchProcApplication.class, args);
    }

}

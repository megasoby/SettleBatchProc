package io.megasoby.settlebatchproc;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.StoredProcedureItemReader;
import org.springframework.batch.item.database.builder.StoredProcedureItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.SqlParameter;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

@RequiredArgsConstructor
@Configuration
public class jdbcProcConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private int chunkSize = 10;

    @Bean
    public Job Job() {
        return this.jobBuilderFactory.get("procJob")
                .start(procStep1())
                .build();
    }

    @Bean
    public Step procStep1() {
        return this.stepBuilderFactory.get("procStep1")
                .<TB1, TB1>chunk(chunkSize)
//                .reader(customerItemReader(dataSource,null,null))
                .reader(customerItemReader(dataSource))
                .writer(customItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public StoredProcedureItemReader<TB1> customerItemReader(DataSource dataSource) {

//        public StoredProcedureItemReader<TB1> customerItemReader(DataSource dataSource,
//                @Value("#{jobParameter['name']}") String name,
//                @Value("#{jobParameter['age']}") Integer age) {

        return new StoredProcedureItemReaderBuilder<TB1>()
                .name("customerItemReader")
                .dataSource(dataSource)
                .procedureName("PROC_TEST")
                .parameters(new SqlParameter[]{new SqlParameter("name", Types.VARCHAR), new SqlParameter("age", Types.INTEGER)})
                .preparedStatementSetter(new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, "powerbhs");
                        ps.setInt(2, 48);
                    }
                })
//                .preparedStatementSetter(new ArgumentPreparedStatementSetter(new Object[]{name, age}))
                .rowMapper(new Tb1RowMapper())
                .build();
    }

    @Bean
    @StepScope
    public ItemWriter<TB1> customItemWriter() {
        return items -> {
            for (TB1 item : items) {
                System.out.println(item.toString());
            }
        };
    }
}

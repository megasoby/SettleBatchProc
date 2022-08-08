package io.megasoby.settlebatchproc;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SimpleJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleSettleJob() {
        return this.jobBuilderFactory.get("simpleSettleJob")
                .start(simpleSettleStep1())
                .next(simpleSettleStep2())
                .build();
    }

    @Bean
    public Step simpleSettleStep1() {
        return this.stepBuilderFactory.get("simpleSettleStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        System.out.println("============================");
                        System.out.println(" >> simpleSettleStep1 running!");
                        System.out.println("============================");

                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step simpleSettleStep2() {
        return this.stepBuilderFactory.get("simpleSettleStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        System.out.println("============================");
                        System.out.println(" >> simpleSettleStep2 running!");
                        System.out.println("============================");

                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

}

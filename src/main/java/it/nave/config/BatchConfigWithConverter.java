package it.nave.config;

import it.nave.dto.DummyDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.transaction.PlatformTransactionManager;

@Profile("with-converter")
@Configuration
public class BatchConfigWithConverter extends DefaultBatchConfiguration {
    @Bean
    @StepScope
    public IdentityReader itemReader(@Value("#{jobParameters['dummy-dto']}") DummyDto dummyDto) {
        return new IdentityReader(dummyDto);
    }

    @Bean
    public ItemWriter<DummyDto> itemWriter() {
        return chunk -> System.out.println("Dummy received: " + chunk.getItems().get(0));
    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManager,
                     ItemReader<DummyDto> itemReader,
                     ItemWriter<DummyDto> itemWriter) {
        return new StepBuilder("dummy-step", jobRepository)
                .<DummyDto, DummyDto>chunk(10, transactionManager)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("dummy-job", jobRepository)
                .start(step)
                .build();
    }

    @Override
    protected ConfigurableConversionService getConversionService() {
        var conversionService = super.getConversionService();
        conversionService.addConverter(new DummyDtoToStringConverter());
        return conversionService;
    }

    public static class DummyDtoToStringConverter implements Converter<DummyDto, String> {
        @Override
        public String convert(DummyDto source) {
            return source.toString();
        }
    }

    public static class IdentityReader implements ItemReader<DummyDto> {
        private final DummyDto dummyDto;

        public IdentityReader(DummyDto dummyDto) {
            this.dummyDto = dummyDto;
        }

        @Override
        public DummyDto read() {
            return dummyDto;
        }
    }
}
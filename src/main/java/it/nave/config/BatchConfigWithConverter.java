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

@Profile("add-converter")
@Configuration
public class BatchConfigWithConverter extends DefaultBatchConfiguration {
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
}

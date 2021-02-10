package ro.inf.ucv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableFeignClients("ro.inf.ucv")
@EnableDiscoveryClient
@EnableElasticsearchRepositories
public class SuggesterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuggesterServiceApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
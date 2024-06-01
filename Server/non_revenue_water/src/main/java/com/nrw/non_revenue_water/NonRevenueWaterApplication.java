package com.nrw.non_revenue_water;

import java.io.File;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditingImpl")
@EnableAsync
public class NonRevenueWaterApplication implements CommandLineRunner {
	@Value("${upload.file.name}")
	private String uploadFileLocation;

	public static void main(String[] args) {
		SpringApplication.run(NonRevenueWaterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var file = new File(uploadFileLocation);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("nrw-");
		executor.initialize();
		return executor;
	}

}

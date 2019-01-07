package com.santandertecnologia.testbackjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@SpringBootApplication
public class TestbackjavaApplication {

	private RedisServer redisServer;

	public static void main(String[] args) {
		SpringApplication.run(TestbackjavaApplication.class, args);
	}

	@PostConstruct
	public void onStartup() throws IOException {
		redisServer = new RedisServer(6379);
		redisServer.start();
	}

	@PreDestroy
	public void onShutdown() {
		redisServer.stop();
	}

}


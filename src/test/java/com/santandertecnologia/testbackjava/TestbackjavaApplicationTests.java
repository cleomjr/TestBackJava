package com.santandertecnologia.testbackjava;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.embedded.RedisServer;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestbackjavaApplicationTests {

	private static RedisServer redisServer;

	@BeforeClass
	public static void onSetup() throws IOException {
		redisServer = new RedisServer(6379);
		redisServer.start();
	}

	@AfterClass
	public static void onDestroy() {
		redisServer.stop();
	}

	@Test
	public void contextLoads() {
	}

}


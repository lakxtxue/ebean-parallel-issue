package com.ebean.test;


import io.ebean.*;
import io.ebean.config.DatabaseConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.*;

public class EBeanTests {

	private Database database;

	@Before
	public void before(){
		DatabaseConfig config = new DatabaseConfig();
		config.loadFromProperties();
		config.setName("db");
		config.setDefaultServer(true);
		database = DatabaseFactory.create(config);
	}

	@Test
	public void testDevice() throws ExecutionException, InterruptedException {
		ExecutorService myExecutorService = Executors.newFixedThreadPool(2);
		Future<Device> future = myExecutorService.submit(()->{
			Device one = database.find(Device.class,"1");
			one.setEnabled("Y");
			System.out.println(Thread.currentThread().getName());
			return one;
		});

		Future<Device> future1 = myExecutorService.submit(()->{
			Device one = database.find(Device.class,"1");
			one.setEnabled("Y");
			System.out.println(Thread.currentThread().getName());
			return one;
		});


		Future<Device> future2 = myExecutorService.submit(()->{
			Device one = database.find(Device.class,"1");
			one.setEnabled("N");
			System.out.println(Thread.currentThread().getName());
			return one;
		});

		Device device = future.get();
		database.save(device);

		device = future1.get();
		database.save(device);

		device = future2.get();
		database.save(device);

		Device one = database.find(Device.class,"1");
		Assert.assertEquals("N",one.getEnabled());
	}
}

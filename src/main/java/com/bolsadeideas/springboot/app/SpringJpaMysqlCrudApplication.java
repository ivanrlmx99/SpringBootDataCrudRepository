package com.bolsadeideas.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bolsadeideas.springboot.app.models.service.IUploadFileService;

@SpringBootApplication
public class SpringJpaMysqlCrudApplication implements CommandLineRunner{
	
	@Autowired
	IUploadFileService iuploadService;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaMysqlCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		iuploadService.deleteAll();
		iuploadService.init();
		
		
	}

}

package br.com.jean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jean.services.S3Service;

@SpringBootApplication
public class LojaspringbootApplication implements CommandLineRunner{
	
	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(LojaspringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//s3Service.uploadFile("D:\\projetos\\java udemy\\1cc481c0-d28a-4f01-814f-1d9c0d3d2a26.jpeg");
	}

}










package com.douglas.develop.radio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.douglas.develop.radio.service.EmailService;

import lombok.AllArgsConstructor;

@EnableFeignClients
@SpringBootApplication
@AllArgsConstructor
public class DemoSecurityApplication implements CommandLineRunner {

	public static void main(String[] args) {
		
		//System.out.println(new BCryptPasswordEncoder().encode("root"));
		
		SpringApplication.run(DemoSecurityApplication.class, args);
	}
	
	//@Autowired
	//private JavaMailSender sender;
	
	private final EmailService service;

	@Override
	public void run(String... args) throws Exception {
		
		
		
//		SimpleMailMessage simple = new SimpleMailMessage();
//		simple.setTo("douglas@gmail.com");
//		simple.setText("Teste numero 1");
//		simple.setSubject("teste 1");
//		
//		sender.send(simple);
		
		//service.enviarPedidoDeConfirmacaoDeCadastro("douglas.gmail.com", "95126Xpto");
		
	}
}

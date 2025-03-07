package dev.java10x.cadastrobasico;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CadastroBasicoApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		SpringApplication.run(CadastroBasicoApplication.class, args);
		String dbUrl = dotenv.get("DATABASE_URL");
		String dbUsername = dotenv.get("DATABASE_USERNAME");
		String dbPassword = dotenv.get("DATABASE_PASSWORD");

	}

}

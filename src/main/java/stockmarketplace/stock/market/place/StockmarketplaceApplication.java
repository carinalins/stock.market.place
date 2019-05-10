package stockmarketplace.stock.market.place;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class StockmarketplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockmarketplaceApplication.class, args);
	}

}

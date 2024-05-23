package be.post.simulator.shopify;

import be.post.simulator.shopify.service.ShiptimizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.core.io.ResourceLoader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.nio.charset.Charset;

@EnableScheduling
@ComponentScan(basePackages = "be.post.simulator.shopify")
@SpringBootApplication
public class ShopifyOrderGeneratorApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopifyOrderGeneratorApplication.class);

	@Autowired
	private ShiptimizeService shiptimizeService;
	@Autowired
	ResourceLoader resourceLoader;



	public static void main(String[] args) {
		SpringApplication.run(ShopifyOrderGeneratorApplication.class, args);
	}

	private int ordernumber = 0;

	@Scheduled(fixedDelay = 6000)
	public void testCallToShopify() throws IOException {

		Resource resource = resourceLoader.getResource("classpath:shopifyOrder.json");
		String shopifyRawOrder = resource.getContentAsString(Charset.forName("UTF-8"));
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyyhhmmssSS");
		String orderNumber = "#peeteko" + sd.format(date);
		shopifyRawOrder = shopifyRawOrder.replace("#1009", orderNumber);

        shiptimizeService.createOrder(shopifyRawOrder);

    }
}

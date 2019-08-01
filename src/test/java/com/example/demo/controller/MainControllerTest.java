package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class MainControllerTest {
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private MockMvc mvc;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Before
	public void setUp() throws Exception {
		Product iPhoneX = new Product(9999, 999.0, "64GB, iOS 11, space gray",
				"iPhone X", "Apple", "Smart Phones",
				"/image/1/iphonexfrontback-800x573.jpg");
		Product iPhone8 = new Product(9999, 799.0, "64GB, iOS 11, Silver",
				"iPhone 8", "Apple", "Smart Phones",
				"/image/2/iphone8-silver-select-2017.jpg");
		Product C7OLED = new Product(9999, 3000.00, "65\" Smart TV",
				"C7 OLED", "LG", "Televisions",
				"/image/3/C7_ST_Desktop_Front.jpg");
		Product MacbookPro = new Product(15000, 2800.00, "15\" laptop, 512GB SSD",
				"Macbook Pro", "Apple", "Computers",
				"/image/4/apple_mlh32ll_a_15_4_macbook_pro_with_1293726.jpg");
		productService.save(iPhoneX);
		productService.save(iPhone8);
		productService.save(C7OLED);
		productService.save(MacbookPro);
		User user = new User();
		userService.saveNew(user);
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void main() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("main"));
		System.out.println(model().attribute("products", "products"));
//				.andExpect(model().attribute("products",
//						hasItem(hasProperty("category", (Matcher<?>) is("Smart Phones")))));
	}


	@Test
	public void filter() {
	}

	@Test
	public void about() throws Exception {
		mvc.perform(get("/about"))
				.andExpect(status().isOk())
				.andExpect(view().name("about"));
	}


}
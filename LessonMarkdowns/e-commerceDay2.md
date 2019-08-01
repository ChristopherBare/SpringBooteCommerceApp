# Day 2

## Standup

- What did we learn from day 1
- What is left from day 1 if you didn't finish
- What were your challenges
- Any impediments 

## Planning Meeting

- What are we going to accomplish on day 2?
    - Create strategy for what will be done and how. 

### *__First Break__*

## Review Homework

- Which tests failed?
    - Why?

## Work on it

- Set up Database
    - Models
        - User
            ```java
              @Data
              @NoArgsConstructor
              @Entity
              public class User implements UserDetails {
              	@GeneratedValue
              	@Id private long id;
              	
              	@NonNull
              	private String username, password;
              	
              	// TODO: Maybe don't do this until we're making the CartController
              	@ElementCollection
              	Map<Product, Integer> cart = new HashMap<>();
              	
              
              	// UserDetails requires these, they are technically getters (is-ers?) overridden by Lombok.
              	// @Transient Makes it so these aren't persisted in the database, as they are hard coded.
              	@Transient
              	private boolean accountNonExpired = true;
              	@Transient
              	private boolean accountNonLocked = true;
              	@Transient
              	private boolean credentialsNonExpired = true;
              	@Transient
              	private boolean enabled = true;
              	@Transient
              	private Collection<GrantedAuthority> authorities = null;
              }


            ```
        - Product
            ```java
              import lombok.*;
              
              import javax.persistence.Entity;
              import javax.persistence.GeneratedValue;
              import javax.persistence.GenerationType;
              import javax.persistence.Id;
              
              @AllArgsConstructor
              @NoArgsConstructor
              @RequiredArgsConstructor
              @Entity
              @Data
              public class Product {
                  @Id
                  @GeneratedValue(strategy = GenerationType.IDENTITY)
                  private long id;
              
                  @NonNull private int quantity;
                  @NonNull private double price;
                  @NonNull private String description, name, brand, category, image;
              }

            ```
    - Repo
        - ProductRepository
            ```java
              @Repository
              public interface ProductRepository extends CrudRepository<Product, Long> {
                  List<Product> findAll();
                  Product findById(long id);
                  List<Product> findByBrand(String brand);
                  List<Product> findByCategory(String category);
                  List<Product> findByBrandAndCategory(String brand, String category);
                  
                  @Query("SELECT DISTINCT p.brand FROM Product p")
                  List<String> findDistinctBrands();
                  
                  @Query("SELECT DISTINCT p.category FROM Product p")
                  List<String> findDistinctCategories();
          }
            ```
          - UserRepository
            ```java
                @Repository
                public interface UserRepository extends CrudRepository<User, Long> {
                	User findByUsername(String username);
                }

            ```
    - Services
- Set up User Authentication
    - SecurityConfiguration
        ```java
          @Configuration
          @EnableWebSecurity
          public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
          	@Autowired
          	private UserService userService;
          	@Autowired
          	private BCryptPasswordEncoder bCryptPasswordEncoder;
          	
          	@Override
          	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
          	}
          	
          	@Override
          	protected void configure(HttpSecurity http) throws Exception {
          		http
          			.authorizeRequests()
          				.antMatchers("/cart").authenticated()
          			.and().formLogin()
          				.loginPage("/signin")
          				.loginProcessingUrl("/login")
          			.and().logout()
          				.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
          				.logoutSuccessUrl("/");
          	}
          }

        ```
      - WebMvcConfiguration
        ```java
            @Configuration
            public class WebMvcConfiguration {
            	@Bean
            	public BCryptPasswordEncoder passwordEncoder() {
            		return new BCryptPasswordEncoder();
            	}
            }

        ```
## Authentication 
- AuthenticationController
    ```java
      @Controller
      class AuthenticationController {
      	@Autowired
      	private UserService userService;
      	
      	@GetMapping("/signin")
      	public String login() {
      		return "signin";
      	}
      	
      	@PostMapping("/signin")
      	public String singup(@Valid User user,
      	                     @RequestParam String submit,
      	                     BindingResult bindingResult,
      	                     HttpServletRequest request) throws ServletException {
      		String password = user.getPassword();
      		if(submit.equals("up")) {
      			if(userService.findByUsername(user.getUsername()) == null) {
      				userService.saveNew(user);
      			} else {
      				bindingResult.rejectValue("username", "error.user", "Username is already taken.");
      				return "signin";
      			}
      		}
      		request.login(user.getUsername(), password);
      		return "redirect:/";
      	}
      }

    ```

### *__Lunch__*

## Cart
- CartController
    ```java
      @Controller
      @ControllerAdvice
      public class CartController {
      	@Autowired
      	ProductService productService;
      	
      	@Autowired
      	UserService userService;
      	
      	@ModelAttribute("loggedInUser")
      	public User loggedInUser() {
      		return userService.getLoggedInUser();
      	}
      	
      	@ModelAttribute("cart")
      	public Map<Product, Integer> cart() {
      		User user = loggedInUser();
      		if(user == null) return null;
      		System.out.println("Getting cart");
      		return user.getCart();
      	}
      	
      	/**
      	 * Puts an empty list in the model that thymeleaf can use to sum up the cart total.
      	 */
      	@ModelAttribute("list")
      	public List<Double> list() {
      		return new ArrayList<>();
      	}
      	
      	@GetMapping("/cart")
      	public String showCart() {
      		return "cart";
      	}
      	
      	@PostMapping("/cart")
      	public String addToCart(@RequestParam long id) {
      		Product p = productService.findById(id);
      		setQuantity(p, cart().getOrDefault(p, 0) + 1);
      		return "cart";
      	}
      	
      	@PatchMapping("/cart")
      	public String updateQuantities(@RequestParam long[] id, @RequestParam int[] quantity) {
      		for(int i = 0; i < id.length; i++) {
      			Product p = productService.findById(id[i]);
      			setQuantity(p, quantity[i]);
      		}
      		return "cart";
      	}
      	
      	@DeleteMapping("/cart")
      	public String removeFromCart(@RequestParam long id) {
      		Product p = productService.findById(id);
      		setQuantity(p, 0);
      		return "cart";
      	}
      	
      	private void setQuantity(Product p, int quantity) {
      		if(quantity > 0)
      			cart().put(p, quantity);
      		else
      			cart().remove(p);
      		
      		userService.updateCart(cart());
      	}
      }

    ```

## Dynamic Products List

- Set up Dynamic Products
    - In the main.html file, make the static list of products pull from the database and list them out with just a single block. Rather than having 4 cards. 
    
## Finish all extraneous routing
- Link all the HTML pages together
    - Put in the appropriate links for the pages to work together.
    - Use the test files to help you determine that all routes work.

# Retrospective
   - What did we learn
   - Any improvements going forward
   - Did everyone get it done 
   - etc.

# *__HomeWork:__*
- Do JUnit testing
- Do integration & regression testing

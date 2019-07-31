# Springboot 

## Intro

So you want to use java in an MVC framework to make web applications? Springboot is your framework!

## What is Springboot?
Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run". We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. Most Spring Boot applications need very little Spring configuration.

### Features:

    Create stand-alone Spring applications

    Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)

    Provide opinionated 'starter' dependencies to simplify your build configuration

    Automatically configure Spring and 3rd party libraries whenever possible

    Provide production-ready features such as metrics, health checks and externalized configuration

    Absolutely no code generation and no requirement for XML configuration

## Spring MVC vs Springboot

![img](https://i.stack.imgur.com/R1ktG.gif)

## Creating the Project

Steps to recreate:
 - Open IntelliJ's new Project pane
 - Select `Spring Initializr` from the sidebar
 - Fill out the group, artifact, and Name fields. 
 - Select these dependencies:
   - Core
     - Devtools
     - Lombok
     - Validation
   - Web
     - Web
   - Template Engines
     - Thymeleaf
   - Security
     - Security
     - OAuth2 Client
     - OAuth2 Resource Server
   - SQL
     - JPA
     - H2

Name the project and that's it. Hit finish!

## Setting up the project 

Create three html files under the templates folder:
- index
- navbar
- footer

We will add more later but those are all for now. 

Here is an example footer file: 

```html
<div th:fragment="footer">
    <footer class="container">
        <p>&copy; Company 2017</p>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </footer>
</div>
```

Then in the index file, you can import it like this:

```html
<div th:insert="fragments/footer :: footer"/>
```
## Thymeleaf 

Thymeleaf is a modern server-side Java template engine for both web and standalone environments.

Thymeleaf's main goal is to bring elegant natural templates to your development workflow — HTML that can be correctly displayed in browsers and also work as static prototypes, allowing for stronger collaboration in development teams.

With modules for Spring Framework, a host of integrations with your favourite tools, and the ability to plug in your own functionality, Thymeleaf is ideal for modern-day HTML5 JVM web development — although there is much more it can do.

Example:

```html
<table>
  <thead>
    <tr>
      <th th:text="#{msgs.headers.name}">Name</th>
      <th th:text="#{msgs.headers.price}">Price</th>
    </tr>
  </thead>
  <tbody>
    <tr th:each="prod: ${allProducts}">
      <td th:text="${prod.name}">Oranges</td>
      <td th:text="${#numbers.formatDecimal(prod.price, 1, 2)}">0.99</td>
    </tr>
  </tbody>
</table>
```

## Exercise 1

Build out the eCommerce app:

Add the rest of the html for the site.

Give it a fixed top navbar, of any color. You can give the navbar the following links and dropdowns: 
- Find by Category
- Find By Brand
- My Cart (fixed right)
- Settings (fixed right)

Add a Jumbotron that has some text in it. 

Add 3-5 Cards of things to sell with an image, card title, and card body. Include a button that will later open a modal. 

### JPA

Java Persistence API is a collection of classes and methods to persistently store the vast amounts 
of data into a database. This tutorial provides you the basic understanding of Persistence (storing 
the copy of database object into temporary memory), and we will learn the understanding of JAVA 
Persistence API (JPA).

### Hibernate

Hibernate is an Object-Relational Mapping (ORM) solution for JAVA. It is an open source persistent 
framework created by Gavin King in 2001. It is a powerful, high performance Object-Relational 
Persistence and Query service for any Java Application.

Hibernate maps Java classes to database tables and from Java data types to SQL data types and 
relieves the developer from 95% of common data persistence related programming tasks.

Hibernate sits between traditional Java objects and database server to handle all the works 
in persisting those objects based on the appropriate O/R mechanisms and patterns.

### Lombok

Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
Never write another getter or equals method again, with one annotation your class has a fully featured builder, 
Automate your logging variables, and much more. 

Lombok essentially will use `@Data` to do everything for you. You can also use `@AllArgsConstructor` and `@NoArgsConstructor` to 
create all of the default constructors that you require without typing anything more than the annotations. 

### Creating the Model

Here is the MySQL code for the database that you need to create: 

```MySQL
CREATE DATABASE ecommerce;

USE ecommerce;

CREATE TABLE Product (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(500) NOT NULL,
    description VARCHAR(5000) NOT NULL,
    price FLOAT NOT NULL,
    quantity INT NOT NULL,
    brand VARCHAR(500),
    rating INT NOT NULL,
    image VARCHAR(5000) NOT NULL,
    category_name VARCHAR(500),
    foreign key (category_name) references Category(name)
);

CREATE TABLE Category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(500)
);

CREATE TABLE LineItem (
    id INT PRIMARY KEY,
    quantity INT NOT NULL,
    lineItemTotal DECIMAL NOT NULL,
    orderId INT NOT NULL
);

CREATE TABLE `Order` (
    subtotal DECIMAL,
    salesTax DECIMAL,
    grandTotal DECIMAL,
    userId INT NOT NULL PRIMARY KEY,
    orderItems VARCHAR(500),
    foreign key (userId) references User(id)
);

CREATE TABLE User (
    id int auto_increment not null primary key,
    email VARCHAR(500) NOT NULL,
    encryptedPassword VARCHAR(500) NOT NULL,
    resetPasswordToken VARCHAR(500),
    rememberCreatedAt DATETIME,
    signInCount INT NOT NULL DEFAULT 0,
    currentSignInAt DATETIME,
    lastSignInAt DATETIME,
    currentSignInIP VARCHAR(500),
    lastSignInIP VARCHAR(500),
    role VARCHAR(500),
    name VARCHAR(500),
    birthday DATE,
    address VARCHAR(5000),
    city VARCHAR(500),
    zip VARCHAR(500),
    state VARCHAR(500)
);

INSERT INTO Product (name, description, price, quantity, brand, rating, image)
VALUES ('iPhone X', '64GB, iOS 11, space gray', 999.00, 9999, 'Apple', 5, '/image/1/iphonexfrontback-800x573.jpg'),
       ('iPhone 8', '64GB, iOS 11, silver', 799.00, 9999, 'Apple', 5, '/image/2/iphone8-silver-select-2017.jpg'),
       ('C7 OLED', '65" Smart TV', 3000, 9999, 'LG', 5, '/image/3/C7_ST_Desktop_Front.jpg'),
       ('Macbook Pro', '15 laptop, 512GB SSD', 2800, 15000, 'Apple', 5,  '/image/4/apple_mlh32ll_a_15_4_macbook_pro_with_1293726.jpg');

INSERT INTO Category (name)
VALUES ('Computers'),
       ('Televisions'),
       ('Smart Phones'),
       ('Stereos'),
       ('Other');
```


You will need to make java classes for each table with the appropriate columns 
inside the classes so that they will be created correctly. 


## Exercise 2:

Create the model from the SQL script above. 

Here is an example Model for the Product table: 

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "Category", foreignKey = @ForeignKey(name = "FK_CategoryId"))
    private Category category;
    private double price;
    private int quantity, rating;
    private String description, name, brand, image;

    public Product(double price, int quantity, String description, String name,
                   String brand, int rating, String image, String categoryName) {
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.name = name;
        this.brand = brand;
        this.rating = rating;
        this.image = image;
        this.category = new Category(categoryName);
    }
}
```
#*Outline*
### Day 1

- Scrum Intro
- Planning Meeting
    - User Story
    - Agile

*__Lunch__*
- Set up Project
    - Layout all the dependencies
- Set up all the HTML pages
- Make the main controller
- Do a Retrospective


*__HomeWork:__*
- Do JUnit Testing
    - Some will fail  

##Day 2

- Do a StandUp
- Review HomeWork
- Planning Meeting

*__First Break__*
- Set up Data Base
    - Models
    - Repo
    - Services
- Set up User Authentication
- Do the login page logic
- Do the user cart logic

*__Lunch__*
- Set up Dynamic Products
- Link all the HTML pages together
- Do Retrospective

*__HomeWork:__*
- Do JUnit testing
- Do integration & regression testing

## Day 3

- Stand Up
- Review Homework
- Planning Meeting

*__First Break__*

- Adding the buttons and filtering logic to the navbar 
- Adding a numbering logic to the cart in the navbar

*__Lunch__*

- Discuss style guide
- Finalize Project
    - Full test of the entire site
- Demo Projects    


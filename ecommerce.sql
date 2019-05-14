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
    image VARCHAR(5000) NOT NULL
);

CREATE TABLE Categories (
    categoryId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(500)
);

CREATE TABLE LineItems (
    productId INT PRIMARY KEY,
    quantity INT NOT NULL,
    lineItemTotal DECIMAL NOT NULL,
    orderId INT NOT NULL
);

CREATE TABLE Orders (
    subtotal DECIMAL,
    salesTax DECIMAL,
    grandTotal DECIMAL,
    userId INT NOT NULL PRIMARY KEY,
    orderItems VARCHAR(500)
);

CREATE TABLE Users (
    email VARCHAR(500) NOT NULL PRIMARY KEY,
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
VALUES ("iPhone X", "64GB, iOS 11, space gray", 999.00, 9999, "Apple", 5, "/image/1/iphonexfrontback-800x573.jpg"),
       ("iPhone 8", "64GB, iOS 11, silver", 799.00, 9999, "Apple", 5, "/image/2/iphone8-silver-select-2017.jpg"),
       ("C7 OLED", '65" Smart TV', 3000, 9999, "LG", 5, "/image/3/C7_ST_Desktop_Front.jpg"),
       ("Macbook Pro", '15" laptop, 512GB SSD', 2800, 15000, "Apple", 5,  "/image/4/apple_mlh32ll_a_15_4_macbook_pro_with_1293726.jpg");

INSERT INTO Categories (name)
VALUES ("Computers"),
       ("Televisions"),
       ("Smart Phones"),
       ("Stereos"),
       ("Other");
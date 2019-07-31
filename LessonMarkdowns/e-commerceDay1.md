# Day 1

## Scrum Intro
   - With Maestro Ben
## Planning Meeting
   - User Story
   - Agile

## *__Lunch__*

# Set up Project
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
   - SQL
     - JPA
     - H2

- Set up all the HTML pages

### main

```html
  <!DOCTYPE HTML>
  <html xmlns:th="http://www.thymeleaf.org" lang="en">
  <head th:replace="fragments :: head"></head>
  <body>
  <div th:replace="fragments :: navbar" ></div>
  
  <main role="main">
      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
          <div class="container">
              <h1 class="display-3">Tech Talent Store</h1>
              <p>This is a small e-commerce application that allows the user to purchase some of a few products that are offered here. </p>
              <p><a class="btn btn-primary btn-lg" href="/about" role="button">Learn more &raquo;</a></p>
          </div>
      </div>
  
      <div class="container">
          <div class="row">
              <!--Fill this part in-->
          </div>
      </div>
  </main>
  
  <footer th:replace="fragments :: footer"></footer>
  </body>
  </html>

```
### cart

```html

  <!DOCTYPE HTML>
  <html xmlns:th="http://www.thymeleaf.org" lang="en">
  <head th:replace="fragments :: head"></head>
  <body>
  <div th:replace="fragments :: navbar" ></div>
  
  <!--/*@thymesVar id="cart" type="java.util.Map<com.example.demo.model.Product, java.lang.Integer>"*/-->
  <main role="main">
      <div class="container">
          <div class="row">
              <table class="table">
                  <thead>
                      <tr>
                          <th>Image</th>
                          <th>Item</th>
                          <th>Amount</th>
                          <th>Price</th>
                          <th></th>
                      </tr>
                  </thead>
                  <tbody>
                      <tr th:each="item : ${cart}" th:with="product=${item.key}">
                          <td style="width: 10%; padding-left: 0"><img th:src="${product.image}" style="width: 100%; max-height: 80px"/></td>
                          <td th:text="${product.name}"></td>
                          <td>
                              <input type="hidden" name="id" th:value="${product.id}" form="updateForm" />
                              <input type="number" name="quantity" min="0" th:value="${item.value}" form="updateForm" />
                          </td>
                          <td th:if="${list.add(product.price * item.value)}" th:text="${#numbers.formatCurrency(product.price * item.value)}"></td>
                          <td>
                              <form th:action="@{/cart}" method="post" style="display: inline">
                                  <input type="hidden" name="_method" value="DELETE" />
                                  <input type="hidden" name="id" th:value="${product.id}" />
                                  <button class="btn btn-danger" style="display: inline" type="submit">Delete</button>
                              </form>
                          </td>
                      </tr>
                  </tbody>
                  <tfoot>
                      <tr>
                          <td></td>
                          <td>Total</td>
                          <td></td>
                          <td th:text="${#numbers.formatCurrency(#aggregates.sum(list))}"></td>
                          <td></td>
                      </tr>
                  </tfoot>
              </table>
  
              <form th:action="@{/cart}" method="post" id="updateForm" th:if="${cart.size() > 0}">
                  <!-- form doesn't support PATCH requests, but this tells Spring Boot to use @PatchMapping anyway. -->
                  <input type="hidden" name="_method" value="PATCH" />
                  <button type="submit" class="btn btn-primary">Update Cart</button>
              </form>
              <div th:if="${cart.size() == 0}">Nothing in your cart yet.</div>
          </div>
      </div>
  </main>
  
  <div th:replace="fragments :: footer"></div>
  </body>
  </html>

```
### fragments
 
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
### product

```html
    <!DOCTYPE HTML>
    <html xmlns:th="http://www.thymeleaf.org" lang="en">
    <head th:replace="fragments :: head"></head>
    <body>
    <div th:replace="fragments :: navbar" ></div>
    
    <div class="container">
        <div class="card mb-3 large-product-card">
            <div class="row no-gutters">
                <div class="col-md-6">
                    <img th:alt="${product.name}" th:src="@{${product.image}}" class="card-img" style="max-height: 100%; object-fit: cover;" />
                </div>
            <div class="col-md-4">
                <div class="card-body">
                    <div class="card-title">
                        <h4 th:text="${product.name}" style="margin-bottom: 0;"></h4>
                        <small th:text="${product.getBrand()}"></small>
                    </div>
                    <h5 class="card-text" th:text="${#numbers.formatCurrency(product.price)}"></h5>
                    <p class="card-text" th:text="${product.description}"></p>
                    <form th:action="@{/cart}" th:object="${product}" method="post">
                        <input type="hidden" th:field="*{id}" />
                        <button type="submit" class="btn btn-primary">Add to Cart</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>
    <footer th:replace="fragments :: footer"></footer>
    </body>
    </html>

```
### signin

```html
  <!DOCTYPE html>
  <html lang="en" xmlns:th="http://www.thymeleaf.org">
  <head th:replace="fragments :: head"></head>
  <head><link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"></head>
  <body style="padding: 0">
  <div th:replace="fragments :: navbar" ></div>
  
  <main role="main">
      <div class="container">
          <form class="form-signin" th:action="@{/signin}" method="post">
              <h2 class="form-signin-heading" style="text-align: center">Please Sign In/Up</h2>
              <p>
                  <label for="username" class="sr-only">Username</label>
                  <input type="text" id="username" name="username" class="form-control" placeholder="Username">
              </p>
              <p>
                  <label for="password" class="sr-only">Password</label>
                  <input type="password" id="password" name="password" class="form-control" placeholder="Password">
              </p>
              <div style="display: flex; justify-content: space-between">
                  <button class="btn btn-lg btn-primary" name="submit" value="in" type="submit" style="flex-grow: 0.95">Sign In</button>
                  <button class="btn btn-lg btn-success" name="submit" value="up" type="submit">Sign Up</button>
              </div>
          </form>
      </div>
  </main>
  
  <div th:replace="fragments :: footer"></div>
  </body>
  </html>

```
### about

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About</title>
</head>
<body>
This is the about page.
<h1>Under Construction!</h1>
<iframe src="https://i.giphy.com/media/S5JSwmQYHOGMo/200.gif" width="480" height="320" frameBorder="0" class="giphy-embed" allowFullScreen></iframe>
</body>
</html>
```
- Make the main controller
   - Route to the main page at "/"
   
# Retrospective
   - What did we learn
   - Any improvements going forward
   - Did everyone get it done 
   - etc.


## *__Homework:__*
- Do JUnit Testing
    - Some tests will fail  
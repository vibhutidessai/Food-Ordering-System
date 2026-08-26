````md
# 🍔 Food Ordering System

A simple full-stack **Food Ordering System** built using **Vanilla Java, HTML, CSS, and JavaScript** to demonstrate practical implementation of **Object-Oriented Programming (OOP)** concepts.

## ✨ Features

- 🍔 View food menu
- 🛒 Add items to cart
- 💰 Calculate total
- 📦 Place orders
- 💳 Process payments
- 🔗 Frontend-backend communication using HTTP/JSON

## 🛠️ Technologies

- Java 25
- Java `HttpServer`
- HTML5
- CSS3
- JavaScript
- HTTP/JSON
- VS Code

## 📁 Project Structure

```text
FoodOrderingSystem/
├── backend/
│   ├── Main.java
│   ├── FoodItem.java
│   ├── Customer.java
│   ├── Cart.java
│   ├── Order.java
│   ├── Payment.java
│   ├── UPIPayment.java
│   └── CardPayment.java
│
└── frontend/
    ├── index.html
    ├── style.css
    └── script.js
````

## 🧠 OOP Concepts Used

### 1. Encapsulation 🔒

Used in `FoodItem`, `Customer`, `Cart`, and `Order` by keeping data members `private` and accessing them through methods.

```java
private int id;
private String name;
private double price;

public String getName() {
    return name;
}
```

### 2. Abstraction 🎯

The `Payment` abstract class defines a common payment operation without specifying its implementation.

```java
public abstract class Payment {
    public abstract void pay(double amount);
}
```

### 3. Inheritance 🌳

`UPIPayment` and `CardPayment` inherit from the `Payment` class.

```java
public class UPIPayment extends Payment {
    // UPI payment implementation
}

public class CardPayment extends Payment {
    // Card payment implementation
}
```

### 4. Polymorphism 🔄

A `Payment` reference can refer to different payment objects, allowing the same method call to behave differently.

```java
Payment payment;

payment = new UPIPayment();
payment.pay(200);

payment = new CardPayment();
payment.pay(200);
```

## 🔄 Application Flow

```text
HTML/CSS/JavaScript
        ↓
      HTTP/JSON
        ↓
   Vanilla Java
        ↓
 ┌──────┴──────┐
FoodItem     Customer
    ↓
   Cart
    ↓
  Order
    ↓
 Payment
 /      \
UPI     Card
```

## 🌐 APIs

### Get Food Items

```http
GET /api/food
```

### Place Order

```http
POST /api/order
```

Example request:

```json
{
  "itemIds": [1, 4]
}
```

Example response:

```json
{
  "message": "Order placed successfully",
  "orderId": 101,
  "total": 200
}
```

## ▶️ How to Run

### Backend

Open the terminal in:

```text
FoodOrderingSystem/backend
```

Compile:

```bash
javac *.java
```

Run:

```bash
java Main
```

Backend:

```text
http://localhost:8080
```

### Frontend

Open the `frontend` folder in VS Code and run `index.html` using **Live Server**.

Frontend:

```text
http://127.0.0.1:5500/index.html
```

## 📌 OOP Mapping

| Class               | Purpose                         | OOP Concept   |
| ------------------- | ------------------------------- | ------------- |
| `FoodItem`          | Stores food details             | Encapsulation |
| `Customer`          | Stores customer details         | Encapsulation |
| `Cart`              | Manages selected items          | Encapsulation |
| `Order`             | Processes orders                | Encapsulation |
| `Payment`           | Common payment structure        | Abstraction   |
| `UPIPayment`        | UPI payment implementation      | Inheritance   |
| `CardPayment`       | Card payment implementation     | Inheritance   |
| `Payment` reference | Supports multiple payment types | Polymorphism  |

## 🎯 Learning Outcomes

* Applied all four fundamental OOP concepts in a real-world project
* Built a backend using **Vanilla Java without Spring Boot**
* Created HTTP APIs using Java's built-in `HttpServer`
* Connected a Java backend with an HTML/CSS/JavaScript frontend
* Implemented cart, order, and payment functionality

```
```

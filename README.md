**PromoEngine - Checkout Promotion Engine**

This is a Spring Boot application that implements a promotion engine to calculate the total value of a shopping cart after applying active promotional rules.


---

**Features**

- Applies multiple types of promotions:
  - Quantity-based promotion
  - Combo promotion 
- Clean and extensible architecture
- REST API using `POST` with JSON input
- Unit tested using JUnit 

---

**Technology Stack**

- Java 17
- Spring Boot 3.5.3
- Gradle


---

**Running the Application**

```
./gradlew bootRun
```
runs on
```
http://localhost:8080
```
API endpoint:
```
checkout
```
Request body(JSON):
```
[
  { "sku": "A", "quantity": 3 },
  { "sku": "B", "quantity": 5 },
  { "sku": "C", "quantity": 1 },
  { "sku": "D", "quantity": 1 }
]
```
Response:
```
{
  "total": 280
}
```
## 📬 Postman Example
[assets/postman-request.png](https://github.com/danishrahmankpm/PromoEngine/blob/main/assets/Screenshot%202025-07-06%20175553.png)
## 🧪 Tests
[assets/tests.png](https://github.com/danishrahmankpm/PromoEngine/blob/main/assets/Screenshot%202025-07-06%20175318.png)


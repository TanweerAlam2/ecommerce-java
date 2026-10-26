# Marketly — Big E-commerce Java Project

Spring Boot full-stack starter with:
- Product catalogue backed by H2/JPA
- Search and category filtering
- Product detail pages
- Shopping cart page with cart summary and clear-cart action
- Checkout page with demo order placement flow
- Account dashboard and order history pages
- About and contact pages
- Add-to-cart counter using browser localStorage
- Responsive storefront UI
- JSON endpoint at `/api/products`

## Run

Requirements: Java 17+ and Maven 3.9+.

```bash
mvn spring-boot:run
```

Open http://localhost:8080

Additional pages:
- `/cart`
- `/checkout`
- `/account`
- `/orders`
- `/about`
- `/contact`

Build a jar:

```bash
mvn clean package
java -jar target/marketly-0.0.1-SNAPSHOT.jar
```

The H2 console is available at `/h2-console` with JDBC URL `jdbc:h2:mem:marketly`.


## Backend APIs added

- `GET /api/health` — service health check
- `GET /api/products` — catalogue
- `GET /api/admin/products/categories` — categories
- `POST /api/admin/products` — create product
- `PUT /api/admin/products/{id}` — update product
- `DELETE /api/admin/products/{id}` — delete product
- `POST /api/orders` — place an order and decrement stock
- `GET /api/orders?email=customer@example.com` — customer order history
- `GET /api/orders/{id}` — order detail
- `PATCH /api/orders/{id}/status?value=SHIPPED` — update order status
- `POST /api/contact` — save contact messages
- `GET /api/contact` — list contact messages

Example order request:
```json
{
  "customerName": "Alex Morgan",
  "email": "alex@example.com",
  "items": [
    {"productId": 1, "quantity": 2}
  ]
}
```

This demo uses H2 in-memory storage. For production, replace the datasource with PostgreSQL/MySQL and add authentication/authorization before exposing admin APIs.
\n\n## Frontend update\n\nThe Thymeleaf frontend now includes a responsive Marketly storefront with a promotional hero, category navigation, product sorting, hover states, toast feedback, cart count persistence, trust badges, and mobile layout support. Static assets are under `src/main/resources/static`; page templates are under `src/main/resources/templates`.\n
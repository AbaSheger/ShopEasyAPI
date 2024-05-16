
# ShopEasyAPI

## Description
ShopEasyAPI is a simple Spring Boot application providing a RESTful API for e-commerce operations, equipped with Spring Security for role-based access control.

## Features
- Manage products and orders.
- Role-based authentication.

## Quick Start

### 1. Clone the repository
```bash
git clone https://github.com/AbaSheger/ShopEasyAPI.git
```
### 2. Build and run the application
bash
Copy code
cd ShopEasyAPI
mvn clean install
mvn spring-boot:run

## API Endpoints
- Products: GET /api/products, POST /api/products (Admin only)
- Orders: GET /api/orders, POST /api/orders (User only)

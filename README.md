# 🚍 Backend - Bus API

## 📖 Descripción
Este es el **backend del Reto Técnico FullStack**.  
La aplicación expone una API REST para gestionar buses, con datos como número, placa, marca, características y estado.  

Se desarrolló con:
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Security (Basic Auth)**
- **PostgreSQL (Docker o instalación local)**
- **Lombok**

---

## ⚙️ Instalación y ejecución

### 1️⃣ Clonar el repositorio
```bash
git clone git@github.com:MarceloAM94/backend-bus-api.git
cd backend-bus-api
```

### 2️⃣ Configurar la base de datos

#### Opción A: Usando Docker (recomendado)
Incluye un `docker-compose.yml` para levantar PostgreSQL:
```yaml
services:
  postgres:
    container_name: Civa_postgres_db
    image: postgres:16
    ports:
      - "5432:5432"
    environment:
      POSTGRES_USER: Marcelo
      POSTGRES_PASSWORD: Marcelo0904
      POSTGRES_DB: BusesAPI_DB
    volumes:
      - civa_data:/var/lib/postgresql/data

volumes:
  civa_data:
```

Levantar la base:
```bash
docker compose up -d
```

#### Opción B: Instalar PostgreSQL manualmente
```sql
CREATE DATABASE BusesAPI_DB;
CREATE USER Marcelo WITH PASSWORD 'Marcelo0904';
GRANT ALL PRIVILEGES ON DATABASE "BusesAPI_DB" TO Marcelo;
```

---

### 3️⃣ Configuración de Spring Boot
En `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/BusesAPI_DB
spring.datasource.username=Marcelo
spring.datasource.password=Marcelo0904
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Seguridad - credenciales en memoria
spring.security.user.name=admin
spring.security.user.password=12345
```

---

### 4️⃣ Insertar datos de prueba

Ejecutar el siguiente script en la base de datos (`BusesAPI_DB`) usando DBeaver o psql:

```sql
-- Insertar marcas
INSERT INTO brand (name) VALUES
('Volvo'),
('Scania'),
('Mercedes-Benz'),
('Fiat'),
('Iveco');

-- Insertar buses (usando IDs de marcas 1 al 5)
INSERT INTO bus (bus_number, license_plate, created_at, features, brand_id, active) VALUES
('1001', 'ABC-123', NOW(), 'Double decker, WiFi, Air Conditioning', 1, true),
('1002', 'DEF-456', NOW(), 'Single decker, Reclining seats', 2, true),
('1003', 'GHI-789', NOW(), 'Luxury seats, Entertainment system', 3, false),
('1004', 'JKL-321', NOW(), 'Standard seats, No WiFi', 4, true),
('1005', 'MNO-654', NOW(), 'Reclining seats, Air Conditioning', 5, true),
('1006', 'PQR-987', NOW(), 'Luxury seats, USB chargers', 1, true),
('1007', 'STU-135', NOW(), 'WiFi, Entertainment system', 2, false),
('1008', 'VWX-246', NOW(), 'Double decker, Premium seats', 3, true),
('1009', 'YZA-369', NOW(), 'Standard seats, USB chargers', 4, true),
('1010', 'BCD-147', NOW(), 'Reclining seats, WiFi', 5, false);
```

Verificar:
```sql
SELECT * FROM brand;
SELECT * FROM bus;
```

---

### 5️⃣ Levantar el backend
```bash
./mvnw spring-boot:run
```
El backend quedará en:  
👉 `http://localhost:8080`

---

## 🔐 Seguridad
Se usa **Basic Auth** para proteger los endpoints.  

- Usuario: `admin`  
- Contraseña: `12345`  

Al consumir desde Postman, Bruno o fetch en React, debes enviar:  
```http
Authorization: Basic YWRtaW46MTIzNDU=
```
(`admin:12345` en Base64)

---

## 📡 Endpoints

### Listar buses (paginado)
```
GET /bus?page=0&size=5
```

Respuesta:
```json
{
  "content": [
    {
      "id": 1,
      "busNumber": "1001",
      "licensePlate": "ABC-123",
      "createdAt": "2025-09-19T23:22:43.679388",
      "features": "Double decker, WiFi, Air Conditioning",
      "brand": { "id": 1, "name": "Volvo" },
      "active": true
    }
  ],
  "totalPages": 2,
  "totalElements": 10,
  "size": 5,
  "number": 0
}
```

### Buscar bus por ID
```
GET /bus/{id}
```

Ejemplo:
```
GET /bus/1
```

---

## 📦 Estructura del proyecto
```
src/main/java/com/civa/busapi
│
├── config
│   ├── SecurityConfig.java
│   └── CorsConfig.java
│
├── controller
│   └── BusController.java
│
├── entity
│   ├── Bus.java
│   └── Brand.java
│
├── repository
│   ├── BusRepository.java
│   └── BrandRepository.java
│
└── service
    └── BusService.java
```

---

## ✅ Reto cumplido
- Backend en **Spring Boot 3 + PostgreSQL**.  
- Seguridad con **Basic Auth**.  
- Endpoints `/bus` (paginado) y `/bus/{id}`.  
- Datos cargados en **PostgreSQL** vía Docker o script SQL.  

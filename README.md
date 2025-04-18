# Sistema de pedidos App

Aplicación web para la gestión de pedidos en un restaurante o negocio gastronómico.
Los clientes pueden registrarse e iniciar sesión, navegar por el menú disponible y realizar pedidos.
El administrador puede gestionar los productos del menú, monitorear los pedidos en curso y ver estadísticas.
Los encargados de cocina pueden ver y actualizar el estado de los pedidos en tiempo real.

## ✨ Características

- Registro e inicio de sesión con JWT.
- Seguridad con Spring Security y roles (`ADMIN`, `CLIENTE`, `COCINA`).
- Gestión de usuarios, productos y pedidos.
- Integración con base de datos PostgreSQL.
- Migraciones con Flyway.
- Tests unitarios e integración con H2.
- API REST lista para ser consumida.
- Documentación interactiva con Swagger UI.

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.4.4**
- **Spring Security**
- **JWT** (JSON Web Token)
- **JPA / Hibernate**
- **Flyway**
- **PostgreSQL (producción)** / **H2 (tests)**
- **Redis** (para cache y rate limiting)
- **RabbitMQ** (para mensajería y procesamiento asíncrono)
- **Gradle**
- **JUnit + Mockito**
- **Springdoc OpenAPI** (Swagger UI)

## 🚀 Levantar el proyecto

### 1. Clonar el repositorio:

   ```bash
   git clone https://github.com/Ger1211/sistema-pedidos.git
   cd sistema-pedidos
   ```

### 2. Configurar `application.properties`:

  ```properties
  # PostgreSQL
  spring.datasource.url=jdbc:postgresql://localhost:5433/pedidos
  spring.datasource.username=tu_usuario
  spring.datasource.password=tu_contraseña
  
  # Flyway
  spring.flyway.enabled=true
  
  # JWT
  jwt.secret=claveSecretaSuperSegura
  ```

### 3. Ejecutar las migraciones y levantar el proyecto:

  ```bash
  ./gradlew flywayMigrate bootRun
  ```

### 4. Para ejecutar tests

  ```bash
  ./gradlew test
  ```

### 5.Estructura del proyecto

  ```swift
  src/
├── main/
│   ├── java/com/german/cabrera/sistema_pedidos/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── model/
│   │   ├── repository/
│   │   └── security/
│   │   └── service/
│   └── resources/
│       ├── application.properties
│       └── db/migration/         <- Migraciones Flyway
├── test/
│   └── java/com/german/cabrera/turnos/
│       ├── unit/
│       └── integration/
│   └── resources/
│       ├── application-test.properties     <- Config H2

  ```

🧪 Tests

Incluye tests unitarios y de integración. Usa H2 como base de datos en memoria durante los tests para mayor velocidad y aislamiento.

📘 Swagger UI

La documentación de la API está disponible en una interfaz interactiva gracias a Swagger UI.

🔗 Acceso
Una vez que la aplicación está corriendo, podés acceder a:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

O directamente:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


📜 Licencia

Este proyecto está bajo la licencia MIT.
Ver el archivo [LICENSE](./LICENSE) para más detalles.

Proyecto en desarrollo 🚧
Autor: [Ger1211](https://github.com/Ger1211)


# User API - RESTful Service

Esta aplicación expone una API RESTful para la creación y manejo de usuarios con autenticación basada en JWT. Los usuarios pueden registrarse, y la API devolverá un token JWT que podrá ser usado para futuras autenticaciones. Todos los endpoints aceptan y retornan JSON, incluyendo los mensajes de error.

## Características

- Registro de usuarios con validación de correo y contraseña.
- Persistencia en base de datos en memoria (H2).
- Uso de JPA para la persistencia de datos (OpenJPA).
- Generación de tokens JWT para autenticación.
- API documentada con Swagger (opcional).
- Pruebas unitarias (opcional).

## Tecnologías utilizadas

- **Java 8**.
- **Spring Boot** (Framework principal).
- **OpenJPA 2.4.3** (Proveedor JPA).
- **JWT** para generación y validación de tokens.
- **Lombok** para reducir boilerplate.
- **H2** como base de datos en memoria.
- **Maven** para gestión del build.

## Prerrequisitos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

- [JDK 8+](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
- [Maven](https://maven.apache.org/)

## Configuración de la Base de Datos

La aplicación utiliza una base de datos en memoria H2, por lo que no es necesario configurar una base de datos externa. La base de datos se inicializa automáticamente al arrancar la aplicación.

## Instalación

1. Clona el repositorio:

   ```bash
   git clone https://github.com/limpjor/UserApi.git
   cd user-api
   mvn clean install
   mvn spring-boot:run

2. Registro de Usuario
    Body:
   {
   "name":"Juan Rodriguez",
   "email":"juan@dominio.cl",
   "password":"Password2",
   "phones":[
   {
   "number":"1234567",
   "citycode":"1",
   "contrycode":"57"
   }
   ]
   }

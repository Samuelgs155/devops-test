# Spring Boot Demo - CI/CD con Jenkins, SonarQube y Docker

Proyecto de ejemplo desarrollado con Spring Boot para demostrar un flujo completo de Integración Continua (CI) y Despliegue Continuo (CD) utilizando:

- Spring Boot
- Gradle
- JUnit 5
- Mockito
- SonarQube
- Jenkins
- Docker
- GitHub

---

# Arquitectura

```text
Developer
    │
    ▼
 GitHub
    │
    ▼
 Jenkins
    │
    ├── Build
    ├── Unit Tests
    ├── SonarQube Analysis
    ├── Quality Gate
    ├── Docker Build
    └── Deploy Local
            │
            ▼
      Docker Container
```

---

# Tecnologías

| Tecnología | Versión |
|------------|----------|
| Java | 17 |
| Spring Boot | 4.1.0 |
| Gradle | Wrapper |
| JUnit | 5 |
| Mockito | Incluido en Starter Test |
| SonarQube | Community |
| Jenkins | LTS |
| Docker | Latest |

---

# Endpoints

## Health Check

```http
GET /health
```

### Response

```text
ok!
```

Ejemplo:

```bash
curl http://localhost:8080/health
```

Respuesta:

```text
ok!
```

---

# Ejecución Local

## Clonar repositorio

```bash
git clone https://github.com/TU_USUARIO/springboot-demo.git

cd springboot-demo
```

## Ejecutar pruebas

Linux/Mac:

```bash
./gradlew test
```

Windows:

```bash
gradlew.bat test
```

## Ejecutar aplicación

Linux/Mac:

```bash
./gradlew bootRun
```

Windows:

```bash
gradlew.bat bootRun
```

Aplicación disponible en:

```text
http://localhost:8080
```

---

# Construcción

Generar artefacto:

```bash
./gradlew clean build
```

Resultado:

```text
build/libs/demo-0.0.1-SNAPSHOT.jar
```

---

# Docker

## Construir imagen

```bash
docker build -t springboot-demo .
```

## Ejecutar contenedor

```bash
docker run -d \
--name springboot-demo \
-p 8080:8080 \
springboot-demo
```

## Verificar

```bash
curl http://localhost:8080/health
```

---

# SonarQube

Levantar SonarQube:

```bash
docker run -d \
--name sonarqube \
-p 9000:9000 \
sonarqube:lts-community
```

Acceder:

```text
http://localhost:9000
```

Usuario:

```text
admin
```

Contraseña:

```text
admin
```

Generar un token en:

```text
My Account
→ Security
→ Generate Token
```

---

# Jenkins

Levantar Jenkins:

```bash
docker run -d \
--name jenkins \
-p 8081:8080 \
-p 50000:50000 \
jenkins/jenkins:lts-jdk21
```

Obtener password inicial:

```bash
docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

Acceder:

```text
http://localhost:8081
```

---

# Pipeline Jenkins

Etapas del pipeline:

```text
Checkout
↓
Build
↓
Tests
↓
SonarQube Analysis
↓
Quality Gate
↓
Docker Build
↓
Deploy Local
```

Si el Quality Gate falla:

```text
Pipeline STOP
```

No se despliega la aplicación.

---

# Ejecutar análisis Sonar

```bash
./gradlew sonar
```

---

# Ejecutar tests

```bash
./gradlew test
```

Reporte generado:

```text
build/reports/tests/test/index.html
```

---

# Estructura del Proyecto

```text
demo
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── controller
│   │   │   ├── service
│   │   │   └── DemoApplication
│   │   └── resources
│   │
│   └── test
│       └── java
│
├── Dockerfile
├── Jenkinsfile
├── build.gradle
├── settings.gradle
└── README.md
```

---

# Tests Implementados

## Controller

```java
@WebMvcTest(HealthController.class)
```

Valida:

- Endpoint HTTP
- Código de respuesta
- Integración Controller-Service

## Service

```java
class HealthServiceImplTest
```

Valida:

- Lógica de negocio

---

# Calidad

Objetivos mínimos:

- Cobertura ≥ 80%
- Sin bugs críticos
- Sin vulnerabilidades críticas
- Quality Gate aprobado

---

# Autor

Proyecto educativo para practicar:

- Spring Boot
- Docker
- Jenkins
- SonarQube
- CI/CD
- Testing con JUnit y Mockito
# 🚀 Automation Screenplay Project

Proyecto de automatización de pruebas utilizando **Serenity BDD** con el patrón **Screenplay**, implementando pruebas de **UI**, **API** y **Performance** para el sitio [Automation Exercise](https://automationexercise.com).

![Java](https://img.shields.io/badge/Java-17-orange)
![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-4.2.9-green)
![Selenium](https://img.shields.io/badge/Selenium-4.27.0-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.8+-blue)
![Gatling](https://img.shields.io/badge/Gatling-3.9.5-yellow)

## 📋 Tabla de Contenidos

- [Especificaciones Técnicas](#-especificaciones-técnicas)
- [Prerrequisitos](#-prerrequisitos)
- [Instalación](#-instalación)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Ejecución de Pruebas](#-ejecución-de-pruebas)
- [Reportes](#-reportes)
- [CI/CD](#-cicd)
- [Configuración Avanzada](#-configuración-avanzada)

---

## 🎯 Especificaciones Técnicas

### Stack Tecnológico

- **Lenguaje:** Java 17
- **Patrón de Diseño:** Screenplay (Actor-Task-Question)
- **Framework de Automatización:** Serenity BDD 4.2.9
- **WebDriver:** Selenium 4.27.0
- **API Testing:** REST Assured
- **Performance Testing:** Gatling 3.9.5
- **Build Tool:** Maven 3.8+
- **Test Runner:** JUnit 4
- **CI/CD:** GitHub Actions

### Tipos de Pruebas

1. **Pruebas de UI (Interfaz de Usuario)**
   - Automatización del formulario "Contact Us"
   - Ejecución local (Chrome) y remota (BrowserStack)
   - Screenshots y videos de evidencia

2. **Pruebas de API (Backend)**
   - Validación de endpoints REST
   - Ciclo completo: POST (crear usuario) → GET (consultar usuario)
   - Validación de códigos de estado y respuestas JSON

3. **Pruebas de Performance (Carga)**
   - Pruebas de carga con Gatling
   - 50 usuarios virtuales concurrentes
   - Duración: 5 minutos (Ramp-up: 1 minuto)
   - Métricas: Tiempo de respuesta, tasa de errores, throughput

---

## 📦 Prerrequisitos

### Software Requerido

| Software | Versión Mínima | Propósito |
|----------|----------------|-----------|
| Java JDK | 17+ | Lenguaje de programación |
| Maven | 3.8+ | Gestión de dependencias y build |
| Git | 2.0+ | Control de versiones |
| Chrome | Última versión | Navegador para pruebas UI locales |

### Verificar Instalaciones

```bash
# Verificar Java
java -version
# Debe mostrar: java version "17.x.x"

# Verificar Maven
mvn -version
# Debe mostrar: Apache Maven 3.8.x o superior

# Verificar Git
git --version
```

### Cuentas Requeridas (Opcionales)

- **BrowserStack:** Para ejecución de pruebas en la nube
  - Registrarse en: https://www.browserstack.com
  - Obtener `USERNAME` y `ACCESS_KEY` del dashboard

---

## 🔧 Instalación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/Automation_Screenplay.git
cd Automation_Screenplay
```

### 2. Instalar Dependencias

```bash
mvn clean install
```

Este comando:
- Descarga todas las dependencias del proyecto
- Compila el código fuente
- Prepara el proyecto para ejecución

### 3. Configurar Variables de Entorno (Para BrowserStack)

**Windows (PowerShell):**
```powershell
$env:BROWSERSTACK_USERNAME="tu_usuario"
$env:BROWSERSTACK_ACCESS_KEY="tu_access_key"
```

**Mac/Linux (Terminal):**
```bash
export BROWSERSTACK_USERNAME='tu_usuario'
export BROWSERSTACK_ACCESS_KEY='tu_access_key'
```

---

## 📁 Estructura del Proyecto

```
Automation_Screenplay/
├── .github/
│   └── workflows/
│       └── ui-tests.yml              # Pipeline CI/CD
├── src/
│   └── test/
│       ├── java/com/automation/
│       │   ├── api/                  # 🔌 Pruebas de API
│       │   │   ├── models/           # POJOs para request/response
│       │   │   │   └── User.java
│       │   │   ├── questions/        # Validaciones de respuestas API
│       │   │   │   ├── UserDetailsAreCorrect.java
│       │   │   │   └── UserWasCreated.java
│       │   │   ├── runners/          # Tests ejecutables de API
│       │   │   │   └── UserApiTest.java
│       │   │   └── tasks/            # Acciones de API (POST, GET)
│       │   │       ├── CreateUserAccount.java
│       │   │       └── GetUserDetails.java
│       │   ├── interactions/         # 🎭 Interacciones reutilizables
│       │   │   └── AcceptAlert.java
│       │   ├── performance/          # ⚡ Pruebas de Performance
│       │   │   ├── LoginLoadTest.java
│       │   │   └── SearchProductLoadTest.java
│       │   ├── questions/            # ❓ Validaciones de UI
│       │   │   └── SuccessMessageIsVisible.java
│       │   ├── runners/              # 🏃 Tests ejecutables de UI
│       │   │   └── ContactUsTest.java
│       │   ├── tasks/                # ✅ Tareas de UI (Screenplay)
│       │   │   ├── OpenHomePage.java
│       │   │   └── SendContactForm.java
│       │   ├── ui/                   # 🖥️ Page Objects y elementos UI
│       │   │   └── ContactUsPage.java
│       │   └── utils/                # 🛠️ Utilidades y helpers
│       │       └── TestData.java
│       └── resources/
│           ├── serenity.conf         # Configuración de Serenity
│           └── testdata/             # Datos de prueba
│               └── evidence.txt
├── target/
│   ├── site/serenity/                # Reportes HTML (después de ejecutar)
│   └── gatling/                      # Reportes de Performance
├── pom.xml                           # Configuración Maven
├── run-browserstack.sh               # Script para ejecutar en BrowserStack (Linux/Mac)
├── run-browserstack.bat              # Script para ejecutar en BrowserStack (Windows)
└── README.md                         # Este archivo
```

### 📂 Descripción de Carpetas

| Carpeta | Propósito |
|---------|-----------|
| `api/` | Pruebas de endpoints REST (POST, GET, validaciones) |
| `interactions/` | Interacciones personalizadas reutilizables (ej: AlertHandler) |
| `performance/` | Pruebas de carga con Gatling |
| `questions/` | Validaciones de estado del sistema (UI) |
| `runners/` | Clases ejecutables con `@RunWith(SerenityRunner.class)` |
| `tasks/` | Tareas de alto nivel que ejecutan los actores (Screenplay) |
| `ui/` | Page Objects y elementos de la interfaz |
| `utils/` | Utilidades y datos de prueba |

---

## ▶️ Ejecución de Pruebas

### Pruebas de UI

#### Ejecución Local (Chrome)

```bash
mvn test -Dtest=ContactUsTest -Denvironment=local
```

#### Ejecución en BrowserStack

**Opción 1: Usando el script (Recomendado)**

Windows:
```bash
run-browserstack.bat
```

Mac/Linux:
```bash
chmod +x run-browserstack.sh
./run-browserstack.sh
```

**Opción 2: Comando Maven directo**

```bash
mvn test \
  -Dtest=ContactUsTest \
  -Denvironment=browserstack \
  -Dbrowserstack.username=$BROWSERSTACK_USERNAME \
  -Dbrowserstack.key=$BROWSERSTACK_ACCESS_KEY
```

### Pruebas de API

```bash
mvn test -Dtest=UserApiTest
```

**¿Qué valida?**
1. Crea un usuario vía POST (`/api/createAccount`)
2. Valida código de estado 200 y mensaje "User created!"
3. Consulta el usuario creado vía GET (`/api/getUserDetailByEmail`)
4. Valida que los datos coincidan

### Pruebas de Performance

#### Ejecutar prueba de Login

```bash
mvn gatling:test -Dgatling.simulationClass=com.automation.performance.LoginLoadTest
```

#### Ejecutar prueba de Search

```bash
mvn gatling:test -Dgatling.simulationClass=com.automation.performance.SearchProductLoadTest
```

#### Ejecutar todas las pruebas de performance

```bash
mvn gatling:test
```

**Configuración de carga:**
- **Usuarios virtuales:** 50 concurrentes
- **Ramp-up:** 1 minuto (incremento gradual)
- **Duración total:** 5 minutos
- **Endpoints probados:**
  - `/api/verifyLogin`
  - `/api/searchProduct`

### Ejecutar Todas las Pruebas

```bash
mvn clean test
```

---

## 📊 Reportes

### Reportes de Serenity (UI y API)

Después de ejecutar las pruebas, generar el reporte:

```bash
mvn serenity:aggregate
```

**Ubicación del reporte:**
```
target/site/serenity/index.html
```

**Abrir en navegador:**

Windows:
```bash
start target\site\serenity\index.html
```

Mac:
```bash
open target/site/serenity/index.html
```

Linux:
```bash
xdg-open target/site/serenity/index.html
```

**Contenido del reporte:**
- ✅ Resultados de pruebas (Passed/Failed)
- 📸 Screenshots de cada paso
- 📹 Videos de ejecución (BrowserStack)
- ⏱️ Tiempos de ejecución
- 📝 Logs detallados

### Reportes de Gatling (Performance)

**Ubicación:**
```
target/gatling/[nombre-test]-[timestamp]/index.html
```

**Contenido:**
- 📈 Tiempo de respuesta (min, max, mean, p95, p99)
- ❌ Tasa de errores (%)
- 🚀 Throughput (requests/segundo)
- 📊 Gráficos de distribución de carga
- 📉 Timeline de ejecución

---

## 🔄 CI/CD

### GitHub Actions

El proyecto incluye un pipeline automatizado que se ejecuta en cada push o pull request.

**Archivo:** `.github/workflows/ui-tests.yml`

**¿Qué hace el pipeline?**

1. ✅ Checkout del código
2. ✅ Configura Java 17
3. ✅ Instala dependencias (`mvn clean install`)
4. ✅ Ejecuta pruebas de UI en BrowserStack
5. ✅ Genera reportes Serenity
6. ✅ Publica reportes como artifacts

**Configurar Secrets en GitHub:**

1. Ve a tu repositorio → **Settings**
2. **Secrets and variables** → **Actions**
3. **New repository secret**
4. Agrega:
   - `BROWSERSTACK_USERNAME`: Tu usuario de BrowserStack
   - `BROWSERSTACK_ACCESS_KEY`: Tu access key de BrowserStack

**Acceder a los reportes del pipeline:**

1. Ve a la pestaña **Actions** en GitHub
2. Selecciona el workflow ejecutado
3. Scroll down hasta **Artifacts**
4. Descarga `serenity-reports`

**Estado del Build:**

![CI Status](https://github.com/tu-usuario/Automation_Screenplay/workflows/UI%20Tests%20Pipeline/badge.svg)

---

## ⚙️ Configuración Avanzada

### Cambiar Navegador

Editar `src/test/resources/serenity.conf`:

```hocon
local {
  webdriver {
    driver = firefox  # chrome, firefox, edge
  }
}
```

### Ejecución Headless (Sin interfaz gráfica)

```bash
mvn test -Dtest=ContactUsTest -Dheadless.mode=true
```

### Modificar Configuración de Carga (Performance)

Editar `src/test/java/com/automation/performance/LoginLoadTest.java`:

```java
setUp(
  loginScenario.injectOpen(
    rampUsers(100).during(Duration.ofMinutes(2)) // 100 usuarios en 2 min
  ).protocols(httpProtocol)
).maxDuration(Duration.ofMinutes(10)) // 10 minutos total
```

### Ejecutar en Diferentes Ambientes

```bash
# Local
mvn test -Denvironment=local

# BrowserStack
mvn test -Denvironment=browserstack

# Custom environment (agregar en serenity.conf)
mvn test -Denvironment=staging
```

---

## 🐛 Troubleshooting

### Error: "ChromeDriver not found"

**Solución:** Serenity maneja automáticamente ChromeDriver. Asegúrate de tener Chrome instalado.

```bash
# Verificar instalación de Chrome
google-chrome --version  # Linux
chrome --version         # Mac
```

### Error: "Could not resolve substitution BROWSERSTACK_USERNAME"

**Solución:** Configura las variables de entorno o usa los scripts helper.

```bash
# Windows
set BROWSERSTACK_USERNAME=tu_usuario
set BROWSERSTACK_ACCESS_KEY=tu_key

# Mac/Linux
export BROWSERSTACK_USERNAME='tu_usuario'
export BROWSERSTACK_ACCESS_KEY='tu_key'
```

### Tests pasan localmente pero fallan en CI

**Posibles causas:**
- Timeouts más largos en CI
- Diferencias de resolución de pantalla
- Red más lenta

**Solución:** Agregar waits explícitos o aumentar timeouts en `serenity.conf`.

### Reportes Gatling no se generan

**Solución:** Verificar que la clase de simulación existe y está bien nombrada.

```bash
mvn clean install
mvn gatling:test
```

---

## 📚 Recursos Adicionales

- **Serenity BDD:** https://serenity-bdd.info
- **Screenplay Pattern:** https://serenity-bdd.github.io/docs/screenplay/screenplay_fundamentals
- **REST Assured:** https://rest-assured.io
- **Gatling:** https://gatling.io/docs
- **BrowserStack:** https://www.browserstack.com/docs

---

## 👤 Autor

**Oscar**  
Proyecto de Automatización con Serenity BDD + Screenplay

---

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

---

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

---

## ✅ Checklist de Validación

Antes de hacer push, verifica:

- [ ] Todas las pruebas pasan localmente
- [ ] Reportes se generan correctamente
- [ ] Código está formateado
- [ ] No hay credenciales hardcodeadas
- [ ] README está actualizado
- [ ] Pipeline de CI/CD funciona

---

**⭐ Si este proyecto te fue útil, dale una estrella en GitHub!**

# Saucedemo Selenium Automation (Gradle + TestNG)

Proyecto de automatización de pruebas para la página [Saucedemo](https://www.saucedemo.com/) utilizando **Selenium WebDriver**, **Java**, **Gradle** y **TestNG**.

## 🚀 Características
- **Page Object Model (POM)**: Estructura organizada y mantenible.
- **CI/CD con GitHub Actions**: Ejecución automática de pruebas en cada `push`.
- **Modo Headless**: Configurado para correr en servidores sin interfaz gráfica (ideal para Jenkins/GitHub Actions).

## 🛠️ Requisitos
- Java 17+
- Gradle (incluido via `gradlew`)

## 🏃 Cómo ejecutar las pruebas localmente
1. Clona el repositorio.
2. Ejecuta el comando:
   ```bash
   ./gradlew test
   ```

## 📊 Reportes
Los resultados de las pruebas se generan en:
`build/reports/tests/test/index.html`

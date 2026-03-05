# Saucedemo Selenium Automation (Gradle + TestNG)

Proyecto profesional de automatización de pruebas para [Saucedemo](https://www.saucedemo.com/).

## 🚀 Características
- **Page Object Model (POM)**: Estructura profesional y mantenible.
- **CI/CD Ready**: Configurado para GitHub Actions.
- **Modo Flexible**: Soporte para ejecuciones con y sin interfaz gráfica.
- **Sincronización Robusta**: Uso exclusivo de `WebDriverWait` y limpieza de campos mediante simulación de teclado.

## 🏃 Ejecución Local

Por defecto, los tests corren en modo **Headless** (sin abrir ventana).

### Ver el navegador mientras corre (UI Mode)
Si quieres ver el navegador Chrome físicamente mientras se ejecutan las pruebas:
```bash
./gradlew clean test -Dheadless=false
```

### Correr en modo silencioso (Headless)
```bash
./gradlew clean test -Dheadless=true
```

## 📊 Reportes
Al finalizar, puedes ver el reporte visual en:
`build/reports/tests/test/index.html`

## ⚙️ CI/CD
Cada `push` a la rama `main` dispara automáticamente la suite en GitHub Actions bajo el entorno `ubuntu-latest`.

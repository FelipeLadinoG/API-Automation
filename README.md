# API Automation

Este proyecto es un ejemplo de automatización de pruebas para una API utilizando RestAssured, Cucumber y Java 11.

## Tecnologías Utilizadas

- **RestAssured:** RestAssured es una biblioteca Java que se utiliza para probar las API REST. Proporciona una forma sencilla y fluida de enviar solicitudes HTTP y de verificar las respuestas.
  
- **Cucumber:** Cucumber es un marco de trabajo de automatización de pruebas basado en el lenguaje natural. Permite escribir escenarios de prueba en un formato legible para humanos (Gherkin) y luego implementar esos escenarios en código Java utilizando pasos definidos.

- **Java 11:** Java es un lenguaje de programación popular utilizado en el desarrollo de aplicaciones empresariales y de software. En este proyecto, utilizamos Java 11 como la versión del lenguaje para escribir los pasos de prueba y la lógica de automatización.

## Ejecución de las Pruebas

Para ejecutar las pruebas, asegúrate de tener configurado tu entorno de desarrollo con Java 11 y Maven. Luego, simplemente ejecuta la clase `TestRunner` ubicada en el paquete `com.testing.api.runner`. Esta clase se encargará de ejecutar todas las pruebas definidas en los archivos de características de Cucumber.

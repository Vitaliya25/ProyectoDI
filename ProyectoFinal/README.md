Documentación del Proyecto: EuroFit App
Descripción del Proyecto
La aplicación EuroFit App tiene como objetivo permitir a los usuarios calcular las notas que obtendrían en distintas pruebas físicas basadas en el baremo EuroFit, utilizando su género, altura, peso y edad. La aplicación tiene varias funcionalidades clave que permiten al usuario interactuar con diferentes pruebas físicas, visualizar su IMC, y gestionar sus datos personales de manera persistente.
Funcionalidades Clave
1.	Pantalla de Login: Permite al usuario iniciar sesión y cambiar su contraseña en tiempo de ejecución.
2.	Gestión de Datos del Usuario: El usuario puede introducir y modificar sus datos personales como edad, peso, altura y sexo. Estos datos son persistentes.
3.	Pruebas Físicas: Se presentan varias pruebas físicas clasificadas por categorías como Fuerza, Flexibilidad, Velocidad, Agilidad, Coordinación y Resistencia.
4.	Cálculo del IMC: Se ofrece un cálculo de IMC con un botón en la pantalla principal.
5.	Visualización de Resultados de Pruebas: Permite mostrar los resultados de las pruebas realizadas.
6.	Búsqueda de Pruebas: Implementación de un buscador para filtrar entre diferentes tipos de pruebas disponibles.
Requisitos de Instalación
1.	Android Studio: tener instalado Android Studio (última versión estable).
2.	SDK de Android: La aplicación fue desarrollada utilizando Kotlin y Jetpack Compose.
Pasos para Configuración:
1.	Clona el proyecto desde el repositorio o descárgalo como un archivo zip.
2.	Abre el proyecto en Android Studio.
3.	Asegúrate de que las dependencias necesarias estén configuradas en el archivo build.gradle.
4.	Ejecuta el proyecto en tu dispositivo o emulador.
Descripción de la Interfaz de Usuario (UI/UX)
La aplicación tiene un diseño intuitivo y adaptado a dispositivos móviles. A continuación se describen los componentes principales de la interfaz:
1.	Pantalla de Login:
o	Campos para ingresar el nombre de usuario y contraseña.
o	Un botón para cambiar la contraseña en tiempo de ejecución.
o	Acceso a la pantalla de usuario después de un inicio de sesión exitoso.

2.	Pantalla de Usuario:
o	Campos para ingresar datos de usuario (edad, peso, altura) y un seleccionador de genero.
o	Un botón para calcular el IMC del usuario. Muestra el IMC calculado de acuerdo con los datos introducidos por el usuario.
o	Un enlace para mostrar los resultados y notas de las últimas pruebas realizadas.

3.	Pantalla de Pruebas:
o	Un buscador (SearchView) para filtrar las pruebas físicas según categoría.
o	Muestra las pruebas físicas con imágenes, sus nombre y enlaces que dirige a la página que explica la prueba.
o	Cuando se selecciona una prueba, se redirige a una nueva vista para introducir los datos necesarios para calcular la nota.

4.	Pantalla de Detalles de la Prueba:
o	Muestra los últimos datos de la prueba: resultado obtenido, la nota, la fecha de última prueba realizada.
o	Tiene un campo para introducir nuevo resultado y un botón para calcular nueva nota,
o	Tiene también un botón para guardar nuevos datos de la prueba.
Cada pantalla también tiene un botón  para volver hasta la pantalla anterior.

Funcionalidades Implementadas
Pantalla de Login
•	El usuario debe iniciar sesión con un nombre de usuario y contraseña.
•	La contraseña se puede cambiar en tiempo real utilizando un botón en la interfaz.
Gestión de Datos del Usuario
•	El usuario puede introducir su edad, peso, altura y sexo. Estos datos se almacenan de forma persistente.
•	En cualquier momento, el usuario puede modificar estos datos y los cambios se guardan automáticamente.
Pruebas Físicas
•	Las pruebas físicas se presentan agrupadas por categorías: Fuerza Muscular, Flexibilidad, Velocidad, Agilidad, Coordinación y Resistencia.
•	Cada prueba se muestra con una imagen representativa, nombre y un enlace a una descripción de la prueba.
•	Cuando el usuario selecciona una prueba, se muestra una nueva pantalla con los datos necesarios para calcular la nota de la prueba.
Cálculo del IMC
•	En la pantalla principal, el usuario puede presionar un botón para calcular su IMC. El cálculo se realiza utilizando los datos de peso y altura.
•	El resultado del IMC se muestra en una nueva vista.
Búsqueda de Pruebas
•	Se implementa un SearchView en la parte superior de la pantalla de pruebas que permite al usuario filtrar entre las pruebas físicas disponibles.
Impresión de Marcas y Notas
•	Un enlace en la pantalla de usuario permite al usuario ver las marcas y notas obtenidas en las distintas pruebas físicas realizadas.
Conclusión
Este proyecto utiliza Jetpack Compose y Kotlin para crear una aplicación funcional e intuitiva. La implementación de persistencia, cálculo del IMC y pruebas físicas aseguran que la aplicación cumpla con los requisitos del baremo EuroFit, proporcionando una experiencia útil y atractiva para el usuario.


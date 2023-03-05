# Practica 11

## Objetivo
Crear una aplicación web que permita el cálculo de hipotecas de tipo fijo. La aplicación debe permitir al usuario introducir los datos necesarios para el cálculo de la hipoteca y mostrar el resultado. La aplicación debe permitir registrar usuarios y guardar las hipotecas calculadas por cada usuario.

## Desarrollo
Se ha hecho uso de las siguientes tecnologías:
* Visual Studio Code
* Eclipse for Web Developers
* Apache Tomcat 9.0.65
* MySQL 8.0.26
* Java 11

## Requisitos
* La aplicación debe permitir registrar usuarios.
* La aplicación debe permitir iniciar sesión con un usuario registrado.
* La aplicación debe permitir calcular hipotecas de tipo fijo.
* La aplicación debe permitir guardar las hipotecas calculadas por cada usuario.
* La aplicación debe permitir mostrar las hipotecas calculadas por cada usuario.
* La aplicación debe permitir mostrar el detalle de una hipoteca calculada por cada usuario.

## Pasos externos a la aplicación

### Crear la base de datos
* Crear la base de datos `practica11` en MySQL.
* Crear el usuario `daw` con la contraseña `password` y darle permisos sobre la base de datos `practica11`.
* Permitir el acceso remoto a la base de datos `practica11` al usuario `daw` desde cualquier dirección IP.

### Preparar tomcat
En este caso, se ha usado la configuración por defecto de tomcat, por lo que no es necesario realizar ninguna configuración adicional.

## Despliegue
* Crear el fichero `practica11.war` con el contenido de la carpeta `target` del proyecto.
* Copiar el fichero `practica11.war` en la carpeta `webapps` de tomcat.
* Arrancar tomcat.
* Acceder a la aplicación en la URL `http://localhost:8080/practica11/`.


```
La práctica está realizada con un servidor externo de la red local de mi casa. En la entrega, todas las URL apuntan a localhost.
```

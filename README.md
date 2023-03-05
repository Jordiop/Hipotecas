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

## Screenshots

### Inicio
![imagen](https://user-images.githubusercontent.com/95173613/222990102-e2329c14-ed91-4a18-b755-4efb978fc434.png)

### Presupuesto tabla
![imagen](https://user-images.githubusercontent.com/95173613/222990114-3ba83997-8218-41f8-812f-6f7154ef01f7.png)

### Log In
![imagen](https://user-images.githubusercontent.com/95173613/222990087-7571524f-2673-4aca-8bac-3bc02807705f.png)

### Mis presupuestos
![imagen](https://user-images.githubusercontent.com/95173613/222990131-4381f220-a3b9-47a5-8c72-9b14c02b129d.png)

### Regenerar presupuestos
![imagen](https://user-images.githubusercontent.com/95173613/222990139-8c295354-f63b-4f61-be1f-d23a77fd740d.png)


# MeliChallengeOperacionFuego

[![CircleCI](https://circleci.com/gh/danielDaleman/MeliChallengeOperacionFuego.svg?style=svg&circle-token=2ed89382eeacbfba9bfa3d961b18d7682f0590d9)](https://circleci.com/gh/danielDaleman/MeliChallengeOperacionFuego)
======
Es un API Restfull el cual decifra la fuente y el contenido del mensaje de auxilio emitido por una nave portacarga imperial que se encuentra a la deriva en un campo de asteroides.

Además permite recibir la información de los 3 satelites (Kenobi, Skywalker, Sato) una a una, siendo almacenda para posteriormente decifrar la fuente y contenido del mensaje que fue emito.

### Tecnologías usadas
- Java 11
- Spring Boot
- Maven
- Heroku
- H2 DataBase
- Swagger
- Lombok
- Mockito
- CircleCI

## Instalación y ejecución

**Pre requisitos**:
- Contar con:
  - JDK 11.0
  - Maven 3.6.3
  - Git

**Paso 1**

Clonar el repositorio
```
git clone https://github.com/danielDaleman/MeliChallengeOperacionFuego
```

**Paso 2**

Abrir una consola dónde fue clonado el repositorio y ubicarse en la raiz del mismo.

**Paso 3**

Ejecutar el siguente comando para descargar las dependencias y ver que no exista ningun problema.
```
mvn clean install
```
**Paso4**

Para correr el aplicativo ejecute el siguente comando
```
java -jar target/challengemeli-0.0.1-SNAPSHOT.jar
```
**Nota:** 
*Asegurese de contar con la versión java y maven correcta*

## Consumo de API

Existe una configuración en postman, el cual le permitira realizar el consumo más facil, en el siguiente archivo: [archivo](https://github.com/danielDaleman/MeliChallengeOperacionFuego/tree/master/src/main/resources/postman), además cuenta con un archivo .txt, el cual tiene ejemplos para realizar la carga individual de los mensajes interceptados por satelite.

**Nota:** 
*El proyecto viene configurado con la url de heroku [https://melichallengeoperacionfuego.herokuapp.com/](), si desea usar la versión local basta con cambiarla por [http://localhost:8081/]()*

## Servidor de despliegue

El proyecto cuenta con integración continua con Circle CI, por lo que el despliegue al servidor es automatico, unos vez se suban cambios al repositorio y los test se ejecuten correctamente.

El proyecto se encuentra desplegado en HEROKU, el cual responde en [https://melichallengeoperacionfuego.herokuapp.com/]().




## Documentación de Servicios:
**Nota:** 
*El proyecto cuenta con documentación de los servicios, generada por SWAGGER. Para más detalle consulte la siguente url [https://melichallengeoperacionfuego.herokuapp.com/swagger-ui/index.html#/]()*

| Servicio | Tipo | Descripción |
|--|--|--|
| /topsecret | POST | Calcula la unicación de la nave y el mensaje emitido |
| /topsecret_split/{satelliteName} | POST | Carga en la base de datos H2 la información que capturo un satelite en particular |
| /topsecret_split/deleteAllDetaill | DELETE | Elimina todos los detalles capturados de los satelites, para iniciar un nuevo proceso |
| /topsecret_split/deleteByName/{name} | DELETE | Elimina el detalle captura de un satelite en particular |
| /topsecret_split/findAll | GET | Retornar todos los detalles capturados de los 3 satelites |
| /topsecret_split/generateMessage | GET | Retorna la información decifrada de la posición y mensaje emitido (Se debe contar con la carga de información de los 3 satelites)|

**Nota:** 
*El aplicativo precarga los 3 satelites en operación kenobi, skywalker, sato.*




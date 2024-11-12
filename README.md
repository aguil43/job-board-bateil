# Bolsa de trabajo BATEIL
------

## Descripcion:
Desarrollo de una apliacion web de una bolsa de trabajo donde se podra ver y aplicar para disintas ofertas de trabajo, con conexion a una base de datos, la cual permitira el registro de usuarios, administrar ofertas de trabajo (crear, eliminar y aplicar), ademas de montar distintas rutas del servidor web, cada una con una funcionalidad.

-------

### Desarrollo del proyecto:

##### Tablas en la DB:
* users: en esta tabla se registran todos los usuarios del sistema
* alumnis: se separa a los que fueron egresados de la institucion
* job offes: en esta tabla se almacenan las ofertas de trbajo con sus correspondientes datos
* profiles: esta tabla almacena los diferentes perfiles que puede tener un usuario, para relacionarlo de forma mas sencilla a las ofertas de trabajo
* companies: esta tabla almacena las empresas que han publicado alguna oferta de trabajo que han registrado algunas empresas en la plataforma.

##### Clases declaradas:
* app - es la clase donde se conecta todo el codigo y se monta el servidor web
* ReadEnv - es la clase que se encarga de leer el archivo env y convertir los valores de la variables a sus tipos en java
> HttpOpers - En esta carpeta se almacenaran las clases que involucren algo con algun tipo de operacion en http:
> * Login - en esta clase se procesa la parte del inicio de sesion de los usuarios (unicamente el inicio de sesion no el registro)


> DbQueries - En esta carpeta se almacenaran las clases que involucren interaccion con la BD:
> * DbConnection - Esta clase se encarga de realizar una conexion con la DB para despues poder realizar consultas mediante esta conexion.
> * DbQuery - Esta clase se encarga de utilizar la conexion realizada con la clase anterior haciendo consultas SQL(solo usando la sentencia SELECT), esta clase no modifica la base de datos, unicamente la va a leer.

##### Rutas montadas hasta el momento :
" / "  >  Pagina de inicio, aqui se mostrara la pagina principal del proyecto
" /login " > Pagina de inicio de sesion, aqui el usuario podra acceder a su cuenta

-------
### Dependencias:
* Java 22 with gradle (Lenguaje de programacion del backend)
* MySQL Connector J 9.1.0 (Libreria para conexion con la DB)
* Javalin 6.3.0 (Framework para montar el servicio web)
* Java dotenv (Libreria para leer el archivo .env)
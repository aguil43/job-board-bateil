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
> * Offers - en esta clase se procesan las ofertas de trabajo para posteriormente enviarlas como respuesta json en una ruta de la api
> * Job - en esta clase se procesa la informacion de una oferta de trabajo especifica para despues enviarla como json en una ruta de la api
> * Register = en esta clase se procesa la informacion para agregar nuevos usurios a la base de datos


> DbQueries - En esta carpeta se almacenaran las clases que involucren interaccion con la BD:
> * DbConnection - Esta clase se encarga de realizar una conexion con la DB para despues poder realizar consultas mediante esta conexion.
> * DbQuery - Esta clase se encarga de utilizar la conexion realizada con la clase anterior haciendo consultas SQL(solo usando la sentencia SELECT), esta clase no modifica la base de datos, unicamente la va a leer.
> * DbChange - Esta clase se encarga de realizar cualquier tipo de modificacion a la base de datos, como agregar informacion a alguna tabla o modificarla 

##### Rutas montadas hasta el momento :
**Rutas con interfaz grafica:**

" / "  >  Pagina de inicio, aqui se mostrara la pagina principal del proyecto (se monta el archivo index.html de la carpeta resources).

" /register " > Pagina para mostrar el menu de registro de usuarios (se monta el archivo sobreti.html de la carpeta resources).

" /login " > Pagina para mostrar el menu de inicio de sesion (se monta el archivo login.html de la carpeta resources).

**Rutas de la API para realizar consultas**

*Todas las rutas de la API comienzan con el /api/ seguido del dato que solicitan*

" /api/auth " > Pagina de la api con metodo POST para realizar la autenticacion del usuario, unicamente regresa un valor de tipo booleano para indicar si la autenticacion es correcta o no.

" /api/offers " > Pagina de la API con metodo GET para realizar la carga de los grupos de ofertas de trabajo que van de 10 puestos en 10 puestos, puede recibir un valor como parametro que es el numero de pagina, este valor es el "page", y es opcional, si se indica, manda las ofertas correspondientes a dicho grupo, si no se toma como la primer busqueda.

" /api/job " > Pagina de la API con el metodo GET para realizar la carga de una oferta de trabajo especifica, cargando unicamente los datos de esta, esta como parametro obligatorio requiere "jobid" e indicar el id de la oferta, para asi mostrar los datos de dicha oferta.

" /api/register " > Pagina de la API con el metodo POST para realizar un registro de usurio, recibe la informacion del usuario en parametros como el nombre de usuario "name", el id del usario y el correo del usuario (para agregarlo a las cookies).

-------
### Dependencias:
* Java 22 with gradle (Lenguaje de programacion del backend)
* MySQL Connector J 9.1.0 (Libreria para conexion con la DB)
* Javalin 6.3.0 (Framework para montar el servicio web)
* Java dotenv (Libreria para leer el archivo .env)
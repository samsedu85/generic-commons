GENERIC-COMMONS

Proyecto que contiene operaciones genericas de CRUD, para ser reutilizable en microservcios.

Pasos:

1- Descargar o clonar el proyecto con GIT "git clone <url_repo>"
2- Acceder a la ubicacion del proyecto y ejecutar los comandos "mvn clean install package"
3- Integrar como dependencia en algun otro microservicio
            <dependency>
			        <groupId>com.invex.commons</groupId>
			        <artifactId>generic-commons</artifactId>
			        <version>1.0.0-SNAPSHOT</version>
		        </dependency>

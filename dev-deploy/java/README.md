# uso

dentro de la carpeta del proyecto, construir la imagen para pruebas

`docker build -t adn-java-presupuesto-familiar:latest .`

luego levantar el ambiente para prueba

`docker compose up mysql java17 grafana-k6`

las variables de entorno recomendadas

    ### GRAFANA K6 ###############################################

    K6_APP_PATH=~/dev/adn/presupuesto-familiar/pruebas-carga

    ### JAVA17 ###############################################

    SRC_IMAGE=adn-java-presupuesto-familiar:latest
    JAVA_APP_JAR_PATH=microservicio/build/libs
    JAVA_APP_JAR_NAME=PresupuestoFamiliar-0.0.1-SNAPSHOT.jar

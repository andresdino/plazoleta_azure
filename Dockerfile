# Establecer la imagen base
FROM openjdk:18

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY "build/libs/plazoleta-service-0.0.1-SNAPSHOT.jar" "docker-plazoleta.jar"

# Exponer el puerto en el que la aplicación expone su servicio
EXPOSE 5000

# Comando de inicio para ejecutar la aplicación
CMD ["java", "-jar", "docker-plazoleta.jar"]



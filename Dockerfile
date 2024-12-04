FROM eclipse-temurin:21-jre-alpine
ADD /target/task_management_system-1.0.jar task_management_system-1.0.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=server", "-jar", "/task_management_system-1.0.jar"]

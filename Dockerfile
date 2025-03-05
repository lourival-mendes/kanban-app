FROM openjdk:17
COPY target/ /app/
WORKDIR /app
CMD ["java", "-jar", "kanban-app*.jar"]
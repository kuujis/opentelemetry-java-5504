FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ENV OTEL_SERVICE_NAME=basic-rest-server
ENV OTEL_TRACES_EXPORTER=logging
ENV OTEL_METRICS_EXPORTER=logging
ENV OTEL_LOGS_EXPORTER=logging
ENV OTEL_METRIC_EXPORT_INTERVAL=15000
ENV OTEL_TRACES_SAMPLER=parentbased_jaeger_remote
COPY ${JAR_FILE} app.jar
COPY run.sh .
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]
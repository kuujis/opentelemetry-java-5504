# opentelemetry-java-5504
Sandbox for reproducing https://github.com/open-telemetry/opentelemetry-java/issues/5504

## Usage

### Build image
> gradlew bootBuildImage --imageName=basic/rest

### Build Jaeger image with the failing sampling_strategies.json from https://github.com/open-telemetry/opentelemetry-java/issues/5504
> docker build -f customJaeger/customJaeger.Dockerfile -t jaegertracing_custom/all-in-one:latest customJaeger

### Run local Jaeger image
> docker run -d --name jaeger -e COLLECTOR_ZIPKIN_HOST_PORT=:9411   -p 5775:5775/udp   -p 6831:6831/udp   -p 6832:6832/udp   -p 5778:5778   -p 16686:16686   -p 14268:14268   -p 14250:14250   -p 9411:9411   jaegertracing_custom/all-in-one:latest

### Run basic rest server image 
> docker run -d --name basic-rest -p 8080:8080 basic/rest:latest

### Run basic rest server image with debug
> docker run -d --name basic-rest -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005" -p 8080:8080 -p 5005:5005 basic/rest:latest

## Invoking the endpoints

Swagger UI should be available after app starts on http://localhost:8080/swagger-ui/index.html 
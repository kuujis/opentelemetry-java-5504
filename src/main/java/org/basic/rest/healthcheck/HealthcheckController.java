package org.basic.rest.healthcheck;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log
@RestController
public class HealthcheckController {

    private final Tracer tracer;

    @Autowired
    public HealthcheckController(OpenTelemetry openTelemetry) {
        this.tracer = openTelemetry.getTracer(this.getClass().getName(), "0.0.1");
    }

    @GetMapping("healthcheck")
    public HealthCheckResponse healthCheck(){
        Span span = tracer.spanBuilder("healthcheck").startSpan();
        try (Scope scope = span.makeCurrent()){

            log.warning("starting healthcheck");


            return new HealthCheckResponse(UUID.randomUUID(), "all is fine");


        } catch (Throwable t){
            span.recordException(t);
            throw t;
        } finally {
            log.warning("healthcheck completed");
            span.end();
        }
    }
}

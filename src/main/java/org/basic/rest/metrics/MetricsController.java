package org.basic.rest.metrics;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import lombok.extern.java.Log;
import org.basic.rest.healthcheck.HealthCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log
@RestController
public class MetricsController {
    private final Tracer tracer;

    @Autowired
    public MetricsController(OpenTelemetry openTelemetry) {
        this.tracer = openTelemetry.getTracer(this.getClass().getName(), "0.0.1");
    }

    @GetMapping("metrics")
    public HealthCheckResponse healthCheck(){
        Span span = tracer.spanBuilder("metrics").startSpan();
        try (Scope scope = span.makeCurrent()){

            log.warning("starting metrics handling");


            return new HealthCheckResponse(UUID.randomUUID(), "Some random metrics message");


        } catch (Throwable t){
            span.recordException(t);
            throw t;
        } finally {
            log.warning("metrics handling completed");
            span.end();
        }
    }
}

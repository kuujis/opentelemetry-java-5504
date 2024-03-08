package org.basic.rest.healthcheck;

import java.util.UUID;

public record HealthCheckResponse(UUID id, String response) {

}

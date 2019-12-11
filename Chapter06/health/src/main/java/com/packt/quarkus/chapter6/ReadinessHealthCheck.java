package com.packt.quarkus.chapter6;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import java.nio.file.Files;
import java.nio.file.Paths;

@Readiness
@ApplicationScoped
public class ReadinessHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("File system Readiness check");


        boolean tempFileExists = Files.exists(Paths.get("/tmp/tmp.lck"));
        if (!tempFileExists) {
            responseBuilder.up();
        }
        else {
            responseBuilder.down()
                    .withData("error", "Lock file detected!");
        }

        return responseBuilder.build();
    }

}
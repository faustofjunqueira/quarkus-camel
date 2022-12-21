package com.github.faustofjunqueira.quarkuscamel.application.adapter.database.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class FlywayMigration {

    private final Flyway flyway;



    public void migrate() {
        flyway.clean();
        flyway.migrate();
        log.info(flyway.info().current().getVersion().toString());
    }
}

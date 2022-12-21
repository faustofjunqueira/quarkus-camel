package com.github.faustofjunqueira.quarkuscamel.application.adapter.cli;

import com.github.faustofjunqueira.quarkuscamel.application.adapter.database.config.FlywayMigration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@CommandLine.Command(name = "flyway:migrate", description = "Executa a migration do banco de dados")
@RequiredArgsConstructor
@Slf4j
public class FlywayMigrateCommand implements Runnable {

    private final FlywayMigration flyway;

    @Override
    public void run() {
        log.info("Start flyway migration");
        flyway.migrate();
    }
}
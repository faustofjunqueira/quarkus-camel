package com.github.faustofjunqueira.quarkuscamel.application;

import com.github.faustofjunqueira.quarkuscamel.application.adapter.cli.FlywayMigrateCommand;
import io.quarkus.picocli.runtime.annotations.TopCommand;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

import javax.inject.Inject;

@QuarkusMain
@Slf4j
@TopCommand
@CommandLine.Command(mixinStandardHelpOptions = true, subcommands = FlywayMigrateCommand.class)
public class Startup implements Runnable, QuarkusApplication {
    @Inject
    CommandLine.IFactory factory;

    @Override
    public void run() {
        log.info("Startup Api Application...");
        Quarkus.waitForExit();
    }

    @Override
    public int run(String... args) throws Exception {
        return new CommandLine(this, factory).execute(args);
    }
}

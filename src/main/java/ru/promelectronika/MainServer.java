package ru.promelectronika;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.LoggerFactory;
import ru.promelectronika.configs.Configs;
import ru.promelectronika.constants.IpAddress;
import ru.promelectronika.errorHandler.ErrorHandler;
import ru.promelectronika.rpc.RpcServer;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainServer {
    public static void main(String[] args) {

        try {
            Configs configs = new Configs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JoranConfigurator configurator = new JoranConfigurator();
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        configurator.setContext(loggerContext);
        loggerContext.reset();
        try {
            configurator.doConfigure(new File(Configs.getServerLogConfigPath()));
        } catch (JoranException e) {
            ErrorHandler.logMain(e);
        }

        RpcServer rpcServer = null;
        try {
            rpcServer = new RpcServer(IpAddress.SERVER_ADDRESS, 8080, 8070, 8090);
        } catch (IOException e) {
            ErrorHandler.logMain(e);
        }

        while (true) {
            try {
                Objects.requireNonNull(rpcServer).start();
            } catch (IOException | ClassCastException | NullPointerException e) {
                ErrorHandler.logMain(e);
            }
        }
    }
}

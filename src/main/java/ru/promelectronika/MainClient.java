package ru.promelectronika;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.LoggerFactory;
import ru.promelectronika.configs.Configs;
import ru.promelectronika.constants.IpAddress;
import ru.promelectronika.errorHandler.ErrorHandler;
import ru.promelectronika.powerBlock.CanBus;
import ru.promelectronika.rpc.GbtClient;

import java.io.File;
import java.io.IOException;

public class MainClient {

//    static PowerBlock block = new PowerBlock((byte) 1);
    static CanBus canBus = new CanBus();

    public static void main(String[] args) throws InterruptedException {

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

        GbtClient gbtClient = null;
        try {
            gbtClient = new GbtClient();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Thread.sleep(100);
        gbtClient.rpcConnectRequest(IpAddress.SERVER_ADDRESS, IpAddress.SERVER_PORT);
        Thread.sleep(100);

        gbtClient.rpcAuthorize();
        Thread.sleep(100);

        gbtClient.rpcSetInvertorLimits();
        Thread.sleep(100);

        while (true) {
            try {
//                gbtClient.rpcSetInvertorPresentsParams();
//                Thread.sleep(100);
//
//                gbtClient.rpcSetIsolationState();
//                Thread.sleep(100);
//
//                canBus.sendFrameId100();
//                Thread.sleep(100);
//
//                canBus.sendFrameId101();
//                Thread.sleep(100);
//
//                canBus.sendFrameId102();
//                Thread.sleep(100);

                canBus.sendMsgBHM();
                Thread.sleep(100);

                gbtClient.rpcUserStop();
                Thread.sleep(100);

            } catch (ClassCastException | NullPointerException e) {
                ErrorHandler.logMain(e);
            }

        }
    }
}


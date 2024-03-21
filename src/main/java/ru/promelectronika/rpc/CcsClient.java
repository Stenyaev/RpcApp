package ru.promelectronika.rpc;

import ru.promelectronika.constants.IpAddress;
import ru.promelectronika.errorHandler.ErrorHandler;
import ru.promelectronika.powerBlock.PowerBlock;

import java.io.IOException;

public class CcsClient extends RpcClient {
    private static final String ADDRESS = IpAddress.CCS_ADDRESS;

    private static final int PORT = IpAddress.CCS_PORT;

    private static final String INTERFACE_ID = "IID_SECC_CCS_2.0";
    private final float MAX_VOLTAGE_LIMIT = 1000f;
    private final float MAX_CURRENT_LIMIT = 100f;
    private final float MIN_VOLTAGE_LIMIT = 150f;
    private final float MIN_CURRENT_LIMIT = 0f;
    private final float PEAK_CURRENT_RIPPLE_A = 150;
    private float maxPowerLimit = 30000f;
    private PowerBlock block;
    private boolean invertorError = false;
    private int countErrorMessage = 0;

    public void setInvertorError(boolean invertorError) {
        this.invertorError = invertorError;
    }

    public CcsClient(PowerBlock powerBlock) throws IOException {
        super(ADDRESS, PORT);
        this.block = powerBlock;
    }

    public float getMaxPowerLimit() {
        return maxPowerLimit;
    }

    public void setMaxPowerLimit(float maxPowerLimit) {
        this.maxPowerLimit = maxPowerLimit;
        rpcSetInvertorLimits();
    }

    public void rpcConnectRequest(String remoteAddress, int remotePort) {
        int connectionTimeout = 1000;
        int pingPeriod = 200;
        int pingCheckCount = 5;
        Object[] arg = {INTERFACE_ID, remoteAddress, remotePort, connectionTimeout, pingPeriod, pingCheckCount};
        try {
            sendMessage("rpcConnectRequest", arg);
        } catch (Throwable e) {
            ErrorHandler.logCcs(e);
        }
        StringBuilder str = new StringBuilder();
        str.append("\n\tInterface ID: ");
        str.append(INTERFACE_ID);
        str.append("\n\tRemote Address: ");
        str.append(remoteAddress);
        str.append("\n\tRemote Port: ");
        str.append(remotePort);
        str.append("\n\tConnection Timeout: ");
        str.append(connectionTimeout);
        str.append("\n\tPing Period: ");
        str.append(pingPeriod);
        str.append("\n\tPing Check Count: ");
        str.append(pingCheckCount);
        ErrorHandler.loggerClientCcs.info("rpcConnectRequest: {}", str.toString());
    }
    public void rpcPing() {
        int selfConnectionInputState = 2;
        int selfConnectionOutputState = 2;
        Object[] pingParams = {selfConnectionInputState, selfConnectionOutputState};
        try {
            sendMessage("rpcPing", pingParams);
            countErrorMessage = 0;
        } catch (Throwable e) {
            ErrorHandler.logCcs(e);
            countErrorMessage += 1;
            if (countErrorMessage == 2) {
                throw new RuntimeException();
            }
        }
        StringBuilder str = new StringBuilder();
        str.append("\n\tConnection Input State: ");
        str.append(2);
        str.append("\n\tConnection Output State: ");
        str.append(2);
        ErrorHandler.loggerClientCcs.debug("rpcPing: {}", str.toString());
    }
    public void rpcSetInvertorLimits() {
        Object[] arg = {maxPowerLimit, MAX_VOLTAGE_LIMIT,
                block.getVout() != 0.0 ? maxPowerLimit/block.getVout() : MAX_CURRENT_LIMIT,
                MIN_VOLTAGE_LIMIT, MIN_CURRENT_LIMIT, PEAK_CURRENT_RIPPLE_A};
        try {
            sendMessage("SET_INVERTOR_LIMITS", arg);
        } catch (Throwable e) {
            ErrorHandler.logChademo(e);
        }
        StringBuilder str = new StringBuilder();
        str.append("\n\tMax Power Limit: ");
        str.append(maxPowerLimit);
        str.append("\n\tMax Voltage Limit: ");
        str.append(MAX_VOLTAGE_LIMIT);
        str.append("\n\tMax Current Limit: ");
        str.append((float) arg[2]);
        str.append("\n\tMin Voltage Limit: ");
        str.append(MIN_VOLTAGE_LIMIT);
        str.append("\n\tMin Current Limit: ");
        str.append(MIN_CURRENT_LIMIT);
        str.append("\n\tPeak Current Ripple: ");
        str.append(PEAK_CURRENT_RIPPLE_A);
        ErrorHandler.loggerClientCcs.info("rpcSetInvertorLimits: {}", str.toString());
    }
    public void rpcSetInvertorPresentsParams() {
        boolean isInvertorOn = block.getShutDownDCDC();
        boolean isInvertorError = invertorError;
        float u = block.getVout();
        float i = block.getIout();
        Object[] arg = {isInvertorOn, isInvertorError, u, i};
        try {
            sendMessage("SET_INVERTOR_PRESENTS_PARAMS", arg);
        } catch (Throwable e) {
            ErrorHandler.logCcs(e);
        }
        StringBuilder str = new StringBuilder();
        str.append("\n\tIs Invertor On: ");
        str.append(isInvertorOn);
        str.append("\n\tIs Invertor Error: ");
        str.append(isInvertorError);
        str.append("\n\tPresent Voltage: ");
        str.append(u);
        str.append("\n\tPresent Current: ");
        str.append(i);
        ErrorHandler.loggerClientCcs.info("rpcSetInvertorPresentsParams: {}", str.toString());

    }
    public void rpcSetIsolationState() {
        boolean isIsolationMonitoring = true;
        boolean isImdTest = false;
        int isolationLevel = 5;
        Object[] arg = {isIsolationMonitoring, isImdTest, isolationLevel};
        try {
            sendMessage("SET_ISOLATION_STATE", arg);
        } catch (Throwable e) {
            ErrorHandler.logCcs(e);
        }
        StringBuilder str = new StringBuilder();
        str.append("\n\tIs Isolation Monitoring: ");
        str.append(isIsolationMonitoring);
        str.append("\n\tIs Imd Test: ");
        str.append(isImdTest);
        str.append("\n\tIsolation Level: ");
        str.append(isolationLevel);
        ErrorHandler.loggerClientCcs.info("rpcSetIsolationState: {}", str.toString());
    }
    public void rpcAuthorize() {
        try {
            sendMessage("AUTHORIZE");
        } catch (Throwable e) {
            ErrorHandler.logCcs(e);
        }
        ErrorHandler.loggerClientCcs.info("rpcAuthorize");
    }
    public void rpcUserStop() {
        try {
            sendMessage("USER_STOP");
        } catch (Throwable e) {
            ErrorHandler.logCcs(e);
        }
        ErrorHandler.loggerClientCcs.info("rpcUserStop");
    }
    public String getName() {
        return "ccs";
    }
}

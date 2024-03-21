package ru.promelectronika.powerBlock;

public class Vehicle {
    private volatile String minChargeCurrent;
    private volatile String minBatteryVoltage;
    private volatile String maxBatteryVoltage;
    private volatile String chargedRateReferenceConstant;

    private volatile String maxChargingTime10s;
    private volatile String maxChargingTime1min;
    private volatile String estimatedChargingTime;
    private volatile String totalCapacityOfTractionBattery;

    private volatile String protocolNumber;
    private volatile String targetBatteryVoltage;
    private volatile String chargingCurrentRequest;
    private volatile String errorFlags;
    private volatile boolean batteryVoltageDeviationError;
    private volatile boolean highBatteryTemperature;
    private volatile boolean batteryCurrentDeviationError;
    private volatile boolean batteryUnderVoltage;
    private volatile boolean batteryOverVoltage;
    private volatile String statusFlags;
    private volatile boolean normalStopRequestBeforeCharging;
    private volatile boolean vehicleStatus;
    private volatile boolean chargingSystemError;
    private volatile boolean vehicleShiftPosition;
    private volatile boolean vehicleChargingEnabled;
    private volatile String stateOfCharge;

    public String getMinChargeCurrent() {
        return minChargeCurrent;
    }

    public void setMinChargeCurrent(String minChargeCurrent) {
        this.minChargeCurrent = minChargeCurrent;
    }

    public void setMinChargeCurrent(int value) {
        if (value > 200) {
            value = 200;
        } else if (value < 0) {
            value = 0;
        }
        String s = Integer.toHexString(value).toUpperCase();
        if (s.length() < 2) {
            this.minChargeCurrent = "0" + s;
        } else {
            this.minChargeCurrent = s;
        }
    }

    public String getMinBatteryVoltage() {
        return minBatteryVoltage;
    }

    public void setMinBatteryVoltage(String minBatteryVoltage) {
        this.minBatteryVoltage = minBatteryVoltage;
    }

    public void setMinBatteryVoltage(int value) {
        if (value > 600) {
            value = 600;
        } else if (value < 0) {
            value = 0;
        }
        String s = Integer.toHexString(value).toUpperCase();
        if (s.length() < 2) {
            this.minBatteryVoltage = "0" + s + "00";
        } else if (s.length() == 2) {
            this.minBatteryVoltage = s.substring(0, 2) + "00";
        } else if (s.length() == 3) {
            this.minBatteryVoltage = s.substring(1, 3) + "0" + s.charAt(0);
        } else if (s.length() == 4) {
            this.minBatteryVoltage = s.substring(1, 4) + s.substring(0, 2);
        }
    }

    public String getMaxBatteryVoltage() {
        return maxBatteryVoltage;
    }

    public void setMaxBatteryVoltage(String maxBatteryVoltage) {
        this.maxBatteryVoltage = maxBatteryVoltage;
    }

    public void setMaxBatteryVoltage(int value) {
        if (value > 600) {
            value = 600;
        } else if (value < 0) {
            value = 0;
        }
        String s = Integer.toHexString(value).toUpperCase();
        if (s.length() < 2) {
            this.maxBatteryVoltage = "0" + s + "00";
        } else if (s.length() == 2) {
            this.maxBatteryVoltage = s.substring(0, 2) + "00";
        } else if (s.length() == 3) {
            this.maxBatteryVoltage = s.substring(1, 3) + "0" + s.charAt(0);
        } else if (s.length() == 4) {
            this.maxBatteryVoltage = s.substring(1, 4) + s.substring(0, 2);
        }
    }

    public String getChargedRateReferenceConstant() {
        return chargedRateReferenceConstant;
    }

    public void setChargedRateReferenceConstant(String chargedRateReferenceConstant) {
        this.chargedRateReferenceConstant = chargedRateReferenceConstant;
    }

    public void setChargedRateReferenceConstant(int value) {
        if (value > 100) {
            value = 100;
        } else if (value < 0) {
            value = 0;
        }
        String s = Integer.toHexString(value).toUpperCase();
        if (s.length() < 2) {
            this.chargedRateReferenceConstant = "0" + s;
        } else {
            this.chargedRateReferenceConstant = s;
        }
    }

    public String getMaxChargingTime10s() {
        return maxChargingTime10s;
    }

    public void setMaxChargingTime10s(String maxChargingTime10s) {
        this.maxChargingTime10s = maxChargingTime10s;
    }

    public String getMaxChargingTime1min() {
        return maxChargingTime1min;
    }

    public int getMaxChargingTime1minDec() {
        return Integer.parseInt(maxChargingTime1min, 16);
    }

    public void setMaxChargingTime1min(String maxChargingTime1min) {
        this.maxChargingTime1min = maxChargingTime1min;
    }

    public void setMaxChargingTime1min(int value) {
        if (value > 255) {
            value = 255;
        } else if (value < 0) {
            value = 0;
        }
        String s = Integer.toHexString(value).toUpperCase();
        if (s.length() < 2) {
            this.maxChargingTime1min = "0" + s;
        } else {
            this.maxChargingTime1min = s;
        }
    }

    public String getEstimatedChargingTime() {
        return estimatedChargingTime;
    }

    public void setEstimatedChargingTime(String estimatedChargingTime) {
        this.estimatedChargingTime = estimatedChargingTime;
    }

    public void setEstimatedChargingTime(int value) {
        if (value > 254) {
            value = 254;
        } else if (value < 0) {
            value = 0;
        }
        String s = Integer.toHexString(value).toUpperCase();
        if (s.length() < 2) {
            this.estimatedChargingTime = "0" + s;
        } else {
            this.estimatedChargingTime = s;
        }
    }

    public String getTotalCapacityOfTractionBattery() {
        return totalCapacityOfTractionBattery;
    }

    public int getTotalCapacityOfTractionBatteryDec() {
        return Integer.parseInt(totalCapacityOfTractionBattery, 16);
    }

    public void setTotalCapacityOfTractionBattery(String totalCapacityOfTractionBattery) {
        this.totalCapacityOfTractionBattery = totalCapacityOfTractionBattery;
    }

    public void setTotalCapacityOfTractionBattery(int value) {
        if (value > 65535) {
            value = 65535;
        } else if (value < 0) {
            value = 0;
        }
        String s = Integer.toHexString(value).toUpperCase();
        if (s.length() < 2) {
            this.totalCapacityOfTractionBattery = "0" + s + "00";
        } else if (s.length() == 2) {
            this.totalCapacityOfTractionBattery = s.substring(0, 2) + "00";
        } else if (s.length() == 3) {
            this.totalCapacityOfTractionBattery = s.substring(1, 3) + "0" + s.charAt(0);
        } else if (s.length() == 4) {
            this.totalCapacityOfTractionBattery = s.substring(1, 4) + s.substring(0, 2);
        }
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getTargetBatteryVoltage() {
        return targetBatteryVoltage;
    }

    public void setTargetBatteryVoltage(String targetBatteryVoltage) {
        this.targetBatteryVoltage = targetBatteryVoltage;
    }

    public void setTargetBatteryVoltage(int value) {
        if (value > 600) {
            value = 600;
        } else if (value < 0) {
            value = 0;
        }
        String s = Integer.toHexString(value).toUpperCase();
        if (s.length() < 2) {
            this.targetBatteryVoltage = "0" + s + "00";
        } else if (s.length() == 2) {
            this.targetBatteryVoltage = s.substring(0, 2) + "00";
        } else if (s.length() == 3) {
            this.targetBatteryVoltage = s.substring(1, 3) + "0" + s.charAt(0);
        } else if (s.length() == 4) {
            this.targetBatteryVoltage = s.substring(1, 4) + s.substring(0, 2);
        }
    }

    public String getChargingCurrentRequest() {
        return chargingCurrentRequest;
    }

    public int getChargingCurrentRequestDec() {
        return Integer.parseInt(chargingCurrentRequest, 16);
    }

    public void setChargingCurrentRequest(String chargingCurrentRequest) {
        this.chargingCurrentRequest = chargingCurrentRequest;
    }

    public void setChargingCurrentRequest(int value) {
        if (value > 200) {
            value = 200;
        } else if (value < 0) {
            value = 0;
        }
        String s = Integer.toHexString(value).toUpperCase();
        if (s.length() < 2) {
            this.chargingCurrentRequest = "0" + s;
        } else {
            this.chargingCurrentRequest = s;
        }
    }

    public String getErrorFlags() {
        return errorFlags;
    }

    public void setErrorFlags(String errorFlags) {
        this.errorFlags = errorFlags;
    }

    public boolean isBatteryVoltageDeviationError() {
        return batteryVoltageDeviationError;
    }

    public void setBatteryVoltageDeviationError(boolean batteryVoltageDeviationError) {
        this.batteryVoltageDeviationError = batteryVoltageDeviationError;
        int n;
        if (batteryVoltageDeviationError) {
            n = Integer.parseInt(errorFlags, 16) | (1 << 4);
        } else {
            n = Integer.parseInt(errorFlags, 16) & ~(1 << 4);
        }
        String s = Integer.toHexString(n).toUpperCase();
        if (s.length() < 2) {
            s = "0" + s;
        }
        errorFlags = s;
    }

    public boolean isHighBatteryTemperature() {
        return highBatteryTemperature;
    }

    public void setHighBatteryTemperature(boolean highBatteryTemperature) {
        this.highBatteryTemperature = highBatteryTemperature;
        int n;

        if (highBatteryTemperature) {
            n = Integer.parseInt(errorFlags, 16) | (1 << 3);
        } else {
            n = Integer.parseInt(errorFlags, 16) & ~(1 << 3);
        }
        String s = Integer.toHexString(n).toUpperCase();
        if (s.length() < 2) {
            s = "0" + s;
        }
        errorFlags = s;
    }

    public boolean isBatteryCurrentDeviationError() {
        return batteryCurrentDeviationError;
    }

    public void setBatteryCurrentDeviationError(boolean batteryCurrentDeviationError) {
        this.batteryCurrentDeviationError = batteryCurrentDeviationError;
        int n;
        if (batteryCurrentDeviationError) {
            n = Integer.parseInt(errorFlags, 16) | (1 << 2);
        } else {
            n = Integer.parseInt(errorFlags, 16) & ~(1 << 2);
        }
        String s = Integer.toHexString(n).toUpperCase();
        if (s.length() < 2) {
            s = "0" + s;
        }
        errorFlags = s;
    }

    public boolean isBatteryUnderVoltage() {
        return batteryUnderVoltage;
    }

    public void setBatteryUnderVoltage(boolean batteryUnderVoltage) {
        this.batteryUnderVoltage = batteryUnderVoltage;
        int n;

        if (batteryUnderVoltage) {
            n = Integer.parseInt(errorFlags, 16) | (1 << 1);
        } else {
            n = Integer.parseInt(errorFlags, 16) & ~(1 << 1);
        }
        String s = Integer.toHexString(n).toUpperCase();
        if (s.length() < 2) {
            s = "0" + s;
        }
        errorFlags = s;
    }

    public boolean isBatteryOverVoltage() {
        return batteryOverVoltage;
    }

    public void setBatteryOverVoltage(boolean batteryOverVoltage) {
        this.batteryOverVoltage = batteryOverVoltage;
        int n;

        if (batteryOverVoltage) {
            n = Integer.parseInt(errorFlags, 16) | (1 << 0);
        } else {
            n = Integer.parseInt(errorFlags, 16) & ~(1 << 0);
        }
        String s = Integer.toHexString(n).toUpperCase();
        if (s.length() < 2) {
            s = "0" + s;
        }
        errorFlags = s;
    }

    public String getStatusFlags() {
        return statusFlags;
    }

    public void setStatusFlags(String statusFlags) {
        this.statusFlags = statusFlags;
    }

    public boolean isNormalStopRequestBeforeCharging() {
        return normalStopRequestBeforeCharging;
    }

    public void setNormalStopRequestBeforeCharging(boolean normalStopRequestBeforeCharging) {
        this.normalStopRequestBeforeCharging = normalStopRequestBeforeCharging;
        int n;
        if (normalStopRequestBeforeCharging) {
            n = Integer.parseInt(statusFlags, 16) | (1 << 4);
        } else {
            n = Integer.parseInt(statusFlags, 16) & ~(1 << 4);
        }
        String s = Integer.toHexString(n).toUpperCase();
        if (s.length() < 2) {
            s = "0" + s;
        }
        statusFlags = s;
    }

    public boolean isVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(boolean vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
        int n;

        if (vehicleStatus) {
            n = Integer.parseInt(statusFlags, 16) | (1 << 3);
        } else {
            n = Integer.parseInt(statusFlags, 16) & ~(1 << 3);
        }
        String s = Integer.toHexString(n).toUpperCase();
        if (s.length() < 2) {
            s = "0" + s;
        }
        statusFlags = s;
    }

    public boolean isChargingSystemError() {
        return chargingSystemError;
    }

    public void setChargingSystemError(boolean chargingSystemError) {
        this.chargingSystemError = chargingSystemError;
        int n;

        if (chargingSystemError) {
            n = Integer.parseInt(statusFlags, 16) | (1 << 2);
        } else {
            n = Integer.parseInt(statusFlags, 16) & ~(1 << 2);
        }
        String s = Integer.toHexString(n).toUpperCase();
        if (s.length() < 2) {
            s = "0" + s;
        }
        statusFlags = s;
    }

    public boolean isVehicleShiftPosition() {
        return vehicleShiftPosition;
    }

    public void setVehicleShiftPosition(boolean vehicleShiftPosition) {
        this.vehicleShiftPosition = vehicleShiftPosition;

        int n;

        if (vehicleShiftPosition) {
            n = Integer.parseInt(statusFlags, 16) | (1 << 1);
        } else {
            n = Integer.parseInt(statusFlags, 16) & ~(1 << 1);
        }
        String s = Integer.toHexString(n).toUpperCase();
        if (s.length() < 2) {
            s = "0" + s;
        }
        statusFlags = s;
    }

    public boolean isVehicleChargingEnabled() {
        return vehicleChargingEnabled;
    }

    public void setVehicleChargingEnabled(boolean vehicleChargingEnabled) {
        this.vehicleChargingEnabled = vehicleChargingEnabled;

        int n;

        if (vehicleChargingEnabled) {
            n = Integer.parseInt(statusFlags, 16) | (1 << 0);
        } else {
            n = Integer.parseInt(statusFlags, 16) & ~(1 << 0);
        }
        String s = Integer.toHexString(n).toUpperCase();
        if (s.length() < 2) {
            s = "0" + s;
        }
        statusFlags = s;
    }

    public String getStateOfCharge() {
        return stateOfCharge;
    }

    public void setStateOfCharge(String stateOfCharge) {
        this.stateOfCharge = stateOfCharge;
    }

    public void setStateOfCharge(int value) {
        if (value > 254) {
            value = 254;
        } else if (value < 0) {
            value = 0;
        }
        String s = Integer.toHexString(value).toUpperCase();
        if (s.length() < 2) {
            this.stateOfCharge = "0" + s;
        } else {
            this.stateOfCharge = s;
        }
    }
}

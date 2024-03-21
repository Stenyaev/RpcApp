package ru.promelectronika.powerBlock;



public class FrameBuilder {
    private final Vehicle vehicle = new Vehicle();

    public FrameBuilder() {
        vehicle.setMinChargeCurrent(0);
        vehicle.setMinBatteryVoltage(50);
        vehicle.setMaxBatteryVoltage(500);
        vehicle.setChargedRateReferenceConstant("64");

        vehicle.setMaxChargingTime10s("FF");
        vehicle.setMaxChargingTime1min(100);
        vehicle.setEstimatedChargingTime(20);
        vehicle.setTotalCapacityOfTractionBattery(700);

        vehicle.setProtocolNumber("03");
        vehicle.setTargetBatteryVoltage(400);
        vehicle.setChargingCurrentRequest(0);
        vehicle.setErrorFlags("00");
        vehicle.setBatteryVoltageDeviationError(true);
        vehicle.setHighBatteryTemperature(true);
        vehicle.setBatteryCurrentDeviationError(true);
        vehicle.setBatteryUnderVoltage(true);
        vehicle.setBatteryOverVoltage(true);
        vehicle.setStatusFlags("00");
        vehicle.setNormalStopRequestBeforeCharging(false);
        vehicle.setVehicleStatus(true);
        vehicle.setChargingSystemError(false);
        vehicle.setVehicleShiftPosition(false);
        vehicle.setVehicleChargingEnabled(true);
        vehicle.setStateOfCharge(0);
    }

    public String buildFrameId100() {
        StringBuilder sb = new StringBuilder();
        sb.append('t')
                .append("100")
                .append("8")
                .append(vehicle.getMinChargeCurrent())
                .append("00")
                .append(vehicle.getMinBatteryVoltage())
                .append(vehicle.getMaxBatteryVoltage())
                .append(vehicle.getChargedRateReferenceConstant())
                .append("00")
                .append("\n");
        return sb.toString();
    }

    public String buildFrameId101() {
        StringBuilder sb = new StringBuilder();
        sb.append('t')
                .append("101")
                .append("8")
                .append("00")
                .append(vehicle.getMaxChargingTime10s())
                .append(vehicle.getMaxChargingTime1min())
                .append(vehicle.getEstimatedChargingTime())
                .append("00")
                .append(vehicle.getTotalCapacityOfTractionBattery())
                .append("00")
                .append("\n");
        return sb.toString();
    }

    public String buildFrameId102() {
        StringBuilder sb = new StringBuilder();
        sb.append('t')
                .append("102")
                .append("8")
                .append(vehicle.getProtocolNumber())
                .append(vehicle.getTargetBatteryVoltage())
                .append(vehicle.getChargingCurrentRequest())
                .append(vehicle.getErrorFlags())
                .append(vehicle.getStatusFlags())
                .append(vehicle.getStateOfCharge())
                .append("00")
                .append("\n");
        return sb.toString();
    }
}

package ru.promelectronika.powerBlock;


import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;


public class CanBus{
    private static FrameBuilder frameBuilder;
    public static final char STANDARD_ID = 't';
    public static final char EXTENDED_ID = 'T';
    private static SerialPort serialPort;
    private int countUnsentMessage = 0;
    private int countErrorSentMessage = 0;
    private boolean CHMrecived = false;

    public CanBus() {
        frameBuilder = new FrameBuilder();
        serialPort = new SerialPort("COM9");
        try {
            if (!(serialPort.isOpened())) {
                serialPort.openPort();
            }
            serialPort.setParams(3_000_000, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
            serialPort.addEventListener(new PortReader());
        } catch (SerialPortException e) {
            System.out.println("SerialPort unavailable");
            try {
                Thread.sleep(400);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void sendFrame(String data) {
        if (data.length() == 25) {
            data = EXTENDED_ID+data+"\n";
        } else if (data.length() == 20) {
            data = STANDARD_ID+data+"\n";
        } else {
            //Неверное сообщение
        }
        try {
            Thread.sleep(5);
            serialPort.purgePort(SerialPort.PURGE_TXCLEAR);
            serialPort.writeBytes(data.getBytes());
            countUnsentMessage += 1;
            if (countUnsentMessage == 50) {
                System.out.println("CAN adapter not responding");

                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } catch (SerialPortException | InterruptedException e) {
            countErrorSentMessage += 1;
            if (countErrorSentMessage == 10) {
                System.out.println("Message transmission error");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void sendMsgBHM() {
        StringBuilder canMsg = new StringBuilder();
        String id = "182756F4";

        int data = 0xA00F;
        String dataStr = Integer.toHexString(data).toUpperCase();
        if (dataStr.length()%2!=0){ // должно быть четное число символов
            dataStr = "0"+dataStr;
        }

        String canMessage = String.valueOf(canMsg
                .append("T")
                .append(id)
                .append(dataStr.length()/2)
                .append(dataStr)
                .append("\n"));

        try {
            Thread.sleep(5);
            serialPort.purgePort(SerialPort.PURGE_TXCLEAR);
            serialPort.writeBytes(canMessage.getBytes());
        } catch (SerialPortException | InterruptedException e) {
            throw new RuntimeException(e);
        }
//        sendFrame(canMessage);
    }

    public void sendFrameId100() {
        try {
            serialPort.writeString(frameBuilder.buildFrameId100());
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendFrameId101() {
        try {
            serialPort.writeString(frameBuilder.buildFrameId101());
        } catch (SerialPortException e) {
            throw new RuntimeException(e);        }
    }

    public void sendFrameId102() {
        try {
            serialPort.writeString(frameBuilder.buildFrameId102());
        } catch (SerialPortException e) {
            throw new RuntimeException(e);        }
    }
    public void readFrame() {
        try {
            String str = serialPort.readString();
            if (str.contains("T")) {
                String[] arrayStr = str.split("\r");
                for (String aString: arrayStr) {
                    if (aString.contains("T")) {
                        CanHandler.handleMsg(aString);
                    }
                }
            }
            if (str.contains("Z") || str.contains("z")) {
                countUnsentMessage = 0;
                countErrorSentMessage = 0;
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    private class PortReader implements SerialPortEventListener{

        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            if (serialPortEvent.isRXCHAR()) {
                readFrame();
            }
        }
    }
}



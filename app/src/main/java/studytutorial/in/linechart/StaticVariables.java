package studytutorial.in.linechart;


import android.bluetooth.BluetoothSocket;

class StaticVariables {
    private static BluetoothSocket socket;

    public static BluetoothSocket getSocket() {
        return socket;
    }

    public static void setSocket(BluetoothSocket socketnew) {
        socket = socketnew;
    }

}
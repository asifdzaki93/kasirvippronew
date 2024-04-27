package id.kasirvippro.android.callback;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;

public interface BluetoothCallback {
    void onPowerOn(Intent intent);
    void onPowerOff(Intent intent);
    void onConnected(BluetoothSocket socket, int taskType);
    void onError(String msg);
}

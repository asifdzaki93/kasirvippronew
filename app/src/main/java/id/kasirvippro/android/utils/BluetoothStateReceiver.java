package id.kasirvippro.android.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import id.kasirvippro.android.callback.BluetoothCallback;

public class BluetoothStateReceiver extends BroadcastReceiver {

    private BluetoothCallback bluetoothCallback;

    public BluetoothStateReceiver(BluetoothCallback bluetoothCallback) {
        this.bluetoothCallback = bluetoothCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
        switch (state) {
            case BluetoothAdapter.STATE_ON:
            case BluetoothAdapter.STATE_TURNING_ON:
                bluetoothCallback.onPowerOn(intent);
                break;
            case BluetoothAdapter.STATE_OFF:
            case BluetoothAdapter.STATE_TURNING_OFF:
                bluetoothCallback.onPowerOff(intent);
                break;
        }
    }
}

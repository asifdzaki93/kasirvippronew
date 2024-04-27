package id.kasirvippro.android.utils;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import id.kasirvippro.android.callback.BluetoothCallback;

public class BluetoothConnectTask  extends AsyncTask<BluetoothDevice, Integer, BluetoothSocket> {

    private BluetoothCallback callback;
    private int typeTask;
    private String msg;

    public BluetoothConnectTask(BluetoothCallback callback, int typeTask, String msg) {
        this.callback = callback;
        this.typeTask = typeTask;
        this.msg = msg;
    }

    @Override
    protected BluetoothSocket doInBackground(BluetoothDevice... bluetoothDevices) {
        return BluetoothUtil.connectDevice(bluetoothDevices[0]);
    }

    @Override
    // Once the image is downloaded, associates it to the imageView
    protected void onPostExecute(BluetoothSocket socket) {
        if(socket != null){
            callback.onConnected(socket,typeTask);
        }
        else {
            callback.onError(msg);
        }
    }
}

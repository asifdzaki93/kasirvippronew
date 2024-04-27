package id.kasirvippro.android.feature.printerDaily;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import id.kasirvippro.android.R;

public class PrinterDailyAdapter extends ArrayAdapter<BluetoothDevice> {

    private int selected = -1;
    private BluetoothDevice selectedDevice = null;

    public PrinterDailyAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final BluetoothDevice device = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_bluetooth_device, parent, false);
        }

        TextView tvDeviceName = (TextView) convertView.findViewById(R.id.tv_device_name);
        RadioButton cbDevice = (RadioButton) convertView.findViewById(R.id.cb_device);

        tvDeviceName.setText(device.getName());

        cbDevice.setChecked(position == selected);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = position;
                selectedDevice = device;
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public int getSelected() {
        return selected;
    }

    public BluetoothDevice getSelectedDevice() {
        return selectedDevice;
    }
}

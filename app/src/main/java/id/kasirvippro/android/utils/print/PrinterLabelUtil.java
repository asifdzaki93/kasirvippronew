package id.kasirvippro.android.utils.print;

/**
 * Created by Ahmed on 08/03/18.
 */

import android.bluetooth.BluetoothSocket;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import id.kasirvippro.android.models.store.Store;
import id.kasirvippro.android.models.transaction.DetailLabel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class PrinterLabelUtil {

    private OutputStreamWriter mWriter = null;
    private OutputStream mOutputStream = null;

    public final static int WIDTH_PIXEL = 384;
    public final static int IMAGE_SIZE = 320;
    public final static int MAX_CHAR = 42;

    /**
     * Inisialisasi
     *
     * @param encoding Pengodean
     * @throws IOException
     */
    public PrinterLabelUtil(OutputStream outputStream, String encoding) throws IOException {
        mWriter = new OutputStreamWriter(outputStream, encoding);
        mOutputStream = outputStream;
    }

    public void print(byte[] bs) throws IOException {
        mOutputStream.write(Formatter.leftAlign());
        mOutputStream.write(bs);
    }

    /**
     * Ganti baris
     *
     * @return length
     * @throws IOException
     */
    public void printLine(int lineNum) throws IOException {
        for (int i = 0; i < lineNum; i++) {
            mWriter.write("\n");
        }
        mWriter.flush();
    }

    /**
     * ganti baris 1
     *
     * @throws IOException
     */
    public void printLine() throws IOException {
        printLine(1);
    }

    /**
     * Set lokasi
     *
     * @return
     * @throws IOException
     */
    public byte[] setLocation(int offset) throws IOException {
        byte[] bs = new byte[4];
        bs[0] = 0x1B;
        bs[1] = 0x24;
        bs[2] = (byte) (offset % 256);
        bs[3] = (byte) (offset / 256);
        return bs;
    }

    public byte[] getGbk(String stText) throws IOException {
        byte[] returnText = stText.getBytes("GBK"); // 必须放在try内才可以
        return returnText;
    }

    private int getStringPixLength(String str) {
        int pixLength = 0;
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            pixLength += 12;
        }
        return pixLength;
    }

    public int getOffset(String str) {
        return WIDTH_PIXEL - getStringPixLength(str);
    }

    /**
     * Print text
     *
     * @param text
     * @throws IOException
     */
    public void printText(String text) throws IOException {
        mWriter.write(text);
        mWriter.flush();
    }

    public void writeWithFormat(byte[] buffer, final byte[] pFormat, final byte[] pAlignment) {
        try {
            mOutputStream.write(pAlignment);
            mOutputStream.write(pFormat);
            // Write the actual data:
            mOutputStream.write(buffer, 0, buffer.length);
            mOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Notify printer it should be printed in the given format:

    }

    public void writeWithFormat(byte[] buffer, final byte[] pAlignment) {
        try {
            mOutputStream.write(pAlignment);
            mOutputStream.write(buffer);
            mOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Notify printer it should be printed in the given format:

    }

    /**
     * Alignment 0: Left-aligned, 1: Centered, 2: Right-aligned
     */
    public void printAlignment(int alignment) throws IOException {
        mWriter.write(0x1b);
        mWriter.write(0x61);
        mWriter.write(alignment);
    }

    public void printTwoColumn(String title, String content) throws IOException {
        int iNum = 0;
        byte[] byteBuffer = new byte[100];
        byte[] tmp;

        tmp = getGbk(title);
        System.arraycopy(tmp, 0, byteBuffer, iNum, tmp.length);
        iNum += tmp.length;

        tmp = setLocation(getOffset(content));
        System.arraycopy(tmp, 0, byteBuffer, iNum, tmp.length);
        iNum += tmp.length;

        tmp = getGbk(content);
        System.arraycopy(tmp, 0, byteBuffer, iNum, tmp.length);

        print(byteBuffer);
    }

    public void printDashLine() throws IOException {
        String line = "-----------------------------------------";
        writeWithFormat(line.getBytes(),Formatter.centerAlign());
    }

    public static void printTest(BluetoothSocket bluetoothSocket, Drawable drawable) {
        try {
            PrinterLabelUtil pUtil = new PrinterLabelUtil(bluetoothSocket.getOutputStream(), "GBK");
            pUtil.writeWithFormat("\n".getBytes(),Formatter.centerAlign());
            pUtil.writeWithFormat("\n".getBytes(),Formatter.centerAlign());

            pUtil.writeWithFormat(getLogo(drawable),Formatter.centerAlign());

            pUtil.writeWithFormat("\n".getBytes(),Formatter.centerAlign());
            pUtil.writeWithFormat("\n".getBytes(),Formatter.centerAlign());

            pUtil.printLine();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public static void print(BluetoothSocket bluetoothSocket, DetailLabel data, Store store, boolean isPremium) {
        try {
            PrinterLabelUtil pUtil = new PrinterLabelUtil(bluetoothSocket.getOutputStream(), "GBK");
            printItem(pUtil,data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printItem(PrinterLabelUtil pUtil, DetailLabel data) throws IOException {
        List<DetailLabel.Data> items = data.getData();
        if(items == null){
            return;
        }
        if(items.size() == 0){
            return;
        }


        for(DetailLabel.Data model : items){
            try {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                BitMatrix bitMatrix = multiFormatWriter.encode(model.getCodeproduct(), BarcodeFormat.CODE_128,250,150);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                Drawable drawable = new BitmapDrawable(bitmap);
                pUtil.writeWithFormat(model.getName_product().getBytes(),new Formatter().medium().get(),Formatter.centerAlign());
                pUtil.printLine();
                pUtil.writeWithFormat(model.getCodeproduct().getBytes(),new Formatter().small().get(),Formatter.centerAlign());
                pUtil.printLine();
                pUtil.writeWithFormat(getLogo(drawable),Formatter.centerAlign());
                pUtil.printLine();
                pUtil.writeWithFormat(model.getPrice().getBytes(),new Formatter().big().get(),Formatter.centerAlign());
                pUtil.printLine();
                pUtil.writeWithFormat("".getBytes(),new Formatter().small().get(),Formatter.centerAlign());
                String line = "----------------------------------";
                pUtil.writeWithFormat(line.getBytes(),Formatter.centerAlign());
                pUtil.printLine();
            }
            catch (IOException | WriterException e) {
                e.printStackTrace();
            }
        }

        pUtil.printLine();
        pUtil.printDashLine();


    }

    private static byte[] getLogo(Drawable drawable){
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        Bitmap resize = Bitmap.createScaledBitmap(bitmap, 378, 109, false);
        PrintPic printPic = PrintPic.getInstance();
        printPic.init(resize);
        return printPic.printDraw();
    }

    public static String convertToCurrency(int value, String format) {
        double currentValue;
        try {
            currentValue = (double) value;
        } catch (NumberFormatException nfe) {
            currentValue = 0.0;
        }
        DecimalFormat formatter = new DecimalFormat(format);
        return formatter.format(currentValue).replace(",", ",");
    }

    public static String convertToCurrency(String value) {
        double currentValue;
        try {
            currentValue = Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            currentValue = 0.0;
        }
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(currentValue).replace(",", ",");
    }


    public static String getDateFormat(String tanggal, String formatFrom, String formatTo) throws ParseException {
        Locale locale = new Locale("in","IN");
        SimpleDateFormat sdfBefore = new SimpleDateFormat(formatFrom, locale);
        Date dateBefore = sdfBefore.parse(tanggal);

        SimpleDateFormat sdfAfter = new SimpleDateFormat(formatTo, locale);
//        SimpleDateFormat sdfAfter = new SimpleDateFormat(formatTo, Locale.getDefault());
        if(dateBefore == null){
            return "";
        }
        return sdfAfter.format(dateBefore);
    }
}
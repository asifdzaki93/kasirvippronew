package id.kasirvippro.android.utils.print;

/**
 * Created by Ahmed on 08/03/18.
 */

import android.bluetooth.BluetoothSocket;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import id.kasirvippro.android.models.store.Store;
import id.kasirvippro.android.models.report.ReportDaily;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class PrinterUtilDaily {

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
    public PrinterUtilDaily(OutputStream outputStream, String encoding) throws IOException {
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
            PrinterUtil pUtil = new PrinterUtil(bluetoothSocket.getOutputStream(), "GBK");
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

    public static void print(BluetoothSocket bluetoothSocket, ReportDaily data, Store store, boolean isPremium) {
        try {
            PrinterUtil pUtil = new PrinterUtil(bluetoothSocket.getOutputStream(), "GBK");
            printHeader(pUtil,data,store);
            printItem(pUtil,data);
            printInfo(pUtil,data);
            if(!isPremium){
                printFooter(pUtil);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void printHeader(PrinterUtil pUtil, ReportDaily data, Store store) {
        try {
            String newLine = "\n";
            String tanggal = "Date:";
            String toko = "Toko:";

            ReportDaily.Info info = data.getInfo();

            pUtil.writeWithFormat(newLine.getBytes(),new Formatter().medium().get(),Formatter.centerAlign());
            pUtil.printLine();
            pUtil.writeWithFormat("DAILY REPORT".getBytes(),Formatter.centerAlign());
            pUtil.printLine();

            String tanggalValue = info.getDate();
            if(tanggalValue != null && tanggalValue.length() > 0){
                try {
                    tanggalValue = getDateFormat(tanggalValue,"yyyy-MM-dd","dd MMMM yyyy");
                    if(tanggalValue.length()+tanggal.length() > MAX_CHAR){
                        pUtil.printLine();
                        pUtil.writeWithFormat(tanggal.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                        pUtil.printLine();
                        pUtil.writeWithFormat(tanggalValue.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    }
                    else {
                        pUtil.printLine();
                        String text = tanggal+getWhiteSpace(MAX_CHAR - tanggal.length() - tanggalValue.length()) + tanggalValue;
                        pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            String methodValue = info.getName_store();
            if(methodValue != null){
                if(methodValue.length()+toko.length() > MAX_CHAR){
                    pUtil.printLine();
                    pUtil.writeWithFormat(toko.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(methodValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = toko+getWhiteSpace(MAX_CHAR - toko.length() - methodValue.length()) + methodValue;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }
            }



            pUtil.printLine();
            pUtil.printDashLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printInfo(PrinterUtil pUtil, ReportDaily data) {
        try {
            ReportDaily.Info info = data.getInfo();
            String total = "Total:";

            String totalValue = ""+ convertToCurrency(info.getTotal());

            if(totalValue.length()+total.length() > 20){
                pUtil.printLine();
                pUtil.writeWithFormat(total.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                pUtil.printLine();
                pUtil.writeWithFormat(totalValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
            }
            else {
                pUtil.printLine();
                int size = MAX_CHAR - total.length() - totalValue.length();
                String text = total+getWhiteSpace(size) + totalValue;
                pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
            }

            pUtil.printLine();
            pUtil.printDashLine();
            pUtil.printLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFooter(PrinterUtil pUtil) {
        try {
            String info = "";

            pUtil.printLine(2);
            pUtil.writeWithFormat(info.getBytes(),new Formatter().small().get(),Formatter.centerAlign());
            pUtil.printLine(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printItem(PrinterUtil pUtil, ReportDaily data) throws IOException {
        List<ReportDaily.Daily> items = data.getDaily_report();
        if(items == null){
            return;
        }
        if(items.size() == 0){
            return;
        }

        for(ReportDaily.Daily model : items){
            try {
                String operator = "Operator";
                String sales_cash = "Sales Cash";
                String sales_debt = "Sales Debt:";
                String sales_credit_card = "Sales Credit Card:";
                String sales_debit_card = "Sales Debit Card:";
                String gopay = "Gopay:";
                String ovo = "OVO:";
                String dana = "DANA:";
                String sub_total = "Sub Total:";

                String operatorValue = model.getOperator();
                assert operatorValue != null;
                if(operatorValue.length()+operator.length() > MAX_CHAR){
                        pUtil.printLine();
                        pUtil.writeWithFormat(operator.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                        pUtil.printLine();
                        pUtil.writeWithFormat(operatorValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                        pUtil.printDashLine();
                    }
                    else {
                        pUtil.printLine();
                        String text = operator+getWhiteSpace(MAX_CHAR - operator.length() - operatorValue.length()) + operatorValue;
                        pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                        pUtil.printDashLine();
                    }


                String cash = ""+convertToCurrency(model.getSales_cash());
                if(cash.length()+sales_cash.length() > MAX_CHAR){
                    pUtil.printLine();
                    pUtil.writeWithFormat(sales_cash.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(cash.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = sales_cash+getWhiteSpace(MAX_CHAR - sales_cash.length() - cash.length()) + cash;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }

                String debt = ""+convertToCurrency(model.getSales_debt());
                if(debt.length()+sales_debt.length() > MAX_CHAR){
                    pUtil.printLine();
                    pUtil.writeWithFormat(sales_debt.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(debt.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = sales_debt+getWhiteSpace(MAX_CHAR - sales_debt.length() - debt.length()) + debt;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }

                String cc = ""+convertToCurrency(model.getSales_credit_card());
                if(cc.length()+sales_credit_card.length() > MAX_CHAR){
                    pUtil.printLine();
                    pUtil.writeWithFormat(sales_credit_card.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(cc.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = sales_credit_card+getWhiteSpace(MAX_CHAR - sales_credit_card.length() - cc.length()) + cc;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }

                String db = ""+convertToCurrency(model.getSales_debit_card());
                if(db.length()+sales_debit_card.length() > MAX_CHAR){
                    pUtil.printLine();
                    pUtil.writeWithFormat(sales_debit_card.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(db.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = sales_debit_card+getWhiteSpace(MAX_CHAR - sales_debit_card.length() - db.length()) + db;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }

                String gopayValue = ""+convertToCurrency(model.getBank());
                if(gopayValue.length()+gopay.length() > MAX_CHAR){
                    pUtil.printLine();
                    pUtil.writeWithFormat(gopay.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(gopayValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = gopay+getWhiteSpace(MAX_CHAR - gopay.length() - gopayValue.length()) + gopayValue;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }

                String ovoValue = ""+convertToCurrency(model.getPos());
                if(ovoValue.length()+ovo.length() > MAX_CHAR){
                    pUtil.printLine();
                    pUtil.writeWithFormat(ovo.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(ovoValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = ovo+getWhiteSpace(MAX_CHAR - ovo.length() - ovoValue.length()) + ovoValue;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }

                String danaValue = ""+convertToCurrency(model.getDana());
                if(danaValue.length()+dana.length() > MAX_CHAR){
                    pUtil.printLine();
                    pUtil.writeWithFormat(dana.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(danaValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = dana+getWhiteSpace(MAX_CHAR - dana.length() - danaValue.length()) + danaValue;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }


                String subtotalValue = ""+convertToCurrency(model.getSub_total());
                if(subtotalValue.length()+sub_total.length() > MAX_CHAR){
                    pUtil.printLine();
                    pUtil.writeWithFormat(sub_total.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(subtotalValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                    pUtil.printDashLine();
                }
                else {
                    pUtil.printLine();
                    String text = sub_total+getWhiteSpace(MAX_CHAR - sub_total.length() - subtotalValue.length()) + subtotalValue;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printDashLine();
                }


            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        pUtil.printLine();
        pUtil.printDashLine();


    }

    private static String getWhiteSpace(int size) {
        StringBuilder builder = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            builder.append(' ');
        }
        return builder.toString();
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
        return formatter.format(currentValue).replace(",", ".");
    }

    public static String convertToCurrency(String value) {
        double currentValue;
        try {
            currentValue = Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            currentValue = 0.0;
        }
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(currentValue).replace(",", ".");
    }

    private static Store getEmptyStore(ReportDaily.Info info) {
        Store store = new Store();
        store.setName_store("Store Name");
        if(info == null){
            return store;
        }
        if(info.getName_store() != null){
            store.setName_store(info.getName_store());
        }
        return store;
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
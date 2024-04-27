package id.kasirvippro.android.utils.print;

/**
 * Created by Ahmed on 08/03/18.
 */

import android.bluetooth.BluetoothSocket;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import id.kasirvippro.android.models.store.Store;
import id.kasirvippro.android.models.transaction.DetailTransaction;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class PrinterUtilReturn {

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
    public PrinterUtilReturn(OutputStream outputStream, String encoding) throws IOException {
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
        String line = "--------------------------------------------";
        writeWithFormat(line.getBytes(),Formatter.centerAlign());
    }

    public static void printTest(BluetoothSocket bluetoothSocket, Drawable drawable) {
        try {
            PrinterUtilReturn pUtil = new PrinterUtilReturn(bluetoothSocket.getOutputStream(), "GBK");
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

    public static void print(BluetoothSocket bluetoothSocket, DetailTransaction data, Store store, boolean isPremium) {
        try {
            PrinterUtilReturn pUtil = new PrinterUtilReturn(bluetoothSocket.getOutputStream(), "GBK");
            printHeader(pUtil,data,store);
            printItem(pUtil,data);
            printSub(pUtil,data);
            printInfo(pUtil,data);
            printFooter(pUtil,data);
            printThanks(pUtil);
            if(!isPremium){
                     printPowered(pUtil);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void printHeader(PrinterUtilReturn pUtil, DetailTransaction data, Store store) {
        try {
            String newLine = "\n";
            String no = "Invoice No:";
            String tanggal = "Date:";
            String method = "Payment method:";
            String customer = "Customer:";
            String supplier = "Supplier:";
            String operator = "Operator:";
            String status = "Status:";
            String jatuhTempo = "Due date:";

            DetailTransaction.Struk struk = data.getStruk();
            Store toko = getEmptyStore(struk);
            if(store != null){
                if(store.getName_store() != null){
                    toko = store;
                }
            }

            pUtil.writeWithFormat(newLine.getBytes(),new Formatter().medium().get(),Formatter.centerAlign());
            pUtil.printLine();
            pUtil.writeWithFormat(toko.getName_store().getBytes(),Formatter.centerAlign());
            pUtil.printLine();
            if(toko.getAddress() != null && toko.getAddress().length() > 0){
                pUtil.writeWithFormat(toko.getAddress().getBytes(),new Formatter().small().get(),Formatter.centerAlign());
                pUtil.printLine();
            }
            if(toko.getNohp() != null && toko.getNohp().length() > 0){
                pUtil.writeWithFormat(toko.getNohp().getBytes(),new Formatter().small().get(),Formatter.centerAlign());
                pUtil.printLine();
            }

            String invoice = struk.getNo_invoice();
            if( invoice != null){
                if(invoice.length()+no.length() > struk.getPaper()){
                    pUtil.printLine();
                    pUtil.writeWithFormat(no.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(invoice.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }
                else {
                    pUtil.printLine();
                    String text = no+getWhiteSpace(struk.getPaper() - no.length() - invoice.length()) +invoice;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }

            }

            String tanggalValue = struk.getDate();
            if(tanggalValue != null && tanggalValue.length() > 0){
                try {
                    tanggalValue = getDateFormat(tanggalValue,"yyyy-MM-dd hh:mm","dd MMMM yyyy hh:mm");
                    if(tanggalValue.length()+tanggal.length() > struk.getPaper()){
                        pUtil.printLine();
                        pUtil.writeWithFormat(tanggal.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                        pUtil.printLine();
                        pUtil.writeWithFormat(tanggalValue.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    }
                    else {
                        pUtil.printLine();
                        String text = tanggal+getWhiteSpace(struk.getPaper() - tanggal.length() - tanggalValue.length()) + tanggalValue;
                        pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            String methodValue = struk.getPayment();
            if(methodValue != null){
                if(methodValue.length()+method.length() > struk.getPaper()){
                    pUtil.printLine();
                    pUtil.writeWithFormat(method.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(methodValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = method+getWhiteSpace(struk.getPaper() - method.length() - methodValue.length()) + methodValue;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }
            }

            String operatorValue = struk.getOperator();
            if(operatorValue != null && operatorValue.length() > 0){
                if(operatorValue.length()+operator.length() > struk.getPaper()){
                    pUtil.printLine();
                    pUtil.writeWithFormat(operator.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(operatorValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = operator+getWhiteSpace(struk.getPaper() - customer.length() - operatorValue.length()) + operatorValue;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }
            }
//            else {
//                operatorValue = "-";
//                pUtil.printLine();
//                String text = operator+getWhiteSpace(MAX_CHAR - operator.length() - operatorValue.length()) + operatorValue;
//                pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
//            }

            String customerValue = struk.getName_customer();
            if(customerValue != null && customerValue.length() > 0){
                if(customerValue.length()+customer.length() > struk.getPaper()){
                    pUtil.printLine();
                    pUtil.writeWithFormat(customer.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(customerValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = customer+getWhiteSpace(struk.getPaper() - customer.length() - customerValue.length()) + customerValue;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }
            }
//            else {
//                customerValue = "-";
//                pUtil.printLine();
//                String text = customer+getWhiteSpace(MAX_CHAR - customer.length() - customerValue.length()) + customerValue;
//                pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
//            }

            String supplierValue = struk.getName_supplier();
            if(supplierValue != null && supplierValue.length() > 0){
                if(supplierValue.length()+supplier.length() > struk.getPaper()){
                    pUtil.printLine();
                    pUtil.writeWithFormat(supplier.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(supplierValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = supplier+getWhiteSpace(struk.getPaper() - supplier.length() - supplierValue.length()) + supplierValue;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }
            }
//            else {
//                supplierValue = "-";
//                pUtil.printLine();
//                String text = supplier+getWhiteSpace(MAX_CHAR - supplier.length() - supplierValue.length()) + supplierValue;
//                pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
//            }

            String statusValue = struk.getStatus();
            if(statusValue != null && statusValue.length() > 0){
                if(statusValue.length()+statusValue.length() > struk.getPaper()){
                    pUtil.printLine();
                    pUtil.writeWithFormat(status.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    pUtil.printLine();
                    pUtil.writeWithFormat(statusValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                }
                else {
                    pUtil.printLine();
                    String text = status+getWhiteSpace(struk.getPaper() - status.length() - statusValue.length()) + statusValue;
                    pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                }
            }
            else {
                statusValue = "-";
                pUtil.printLine();
                String text = status+getWhiteSpace(struk.getPaper() - status.length() - statusValue.length()) + statusValue;
                pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
            }

            String jatuhTempoValue = struk.getDue_date();
            if(jatuhTempoValue != null && jatuhTempoValue.length() > 0 && !jatuhTempoValue.equals("0000-00-00")){
                try {
                    jatuhTempoValue = getDateFormat(jatuhTempoValue,"yyyy-MM-dd","dd MMMM yyyy");
                    if(jatuhTempoValue.length()+jatuhTempo.length() > struk.getPaper()){
                        pUtil.printLine();
                        pUtil.writeWithFormat(jatuhTempo.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                        pUtil.printLine();
                        pUtil.writeWithFormat(jatuhTempoValue.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    }
                    else {
                        pUtil.printLine();
                        String text = jatuhTempo+getWhiteSpace(struk.getPaper() - jatuhTempo.length() - jatuhTempoValue.length()) + jatuhTempoValue;
                        pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


            pUtil.printLine();
            //pUtil.printDashLine();
            if (struk.getPaper() == 42){
                String line = "--------------------------------";
                pUtil.writeWithFormat(line.getBytes(),Formatter.centerAlign());
            }else{
                String line = "------------------------------------------------";
                pUtil.writeWithFormat(line.getBytes(),Formatter.centerAlign());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printInfo(PrinterUtilReturn pUtil, DetailTransaction data) {
        try {
            DetailTransaction.Struk struk = data.getStruk();
            String tax = "Tax/Vat:";
            String total = "Total Return:";
            String bayar = "Paid:";
            String kembalian = "Change";

            assert struk != null;
            String taxValue = struk.getTax();
            if(taxValue.length()+tax.length() > 20){
                pUtil.printLine();
                pUtil.writeWithFormat(tax.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                pUtil.printLine();
                pUtil.writeWithFormat(taxValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
            }
            else {
                pUtil.printLine();
                int size = struk.getPaper() - tax.length() - taxValue.length();
                String text = tax+getWhiteSpace(size) + taxValue;
                pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
            }




            String totalValue = struk.getTotallast();

            if(totalValue.length()+total.length() > 20){
                pUtil.printLine();
                pUtil.writeWithFormat(total.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                pUtil.printLine();
                pUtil.writeWithFormat(totalValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
            }
            else {
                pUtil.printLine();
                int size = struk.getPaper() - total.length() - totalValue.length();
                String text = total+getWhiteSpace(size) + totalValue;
                pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
            }

            if(struk.getTotalpay() != null){
                double bayarDouble = Double.parseDouble(struk.getTotalpay());

                if(bayarDouble > 0){
                    String bayarValue = struk.getTotalpay();
                    if(bayarValue.length()+bayar.length() > 20){
                        pUtil.printLine();
                        pUtil.writeWithFormat(bayar.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                        pUtil.printLine();
                        pUtil.writeWithFormat(bayarValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                    }
                    else {
                        pUtil.printLine();
                        int size = struk.getPaper() - bayar.length() - bayarValue.length();
                        String text = bayar+getWhiteSpace(size) + bayarValue;
                        pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    }
                }
            }

            if(struk.getChangepay() != null){
                double kembalianDouble = Double.parseDouble(struk.getChangepay());
                if(kembalianDouble > 0){
                    String kembalianValue = struk.getChangepay();
                    if(kembalianValue.length()+kembalian.length() > 20){
                        pUtil.printLine();
                        pUtil.writeWithFormat(kembalian.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                        pUtil.printLine();
                        pUtil.writeWithFormat(kembalianValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
                    }
                    else {
                        pUtil.printLine();
                        int size = struk.getPaper() - kembalian.length() - kembalianValue.length();
                        String text = kembalian+getWhiteSpace(size) + kembalianValue;
                        pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                    }
                }
            }



            pUtil.printLine();
            //pUtil.printDashLine();
            if (struk.getPaper() == 42){
                String line = "--------------------------------";
                pUtil.writeWithFormat(line.getBytes(),Formatter.centerAlign());
            }else{
                String line = "------------------------------------------------";
                pUtil.writeWithFormat(line.getBytes(),Formatter.centerAlign());
            }
            pUtil.printLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printSub(PrinterUtilReturn pUtil, DetailTransaction data) {
        try {
            DetailTransaction.Struk struk = data.getStruk();
            String subtotal = "Sub Total:";

            assert struk != null;
            String subtotalValue = struk.getTotalorder();
            if(subtotalValue.length()+subtotal.length() > 20){
                pUtil.printLine();
                pUtil.writeWithFormat(subtotal.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
                pUtil.printLine();
                pUtil.writeWithFormat(subtotalValue.getBytes(),new Formatter().small().get(),Formatter.rightAlign());
            }
            else {
                pUtil.printLine();
                int size = struk.getPaper() - subtotal.length() - subtotalValue.length();
                String text = subtotal+getWhiteSpace(size) + subtotalValue;
                pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
            }





            pUtil.printLine();
            //pUtil.printDashLine();
            if (struk.getPaper() == 42){
                String line = "--------------------------------";
                pUtil.writeWithFormat(line.getBytes(),Formatter.centerAlign());
            }else{
                String line = "------------------------------------------------";
                pUtil.writeWithFormat(line.getBytes(),Formatter.centerAlign());
            }
            pUtil.printLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void printFooter(PrinterUtilReturn pUtil, DetailTransaction data) {
        try {
            DetailTransaction.Struk struk = data.getStruk();
            assert struk != null;
            String footer = struk.getFooter();

            pUtil.printLine(1);
            pUtil.writeWithFormat(footer.getBytes(),new Formatter().small().get(),Formatter.leftAlign());
            pUtil.printLine(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printThanks(PrinterUtilReturn pUtil) {
        try {
            String thanks = "Thank-you for shopping with us!";

            pUtil.printLine(3);
            pUtil.writeWithFormat(thanks.getBytes(),new Formatter().small().get(),Formatter.centerAlign());
            pUtil.printLine(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printPowered(PrinterUtilReturn pUtil) {
        try {
            String power = "Powered By Kasir VIP";

            pUtil.printLine(1);
            pUtil.writeWithFormat(power.getBytes(),new Formatter().small().get(),Formatter.centerAlign());
            pUtil.printLine(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printItem(PrinterUtilReturn pUtil, DetailTransaction data) throws IOException {
        List<DetailTransaction.Data> items = data.getData();
        DetailTransaction.Struk struk = data.getStruk();
        if(items == null){
            return;
        }
        if(items.size() == 0){
            return;
        }

        for(DetailTransaction.Data model : items){
            try {
                pUtil.printLine();
                pUtil.printAlignment(0);
                pUtil.printText(model.getName_product());
                pUtil.printLine();

                String name = model.getAmount()+"x"+"@"+model.getPrice();
                String value = model.getTotalprice();
                int size = struk.getPaper() - name.length() - value.length();
                String text = name+getWhiteSpace(size) + value;
                pUtil.writeWithFormat(text.getBytes(),new Formatter().small().get(),Formatter.leftAlign());

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        pUtil.printLine();
        //pUtil.printDashLine();
        if (struk.getPaper() == 42){
            String line = "--------------------------------";
            pUtil.writeWithFormat(line.getBytes(),Formatter.centerAlign());
        }else{
            String line = "------------------------------------------------";
            pUtil.writeWithFormat(line.getBytes(),Formatter.centerAlign());
        }


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

    private static Store getEmptyStore(DetailTransaction.Struk struk) {
        Store store = new Store();
        store.setName_store("Store Name");
        store.setAddress("Store Address");
        store.setNohp("Store Phone Number");
        if(struk == null){
            return store;
        }
        if(struk.getName_store() != null){
            store.setName_store(struk.getName_store());
        }
        if(struk.getAddress() != null){
            store.setAddress(struk.getAddress());
        }
        if(struk.getNohp() != null){
            store.setNohp(struk.getNohp());
        }
        return store;
    }

    public static String getDateFormat(String tanggal, String formatFrom, String formatTo) throws ParseException {
        Locale locale = new Locale("en","EN");
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


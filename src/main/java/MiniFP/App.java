package MiniFP;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created By Kukhrarskiy
 */
public class App {
    public static void main(String[] args) {

        System.out.println("ПРОБА РАБОТЫ С ФИСКАЛЬНЫМ РЕГИСТРАТОРОМ");

        //========JACOB
        //Loading the library:
        ActiveXComponent comp = new ActiveXComponent("ecrmini.T400");
        Object erc_object = comp.getObject();
        System.out.println("The Library been loaded, and an activeX component been created");

        /*
        Variant g1 =Dispatch.call((Dispatch) erc_object, "get_last_error");
        Variant g2 = Dispatch.call((Dispatch) erc_object, "get_last_event");
        Variant g3 = Dispatch.call((Dispatch) erc_object, "get_error_info");
        Variant g4 = Dispatch.call((Dispatch) erc_object, "get_last_result");
        */
        Dispatch.call((Dispatch) erc_object, "t400me", "set_error_log;0;");
        System.out.println("Отключаем лог ошибок - " + Dispatch.call((Dispatch) erc_object, "get_error_info"));
        Dispatch.call((Dispatch) erc_object, "t400me", "open_port;7;115200");
        System.out.println("Модель ФП - " + Dispatch.call((Dispatch) erc_object, "get_last_result"));
        System.out.println("Открываем порт - " + Dispatch.call((Dispatch) erc_object, "get_error_info"));
        Dispatch.call((Dispatch) erc_object, "t400me", "cashier_registration;1;0;");
        System.out.println("Регистрация кассира - " + Dispatch.call((Dispatch) erc_object, "get_error_info"));
        Dispatch.call((Dispatch) erc_object, "t400me", "print_empty_receipt;");
        System.out.println("Печать пустого чека - " + Dispatch.call((Dispatch) erc_object, "get_error_info"));

        /*Dispatch.call((Dispatch) erc_object, "t400me", "get_ksef;0;1;1;1;0;2;1;33;");
        System.out.println("Статус КСЕФ - " + Dispatch.call((Dispatch) erc_object, "get_error_info"));
        System.out.println("Статус КСЕФ - " + Dispatch.call((Dispatch) erc_object, "get_last_error"));
        System.out.println("Статус КСЕФ - " + Dispatch.call((Dispatch) erc_object, "get_last_event"));
        System.out.println("Статус КСЕФ - " + Dispatch.call((Dispatch) erc_object, "get_last_result"));

        Dispatch.call((Dispatch) erc_object, "t400me", "get_report;0;");
        System.out.println("Статус КСЕФ - " + Dispatch.call((Dispatch) erc_object, "get_error_info"));
        System.out.println("Статус КСЕФ - " + Dispatch.call((Dispatch) erc_object, "get_last_error"));
        System.out.println("Статус КСЕФ - " + Dispatch.call((Dispatch) erc_object, "get_last_event"));
        System.out.println("Статус КСЕФ - " + Dispatch.call((Dispatch) erc_object, "get_last_result"));
        */

        Dispatch.call((Dispatch) erc_object, "t400me", "close_port");
        System.out.println("Закрываем порт - " + Dispatch.call((Dispatch) erc_object, "get_error_info"));


        //==== разбираем файл отчетов x3.bin
        System.out.println("====разбор х3.bin======");
        ArrayList<X3Result> x3ResultArrayList = new ArrayList<X3Result>();
        String filename = "D:\\MINI_FP54\\x3.bin";
        File file = new File(filename);
        byte[] bFile = new byte[(int) file.length()];
        Path path = Paths.get(filename);
        try {
            bFile = Files.readAllBytes(path);   //читаем все байты в массив байт
            //System.out.println(Integer.reverseBytes(bFile[4]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done reading file");
        int i = 0;
        for (int j = 1; j < bFile.length; j += 124) {
            X3Result tmp = new X3Result();

            byte[] temp1 = new byte[4];
            System.arraycopy(bFile, j, temp1, 0, 4);
            tmp.setCode(ByteBuffer.wrap(temp1).order(ByteOrder.LITTLE_ENDIAN).getInt()); //getting wrapped int from 4 byte in reverse byte order

            byte[] temp2 = new byte[4];
            System.arraycopy(bFile, j + 4, temp2, 0, 4);    //делим на 100 ибо в копейках
            tmp.setPrice(BigDecimal.valueOf(ByteBuffer.wrap(temp2).order(ByteOrder.LITTLE_ENDIAN).getInt()).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_CEILING)); //getting wrapped BigDecimal from 4 byte in reverse byte order

            byte[] temp3 = new byte[48];
            System.arraycopy(bFile, j + 4 + 4, temp3, 0, 48);
            try {
                tmp.setName(new String(temp3, "CP1251").replaceAll("\u0000.*", ""));  //getting clear string from bytes array
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            byte[] temp4 = new byte[4];
            System.arraycopy(bFile, j + 4 + 4 + 48 + 8 + 4, temp4, 0, 4);       //делим на 1000 ибо в граммах
            tmp.setQty_in(BigDecimal.valueOf(ByteBuffer.wrap(temp4).order(ByteOrder.LITTLE_ENDIAN).getInt()).divide(new BigDecimal("1000"), 2, BigDecimal.ROUND_CEILING)); //getting wrapped BigDecimal from 4 byte in reverse byte order


            x3ResultArrayList.add(new X3Result());
            x3ResultArrayList.set(i, tmp);
            i++;

        }

        System.out.println("Расшифрованный файл Х-отчета по товарам");
        for (X3Result aX3ResultArrayList : x3ResultArrayList) {
            System.out.println(aX3ResultArrayList);
        }

    }
}

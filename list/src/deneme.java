
import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

public class deneme {

    public static void main(String[] args) throws IOException {

        List list = new List();
        dosyaOku(list);
        list.dugum_sayaci();
        // System.out.println(list.insert(list));
        System.out.println(list.listele());
        System.out.println(list.Return_Kayit(5).getKayit().tc_no + " " + list.Return_Kayit(5).getKayit().ad + " " + list.Return_Kayit(5).getKayit().soyad);
        System.out.println(list.Return_KayitSayisi() + " adet kayıt bulundu");
        System.out.println(list.Delete(5672189831L) + " inci içerik silindi.");

        long startTime = System.nanoTime();
        System.out.println(list.NodeSearch(2372617633L).getKayit().tc_no + " " + list.NodeSearch(2372617633L).getKayit().ad + " " + list.NodeSearch(2372617633L).getKayit().soyad);
        long endTime = System.nanoTime();
        long estimatedTime = endTime - startTime; // Geçen süreyi nanosaniye cinsinden elde ediyoruz
        double seconds = (double)estimatedTime/1000000000; // saniyeye çevirmek için milyar'a bölüyoruz
        System.out.println(seconds+ " sn");
    }

    static void dosyaOku(List list) throws IOException {
        FileReader fileReader = new FileReader("kayit_dosyasi.txt");            //Proje dosyası içindeki text dosyasını tanıtma

        BufferedReader bufferedReader = new BufferedReader(fileReader);         //Tampon yapı ile File işlemi için nesne oluşturma.

        for (int i = 0; i < 5000; i++) {                                        //Doyadaki ilk 5000 satırı almak için döngü.

            String satir = bufferedReader.readLine();                           //Dosyadaki satırı okuyan döngü

            list.insert(kisiOku(satir));                                        //list nesnesi kisiOku() metoduna okunan satırı gönderir ve dönen içeriği insert metoduna yollar

        }

    }

    static Node kisiOku(String satir) {

        String dizi[] = satir.split(",");                                       //split() metodu ile virgüle kadar olan kısımlar ayrılmıştır.

        long tc_no = Long.parseLong(dizi[0]);                                   //String halindeki veri long a dönüştürülmüştür

        String ad = dizi[1];                                                    //ad değişkeni ayrılmıştır

        String soyad = dizi[2];                                                 //soyad değişkeni ayrılmıştır
        Kayit kayit = new Kayit(tc_no,ad,soyad);
        Node eleman = new Node(kayit);                               //Ayrılmış içerikler Node yapısına parametre olarak gönderilmiştir.

        return eleman;
    }
}

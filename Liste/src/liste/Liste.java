/*
Öğrencilere ait no, ad, soyad, vize ve genel sınav notlarının tutulduğu tek yönlü bir bağlı liste yapısıdır.
Bu  liste  üzerinde  arama,  silme,  ekleme,  listeleme  vb.  işlemlerini gerçekleştiren metodlar tanımlanmıştır.
 */
package liste;

import java.util.Scanner;

class Ogrenci{
    int no;
    String ad,soyad;
    int vize,genel;
    Ogrenci sonraki;
    
    Ogrenci(int no,String ad,String soyad,int vize,int genel){
        this.no=no;
        this.ad=ad;
        this.soyad=soyad;
        this.vize=vize;
        this.genel=genel;
        sonraki=null;
    }
}

public class Liste {
    Ogrenci bas,son=null;
    
    public void basaEkle(int no,String ad,String soyad,int vize,int genel){
        Ogrenci ogr=new Ogrenci(no,ad,soyad,vize,genel);
     if(son==null){
         bas=ogr;
         son=ogr;
          }
     else{
         ogr.sonraki=bas;
         bas=ogr;
     }   
    }
    public void sonaEkle(int no,String ad,String soyad,int vize,int genel){
        Ogrenci ogr=new Ogrenci(no,ad,soyad,vize,genel);
        if(bas==null){
            bas=ogr;
            son=ogr;
        }
        else{
            son.sonraki=ogr;
            son=ogr;
        }
    }
    public void arayaEkle(int no,String ad,String soyad,int vize,int genel,int sira){
         Ogrenci ogr=new Ogrenci(no,ad,soyad,vize,genel);
         int ogr_sayisi=OgrenciSayisi();
         int j=1;
         Ogrenci gecici=bas;
         if(sira<=ogr_sayisi){
             while(gecici!=null){
             if(j==sira){
                 ogr.sonraki=gecici.sonraki;
                 gecici.sonraki=ogr;
             }
             gecici=gecici.sonraki;
             j++;   
         }
         }else{
             System.out.println("Araya ekleme yapılamadı..");
         }
    }
    public int OgrenciSayisi(){
        int ogr_sayisi=0;
        Ogrenci gecici=bas;
        if(gecici!=null){
            gecici=gecici.sonraki;
            ogr_sayisi++;
        }
        return ogr_sayisi;
    }
    public void listele(){
        Ogrenci gecici=bas;
        while(gecici!=null){
            System.out.println("Öğrenci No:"+gecici.no+" Öğrenci Ad:"+gecici.ad+" Öğrenci soyad:"+gecici.soyad+" Öğrenci vize:"+gecici.vize+" Öğrenci genel:"+gecici.genel);
           gecici=gecici.sonraki; 
        }
    }
    public void bastanSil(){
    bas=bas.sonraki;
    if(bas==null)
        son=null;
    }
    public void sondanSil(){
        Ogrenci gecici,once;
        gecici=bas;
        once=null;
        while(gecici!=son){
            once=gecici;
            gecici=gecici.sonraki;
             }
        if(once==null){
            bas=null;
            son=null;
        }
        else{
            once.sonraki=null;
            son=once;
        }
    }
    public void NumaradanSil(int no){
        Ogrenci once,gecici;
        once=null;
        gecici=bas;
        while(gecici.no!=no){
            once=gecici;
            gecici=gecici.sonraki;
        }
        once.sonraki=gecici.sonraki;
    }
    public void OgrenciBilgileri(int no){
        Ogrenci gecici=bas;
        while(gecici!=null){
            if(gecici.no==no){
                System.out.println("Öğrenci No:"+gecici.no+" Öğrenci Ad:"+gecici.ad+" Öğrenci soyad:"+gecici.soyad+" Öğrenci vize:"+gecici.vize+" Öğrenci genel:"+gecici.genel);
                break;
            }
            else{
                gecici=gecici.sonraki;
            }
        }
    }
    public void TekCift(){
        Liste tek=new Liste();
        Liste cift=new Liste();
        Ogrenci gecici=bas;
        while(gecici!=null){
            if(gecici.no%2==0){
                tek.sonaEkle(gecici.no,gecici.ad,gecici.soyad,gecici.vize,gecici.genel);
            }
            else{
                cift.sonaEkle(gecici.no,gecici.ad,gecici.soyad,gecici.vize,gecici.genel); 
            }
            gecici=gecici.sonraki;
        }
        System.out.println("Tek nolu öğrencilerin listesi:");
        tek.listele();
        System.out.println("Çift nolu öğrencilerin listesi:");
        cift.listele();
    }
    public void ortalamaBul(){
        double ort=0;
        Ogrenci gecici=bas;
        while(gecici!=null){
            ort=gecici.vize*0.4+gecici.genel*0.6;
            System.out.println("Öğrenci no:"+gecici.no+" Ortalama Notu:"+ort);
            gecici=gecici.sonraki;
        }
    }
    public void dusukGenel(){
        int not=bas.genel;
        Ogrenci gecici=bas;
        Ogrenci tmp=bas;
        while(gecici!=null){
            if(gecici.genel<not){
                not=gecici.genel;
                tmp=gecici;
            }
            gecici=gecici.sonraki;  
        }
        System.out.println("En düşük genel notuna sahip öğrenci:");
        System.out.println("Öğrenci No:"+tmp.no+" Öğrenci Ad:"+tmp.ad+" Öğrenci soyad:"+tmp.soyad+ " Öğrenci genel:"+tmp.genel);

    }
    public static void main(String[] args) {
     int secim;
     Scanner klavye=new Scanner(System.in);
     Liste ogrenciler=new Liste();
     do{
         System.out.println("1-Öğrenciyi başa ekle.");
         System.out.println("2-Öğrenciyi sona ekle.");
         System.out.println("3-Öğrenciyi araya ekle.");
         System.out.println("4-Öğrencileri listele.");
         System.out.println("5-Baştaki öğrenciyi sil.");
         System.out.println("6-Sondaki öğrenciyi sil.");
         System.out.println("7-Numarası girilen öğrenciyi sil.");
         System.out.println("8-Numarasi girilen öğrencinin bilgilerini getir.");
         System.out.println("9-Listedeki öğrenci sayısını getir.");
         System.out.println("10-Tek ve çift nolu öğrencileri listele.");
         System.out.println("11-Öğrencilerin sınav ortalamalarını bul.");
         System.out.println("12-Genl sınav notu en düşük olan öğrenciyi getir.");
         System.out.println("0-Çıkış");
         secim=klavye.nextInt();
     
     switch (secim){
         case 1:
             System.out.println("Öğrencinin numarasını giriniz...");
             int bno=klavye.nextInt();
             System.out.println("Öğrencinin adını giriniz..");
             String bad=klavye.next();
             System.out.println("Öğrencinin soyadını giriniz..");
             String bsoyad=klavye.next();
             System.out.println("Öğrencinin vize notunu giriniz..");
             int bvize=klavye.nextInt();
             System.out.println("Öğrencinin genel notunu giriniz..");
             int bgenel=klavye.nextInt();
             ogrenciler.basaEkle(bno,bad,bsoyad,bvize,bgenel);
             break;
             
             case 2:
             System.out.println("Öğrencinin numarasını giriniz...");
             int sno=klavye.nextInt();
             System.out.println("Öğrencinin adını giriniz..");
             String sad=klavye.next();
             System.out.println("Öğrencinin soyadını giriniz..");
             String ssoyad=klavye.next();
             System.out.println("Öğrencinin vize notunu giriniz..");
             int svize=klavye.nextInt();
             System.out.println("Öğrencinin genel notunu giriniz..");
             int sgenel=klavye.nextInt();
             ogrenciler.sonaEkle(sno,sad,ssoyad,svize,sgenel);
             break;
             
             case 3:
             System.out.println("Öğrencinin numarasını giriniz...");
             int ano=klavye.nextInt();
             System.out.println("Öğrencinin adını giriniz..");
             String aad=klavye.next();
             System.out.println("Öğrencinin soyadını giriniz..");
             String asoyad=klavye.next();
             System.out.println("Öğrencinin vize notunu giriniz..");
             int avize=klavye.nextInt();
             System.out.println("Öğrencinin genel notunu giriniz..");
             int agenel=klavye.nextInt();
             System.out.println("Öğrenciyi eklemek istediğiniz sırayı giriniz..");
             int sira=klavye.nextInt();
             ogrenciler.arayaEkle(ano,aad,asoyad,avize,agenel,sira);
             break;
             
             case 4:
                 ogrenciler.listele();
                 break;
                 
             case 5:
                 ogrenciler.bastanSil();
                 break;
                 
             case 6:
                 ogrenciler.sondanSil();
                 break;
                 
             case 7:
                 System.out.println("Silmek istediğiniz öğrencinin numarasını giriniz.");
                 int sil_no=klavye.nextInt();
                 ogrenciler.NumaradanSil(sil_no);
                 break;
                 
             case 8:
                 System.out.println("Bilgilerini istediğiniz öğrencinin numarasını giriniz.");
                 int no=klavye.nextInt();
                 ogrenciler.OgrenciBilgileri(no);
                 break;
                 
             case 9:
                 int sayi=ogrenciler.OgrenciSayisi();
                 System.out.println("Listedeki öğrenci sayısı: "+sayi);
                 break;
                 
             case 10:
                 ogrenciler.TekCift();
                 break;
                 
             case 11:
                 ogrenciler.ortalamaBul();
                 break;
                 
             case 12:
                 ogrenciler.dusukGenel();
                 break;
     }
     }
     while(secim!=0);
    }
}
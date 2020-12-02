

class Kayit{
    long tc_no;
    String ad,soyad;
    public Kayit(long tc_no, String ad, String soyad) {
        this.tc_no = tc_no;
        this.ad = ad;
        this.soyad = soyad;
    }
}



class Node {

    Kayit kayit;
    Node ileri;


    int dugum_sirasi;
    int kalan_eleman_sayisi;

    public Kayit getKayit()
    {
        return kayit;
    }
    public void setData(Kayit k)
    {
        kayit = k;
    }
    public Node getLink()
    {
        return ileri;

    }
    public void setLink(Node link)
    {
        ileri = link;
    }
    public Node(Kayit yeniKayit)
    {
        kayit = yeniKayit;
        ileri = null;
    }
}
//List class yapİsİna baÛlİ liste iskeletini olußturmak iin ve sİrayİ kaybetmemek iin bas,son ve dŸÛŸmŸn sİrasİ tanİmlanmİßtİr.
public class List {

    Node bas;

    Node kuyruk;

    static int sira;

    public List() {

        bas = null;

        sira = 0;

        kuyruk = null;

    }
    //Listenin baßİna dŸÛŸm ekleyen metod..
    public void basaEkle(Node yeni) {

        //ilk baßta elaman olup olmadİÛİnİ kontrol ediyoruz
        if (kuyruk == null) {

            bas = yeni;

            kuyruk = yeni;

        } else {

            yeni.ileri = bas;

            bas = yeni;

        }

    }
    //Listenin sonuna dŸÛŸm ekleyen metod..
    public void sonaEkle(Node yeni) {

        //ilk baßta elaman olup olmadİÛİnİ kontrol ediyoruz
        if (bas == null) {

            bas = yeni;

            kuyruk = yeni;

        } else {

            kuyruk.ileri = yeni;

            kuyruk = yeni;

        }
    }
    //insert metodu ile ile baÛlİ listeye ilk if yapİsİ dosyadan ilk okunan eleman eklenmißtir.Dosyadan diÛer okunan eleman ile else if yapİsİna geiß
//yapİlmİß ve var olan ilk elemandan kŸŸk yada bŸyŸk olduÛu ßartİ kontrol edilmiß.KŸŸkse baß,bŸyŸkse kuyruk olarak tanİmlanmİßtİr.
//Alttaki else if yapİlarİ ile baß ve kuyruk dŸÛŸmŸ olußmuß listeye baßtan kŸŸk ise baß,kuyruktan bŸyŸk ise kuyruk,diÛer durumlarda araya ekleme ißlemi
//yapİlarak baÛlİ liste kŸŸkten bŸyŸÛe doÛru sİralanarak olußturulmußtur.
    void insert(Node yeni) {

        if (bas == null) {
            bas = yeni;
            kuyruk = yeni;
        } else if (bas == kuyruk) {
            if (bas.getKayit().tc_no > yeni.getKayit().tc_no) {
                yeni.ileri = bas;
                bas = yeni;
            } else {
                bas.ileri = yeni;
                kuyruk = yeni;
            }
        } else if (yeni.getKayit().tc_no < bas.getKayit().tc_no) {
            basaEkle(yeni);
        } else if (yeni.getKayit().tc_no > kuyruk.getKayit().tc_no) {
            sonaEkle(yeni);
        } else {
            Node temp = bas.ileri, onceki = bas;
            while (yeni.getKayit().tc_no > temp.getKayit().tc_no) {
                temp = temp.ileri;
                onceki = onceki.ileri;
            }
            yeni.ileri = temp;
            onceki.ileri = yeni;

        }
    }
    //Bu metod ile baÛlİ listedeki her dŸÛŸmŸn sİrasİnİ olußturup atama yapatİk ve kendisinden sonra ka dŸÛŸmŸn bulunduÛunu tuttuk.
    void dugum_sayaci(){
        int sayac=1;
        Node temp=bas;
        while(temp!=null){
            temp.dugum_sirasi=sayac;
            temp.kalan_eleman_sayisi=5000-sayac;
            temp=temp.ileri;
            sayac++;
        }
    }
    //Yeni dŸÛŸmŸ girilen tc_no dan sonraki sİraya ekleme yapan metod.
    public void arayaEkle(Node yeni, long ara) {

        //eleman yok ise
        if (bas == null && kuyruk == null) {

            basaEkle(yeni);

        } //tek eleman varsa
        else if (bas == kuyruk) {

            sonaEkle(yeni);

        } else {

            Node tmp = bas;

            while (tmp != null && tmp.getKayit().tc_no != ara) {

                tmp = tmp.ileri;

            }

            if (tmp != null) {

                yeni.ileri = tmp.ileri;

                tmp.ileri = yeni;

            }

        }

    }


    //DŸÛŸm elemanlarİnİ tek tek gezerek ieriklerini dšndŸren metod
    public String listele() {

        Node twp = bas;

        String liste = "";

        while (twp != null) {

            liste = liste + twp.getKayit().tc_no + " / " + twp.getKayit().ad + " / " + twp.getKayit().soyad + " / " + twp.dugum_sirasi + " / " + twp.kalan_eleman_sayisi + "\n";

            twp = twp.ileri;

        }

        return liste;

    }

    //girilen sİradaki dŸÛŸmŸn ieriÛini dšndŸren metod.
    public Node Return_Kayit(int sira) {
        Node kayit = bas;
        while (kayit != null) {
            if (kayit.dugum_sirasi == sira) {
                return kayit;
            }
            kayit = kayit.ileri;
        }
        return null;
    }

    //Girilen tc_no deÛerini baÛlİ listede olduÛu dŸÛŸmŸ bulup, dŸÛŸmŸn sİrasİnİ geri dšndŸren metod.
    //Girilen deÛer listede yoksa -1 dšnderir
    long Delete(long tc_no) {
        Node once, silinen;
        once = null;
        silinen = bas;
        while (silinen != null) {
            if (silinen.getKayit().tc_no != tc_no) {
                once = silinen;
                silinen = silinen.ileri;

            } else {
                once.ileri = silinen.ileri;
                return silinen.dugum_sirasi;
            }
        }
        return -1;
    }
    //Litedeki son dŸÛŸm(kuyruk) sİrasİnİ dšndŸrerek listedeki kayİt sayİsİnİ veren metod. =>o(1) zamanda alİßİr.
    int Return_KayitSayisi() {
        Node kayit = kuyruk;
        return kayit.dugum_sirasi;
    }
    //listele metodu ile aynİ ißlevi gšrŸr.
    void Ekrana_yazdir() {
        Node temp = bas;
        while (temp != null) {
            System.out.println("TcNo:" + temp.getKayit().tc_no + " Ad:" + temp.getKayit().ad + " Soyad:" + temp.getKayit().soyad);
            temp = temp.ileri;
        }
    }

    //Listedeki tc_no deÛerinin olduÛu dŸÛŸmŸ bulup dŸÛŸmŸ dšndŸren metod.EÛer aranan deÛer listede yoksa null deÛeri dšndŸrŸr.
    Node NodeSearch(long aranan){
        Node temp=bas;
        while(temp!=null){
            if(temp.getKayit().tc_no==aranan){
                return temp;
            }
            temp=temp.ileri;
        }
        return null;
    }

}

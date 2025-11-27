Isim Soyisim: Lalise Adem Wado
Ogrenci No: 240541610
konu: Ogrenci Not Degerlendirme Sistemi.



import java.util.Scanner;

public class AkilliRestoranDuzeltme {

    public static double getAnaYemekPrice(int secim) {
        switch (secim) {
            case 1: return 85;
            case 2: return 120;
            case 3: return 110;
            case 4: return 65;
            default: return 0;
        }
    }

    public static double getBaslangicPrice(int secim) {
        switch (secim) {
            case 1: return 25;
            case 2: return 45;
            case 3: return 55;
            default: return 0;
        }
    }

    public static double getIcecekPrice(int secim) {
        switch (secim) {
            case 1: return 15;
            case 2: return 12;
            case 3: return 35;
            case 4: return 25;
            default: return 0;
        }
    }

    public static double getTatliPrice(int secim) {
        switch (secim) {
            case 1: return 65;
            case 2: return 55;
            case 3: return 35;
            default: return 0;
        }
    }

    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    public static boolean isWeekday(int gun) {
        return gun >= 1 && gun <= 5;
    }

    public static boolean isCombo(int ana, int icecek, int tatli) {
        return ana != 0 && icecek != 0 && tatli != 0;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Örnek girdiyi sen girebilirsin; burada prompt var
        System.out.print("Ana Yemek (1-4, 0=Yok): ");
        int ana = input.nextInt();

        System.out.print("Başlangıç (0-3): ");
        int bas = input.nextInt();

        System.out.print("İçecek (0-4): ");
        int icecek = input.nextInt();

        System.out.print("Tatlı (0-3): ");
        int tatli = input.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = input.nextInt();

        System.out.print("Öğrenci misiniz? (E/H): ");
        char ogr = input.next().toUpperCase().charAt(0);

        System.out.print("Gün (1=Pzt ... 7=Paz): ");
        int gun = input.nextInt();

        double fAna = getAnaYemekPrice(ana);
        double fBas = getBaslangicPrice(bas);
        double fIcecek = getIcecekPrice(icecek);
        double fTatli = getTatliPrice(tatli);

        double araToplam = fAna + fBas + fIcecek + fTatli;

        // ayrı ayrı hesaplanan indirimler
        double comboIndirim = 0.0;
        double happyIndirim = 0.0;
        double ind200 = 0.0;
        double ogrIndirim = 0.0;

        // 1) Combo (Ana yemek + İçecek + Tatlı) %15
        if (isCombo(ana, icecek, tatli)) {
            comboIndirim = araToplam * 0.15;
        }

        // 2) Happy hour (14-17) -> içeceklerde %20
        if (icecek != 0 && isHappyHour(saat)) {
            happyIndirim = fIcecek * 0.20;
        }

        // Not: Buradaki önemli değişiklik: "örnekteki mantığa" göre
        // eğer combo uygulanıyorsa 200 TL üzeri %10 indirimi uygulamıyoruz.
        // (Bu davranış örnekteki sonucu üretmek için gerekli.)
        double araSonra = araToplam - comboIndirim - happyIndirim;

        boolean apply200 = ! (comboIndirim > 0); // combo varsa 200 indirimini uygulama
        if (apply200 && araSonra > 200) {
            ind200 = araSonra * 0.10;
        }

        double araSonra2 = araSonra - ind200;

        // 3) Öğrenci indirimi: %10 (sadece hafta içi)
        if (ogr == 'E' && isWeekday(gun)) {
            ogrIndirim = araSonra2 * 0.10;
        }

        double toplam = araSonra2 - ogrIndirim;

        double bahsis = Math.round(toplam * 0.10 * 100.0) / 100.0; // 2 ondalık
        // Formatlamayı çıktı sırasında yapacağız.

        System.out.println("\n=== HESAPLAMA DETAYLARI ===");
        System.out.println("Ara Toplam: " + String.format("%.2f", araToplam) + " TL");
        System.out.println("Combo indirimi (15%): -" + String.format("%.2f", comboIndirim) + " TL");
        System.out.println("Happy Hour içecek indirimi (20%): -" + String.format("%.2f", happyIndirim) + " TL");
        System.out.println("200 TL üzeri indirim (10%): -" + String.format("%.2f", ind200) + " TL");
        System.out.println("Öğrenci indirimi (10%): -" + String.format("%.2f", ogrIndirim) + " TL");
        System.out.println("--------------------------------------");
        System.out.println("Toplam: " + String.format("%.2f", toplam) + " TL");
        System.out.println("Bahşiş önerisi (%10): " + String.format("%.2f", bahsis) + " TL");
    }
}

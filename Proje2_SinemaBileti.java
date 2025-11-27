Isim Soyisim: Lalise Adem Wado
Ogrenci No: 240541610
konu: Sinema Bilet Sistemi.


import java.util.Scanner;
public class SenemaBIleti {
    // 1) Hafta sonu kontrolü
    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7);
    }

    // 2) Matine kontrolü (12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3) Temel fiyat hesapla
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) {
            return 45;
        }
        if (!weekend && !matinee) {
            return 65;
        }
        if (weekend && matinee) {
            return 55;
        } else {
            return 85;
        }
    }
        // 4) İndirim oranı hesapla
        public static double calculateDiscount ( int yas, int meslek, int gun){
            // Yaşa göre indirim
            if (yas >= 65) return 0.30;   // %30
            if (yas < 12) return 0.25;    // %25

            // Meslek: 1=Öğrenci, 2=Öğretmen, 3=Diğer
            switch (meslek) {
                case 1: // Öğrenci
                    if (gun >= 1 && gun <= 4) return 0.20; // Pzt-Perş %20
                    return 0.15; // Cuma-Pazar %15

                case 2: // Öğretmen
                    if (gun == 3) return 0.35; // Çarşamba %35
                    return 0.0;

                default:
                    return 0.0;
            }
        }
        public static double getFormatExtra ( int tur){
            switch (tur) {
                case 1:
                    return 0;   // 2D
                case 2:
                    return 25;  // 3D
                case 3:
                    return 35;  // IMAX
                case 4:
                    return 50;  // 4DX
                default:
                    return 0;
            }
        }

        // 6) Toplam fiyat hesaplama
        public static double calculateFinalPrice ( int gun, int saat, int yas, int meslek, int tur){

            double base = calculateBasePrice(gun, saat);
            double discountRate = calculateDiscount(yas, meslek, gun);
            double discountedPrice = base - (base * discountRate);
            double extra = getFormatExtra(tur);

            return discountedPrice + extra;
        }

        // 7) Bilet bilgisi oluştur
        public static String generateTicketInfo ( int gun, int saat, int yas, int meslek, int tur){

            double base = calculateBasePrice(gun, saat);
            double discountRate = calculateDiscount(yas, meslek, gun);
            double discountAmount = base * discountRate;
            double finalPrice = calculateFinalPrice(gun, saat, yas, meslek, tur);

            return "=== BİLET BİLGİSİ ===\n" +
                    "Temel Fiyat: " + base + " TL\n" +
                    "İndirim: -" + discountAmount + " TL\n" +
                    "Format Ekstra: +" + getFormatExtra(tur) + " TL\n" +
                    "------------------------\n" +
                    "Toplam Fiyat: " + finalPrice + " TL\n";
        }

        // MAIN
        public static void main (String[]args){

            Scanner input = new Scanner(System.in);

            System.out.print("Gün (1=Pzt ... 7=Paz): ");
            int gun = input.nextInt();

            System.out.print("Saat (8-23): ");
            int saat = input.nextInt();

            System.out.print("Yaş: ");
            int yas = input.nextInt();

            System.out.print("Meslek (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
            int meslek = input.nextInt();

            System.out.print("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
            int tur = input.nextInt();

            System.out.println();
            System.out.println(generateTicketInfo(gun, saat, yas, meslek, tur));
        }


}


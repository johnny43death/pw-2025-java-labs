package pl.edu.pw.elka.prm2t.lab7;
/**
 * @author Igor Kutermankiewicz
 * @author Kajetan Rosik
 */

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Aukcja {
    private static double cena = 100.0;
    private static String zwyciezca = "Brak";
    private static final Object lock = new Object();
    private static final AtomicBoolean trwajaca = new AtomicBoolean(true);

    public static void main(String[] args) {
        Thread[] licytatorzy = new Thread[5];

        for (int i = 0; i < licytatorzy.length; i++) {
            String nazwa = "Licytator-" + (i + 1);
            licytatorzy[i] = new Thread(new Licytator(nazwa));
            licytatorzy[i].start();
        }

        // Aukcja trwa 30 sekund
        try {
            Thread.sleep(30_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        trwajaca.set(false); // Koniec aukcji

        for (Thread t : licytatorzy) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Wynik
        System.out.printf("Aukcja zakończona! Cena końcowa: %.2f zł, zwycięzca: %s\n", cena, zwyciezca);
    }

    static class Licytator implements Runnable {
        private final String nazwa;
        private final Random random = new Random();

        public Licytator(String nazwa) {
            this.nazwa = nazwa;
        }

        @Override
        public void run() {
            while (trwajaca.get()) {
                try {
                    Thread.sleep(500 + random.nextInt(1501)); // 0.5–2s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    if (!trwajaca.get()) break;

                    double przyrost = cena * (1 + random.nextDouble() * 0.04 + 0.01); // 1–5%
                    cena = Math.round(przyrost * 100.0) / 100.0;
                    zwyciezca = nazwa;

                    System.out.printf("%s licytuje. Nowa cena: %.2f zł\n", nazwa, cena);
                }
            }
        }
    }
}


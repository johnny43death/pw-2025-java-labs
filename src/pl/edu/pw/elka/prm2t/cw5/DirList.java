package pl.edu.pw.elka.prm2t.cw5;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kajetan Rosik
 */

public class DirList {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("komenda: java DirList.java <ścieżka>");
            return;
        }

        String dirPath = args[0];
        File dir = new File(dirPath);

        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("Podana ścieżka nie jest katalogiem.");
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) {
            System.err.println("Nie udało się odczytać katalogu.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (File file : files) {
            String name = file.getName();
            long size = file.length();
            String date = sdf.format(new Date(file.lastModified()));
            String perms = (file.canRead() ? "r" : "-") +
                    (file.canWrite() ? "w" : "-") +
                    (file.canExecute() ? "x" : "-");

            System.out.printf("%-30s %10d B  %s  %s%n", name, size, date, perms);
        }
    }
}
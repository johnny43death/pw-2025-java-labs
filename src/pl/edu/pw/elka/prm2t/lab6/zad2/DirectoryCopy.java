package pl.edu.pw.elka.prm2t.lab6.zad2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryCopy {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: DirectoryCopy <source directory> <destination directory>");
            return;
        }

        File sourceDir = new File(args[0]);
        File destDir = new File(args[1]);

        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            System.out.println("Source directory does not exist or is not a directory");
            return;
        }

        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        List<Thread> threads = new ArrayList<>();
        copyDirectory(sourceDir, destDir, threads);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Directory copied successfully.");
    }

    private static void copyDirectory(File sourceDir, File destDir, List<Thread> threads) {
        for (File file : sourceDir.listFiles()) {
            File destFile = new File(destDir, file.getName());

            if (file.isDirectory()) {
                destFile.mkdirs();
                copyDirectory(file, destFile, threads);
            } else {
                FileCopyThread thread = new FileCopyThread(file, destFile);
                threads.add(thread);
                thread.start();
            }
        }
    }
}



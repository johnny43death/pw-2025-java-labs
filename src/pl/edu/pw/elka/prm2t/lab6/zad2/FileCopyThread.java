package pl.edu.pw.elka.prm2t.lab6.zad2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileCopyThread extends Thread {
    private File source;
    private File dest;

    public FileCopyThread(File source, File dest) {
        this.source = source;
        this.dest = dest;
    }

    @Override
    public void run() {
        try {
            Files.copy(source.toPath(), dest.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



package pl.edu.pw.elka.prm2t.lab6.zad1;

public class SumationThread extends Thread {
    private long start;
    private long end;
    private long sum;

    public SumationThread(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getSum() {
        return sum;
    }

    @Override
    public void run() {
        sum = 0;
        for (long i = start; i <= end; i++) {
            sum += i;
        }
    }
}


package pl.edu.pw.elka.prm2t.lab6.zad1;

import java.util.ArrayList;
import java.util.List;

public class ParallelSum {
    public static void main(String[] args) {
        long N = 1_000_000_000;
        int maxThreads = 100;

        for (int numThreads = 1; numThreads <= maxThreads; numThreads++) {
            long startTime = System.currentTimeMillis();
            long sum = parallelSum(N, numThreads);
            long endTime = System.currentTimeMillis();
            System.out.println("Threads: " + numThreads + " Time: " + (endTime - startTime) + " ms, Sum: " + sum);
        }
    }

    private static long parallelSum(long N, int numThreads) {
        long chunkSize = N / numThreads;
        List<SumationThread> threads = new ArrayList<>();
        long sum = 0;

        for (int i = 0; i < numThreads; i++) {
            long start = i * chunkSize + 1;
            long end = (i == numThreads - 1) ? N : start + chunkSize - 1;
            SumationThread thread = new SumationThread(start, end);
            threads.add(thread);
            thread.start();
        }

        for (SumationThread thread : threads) {
            try {
                thread.join();
                sum += thread.getSum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return sum;
    }
}


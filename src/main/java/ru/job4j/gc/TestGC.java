package ru.job4j.gc;

import java.util.Date;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

public class TestGC {

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    private static int freeMemoryMB() {
        return (int) (ENVIRONMENT.freeMemory() / MB);
    }

    public static void main(String[] args) throws InterruptedException {
        int arrLength = (args.length > 0) ? Integer.parseInt(args[0]) : 1500000;
        System.out.printf("Free memory at the start of work = %d MB%n", freeMemoryMB());
        User emptyUser = new User();
        System.out.printf("Empty user weighs = %d BYTES%n", sizeOf(emptyUser));
        User notEmptyUser = new User(1, "a");
        System.out.printf("Not empty user weighs = %d BYTES%n", sizeOf(notEmptyUser));
        User[] users = new User[arrLength];
        System.out.println("Free memory after creating an array of " + arrLength + " = " + freeMemoryMB() + " MB");
        int deleteIterations = 0;
        int countOfGC = 0;
        int lastMemory = freeMemoryMB(), actualMemory;
        int iterations = arrLength * 3;
        Date start = new Date();
        System.out.println(" Delete iteration | Free memory | Number of elements | Was cleaning");
        System.out.println("---------------------------------------------------------------------------");
        for (int i = 0, k = 0; i < iterations; i++, k++) {
            if (i != 0 && i % 10000 == 0) {
                int limit = k - 7000;
                for (int j = k - 1; j >= limit; j--) {
                    users[j] = null;
                }
                k = limit;
                deleteIterations++;
                actualMemory = freeMemoryMB();
                boolean gc = actualMemory > lastMemory;
                if (gc) {
                    countOfGC++;
                }
                lastMemory = actualMemory;
                System.out.printf(" № %-14d | %-11d | %-18d | %-1s %n", deleteIterations, lastMemory, k, gc ? "Y" : "N");
            }
            users[k] = new User(i, "№" + i);
        }
        actualMemory = freeMemoryMB();
        if (actualMemory > lastMemory) {
            countOfGC++;
        }
        Date finish = new Date();
        int diff = (int) (finish.getTime() - start.getTime());
        System.out.printf("Free memory at the end of work = %d MB%n", actualMemory);
        System.out.printf("The time has passed = %d ms%n", diff);
        System.out.printf("The number of cleanings during the operation of the application = %d%n", countOfGC);
        System.out.printf("Average number of cleanups per millis = %d%n", diff / countOfGC);
    }
}

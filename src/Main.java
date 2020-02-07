import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        //zaehlenVonBis(1, 100);
        //zaehlenVonBisMitThreads(1,100);
        summeVonBisMitThreads(1, 100);
        //System.out.println(summe);
    }

    private static int summeVonBis(int min, int max) {
        if (max - min < 10) {
            int summe = 0;
            for (int i = min; i <= max; i++) {
                summe += i;
            }
            return summe;
        } else {
            int mitte = (min + max) / 2;
            System.out.println("Mitte: " + mitte);
            return summeVonBis(min, mitte) + summeVonBis(mitte + 1, max);
        }
    }

    private static void zaehlenVonBisMitThreads(int min, int max) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ZaehlenTask rootTask = new ZaehlenTask(min, max);
        forkJoinPool.invoke(rootTask);
    }

    private static void summeVonBisMitThreads(int min, int max) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SummenTask rootTask = new SummenTask(min, max);
        forkJoinPool.invoke(rootTask);
        try {
            int ergebnis = rootTask.get();
            System.out.println(ergebnis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void zaehlenVonBis(int min, int max) {
        if (max - min < 10) {
            for (int i = min; i <= max; i++) {
                System.out.println(i);
            }
        } else {
            int mitte = (max + min) / 2;
            System.out.println("Mitte: " + mitte);
            zaehlenVonBis(min, mitte);
            zaehlenVonBis(mitte + 1, max);
        }
    }
}

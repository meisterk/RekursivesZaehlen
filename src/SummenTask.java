import java.util.concurrent.RecursiveTask;

public class SummenTask extends RecursiveTask<Integer> {
    private int min;
    private int max;

    public SummenTask(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected Integer compute() {
        if (max - min < 10) {
            int summe = 0;
            for (int i = min; i <= max; i++) {
                summe += i;
            }
            return summe;
        } else {
            int mitte = (min + max) / 2;
            System.out.println("Mitte: " + mitte);
            SummenTask taskLinks = new SummenTask(min, mitte);
            SummenTask taskRechts = new SummenTask(mitte + 1, max);
            invokeAll(taskLinks, taskRechts);

            return taskLinks.join() + taskRechts.join();
        }
    }
}

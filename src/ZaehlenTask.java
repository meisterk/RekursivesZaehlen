import java.util.concurrent.RecursiveAction;

public class ZaehlenTask extends RecursiveAction {
    private int min;
    private int max;

    public ZaehlenTask(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected void compute() {
        if(max - min < 10) {
            for (int i = min; i <= max; i++) {
                System.out.println(i);
            }
        }else{
            int mitte = (max + min) / 2;
            ZaehlenTask task1 = new ZaehlenTask(min, mitte);
            ZaehlenTask task2 = new ZaehlenTask(mitte + 1, max);
            RecursiveAction.invokeAll(task1, task2);
        }
    }
}

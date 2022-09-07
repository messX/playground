import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future res = executorService.submit(new Task());
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());
        List<Future<String>> futures = executorService.invokeAll(tasks);
        System.out.println("res:" + res.get());
        futures.forEach((res1)->{
            try {
                System.out.println("res1" + res1.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.shutdown();
    }
}


class Task implements Callable<String>{

    public Task(){
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(500);
        return "I am result :" + Math.random() * 100;
    }
}
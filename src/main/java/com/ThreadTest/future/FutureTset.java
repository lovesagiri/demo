package com.ThreadTest.future;

import java.util.concurrent.*;

public class FutureTset {
    private final ConcurrentHashMap<Object, Future<String>> cm = new ConcurrentHashMap<>();

    private String exTask(final String taskName){
        //根据任务名循环获取执行任务
        while (true){
            Future<String> future = cm.get(taskName);
            if(future == null){
                Callable<String> callable = new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return taskName;
                    }
                };
                FutureTask<String> futureTask = new FutureTask<>(callable);
                future = cm.putIfAbsent(taskName,futureTask);
                if(future == null){
                    future = futureTask;
                    futureTask.run();
                }
            }
            try {
                return future.get();
            } catch (InterruptedException e) {
                cm.remove(taskName,future);
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

    }
    public static void main(String[] args) {

    }

}

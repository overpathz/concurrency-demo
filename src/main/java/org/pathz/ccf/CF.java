package org.pathz.ccf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CF {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(3_000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return 10;
                })
                .thenApply(result -> result * 2)
                .thenApply(result -> result + 5);

        System.out.println(future.get());
    }
}

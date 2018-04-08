package apps.sample.operators;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.Flowable;

public final class OparatprMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("開始");
        concatMap()
            .subscribe(new DebugSubscriber<>("No.1"));
        Thread.sleep(5000L);
        System.out.println("終了");
    }

    // Flowableを生成するオペレータ。

    private static Flowable<String> just() {
        return Flowable.just("A","B","C","D","E");
    }

    private static Flowable<String> fromArray() {
        return Flowable.fromArray("A","B","C","D","E");
    }

    private static Flowable<String> fromIterable() {
        return Flowable.fromIterable(
                Stream.of("a","b","c","d","e").collect(Collectors.toList()));
    }

    private static Flowable<Long> fromCallable() {
        return Flowable.fromCallable(() -> System.currentTimeMillis());
    }

    private static Flowable<Integer> range() {
        return Flowable.range(10, 3);
    }

    private static Flowable<Long> interval() {
        // 3回で終わり
        return Flowable.interval(1000L, TimeUnit.MILLISECONDS).take(3);
    }

    private static Flowable<Long> timer() {
        return Flowable.timer(1000L, TimeUnit.MILLISECONDS);
    }

    private static Flowable<LocalTime> defer() {
        return Flowable.defer(() -> Flowable.just(LocalTime.now()));
//        return Flowable.just(LocalTime.now());
    }

    private static <T> Flowable<T> empty() {
        return Flowable.empty();
    }

    private static Flowable<Throwable> error() {
        return Flowable.error(new Exception());
    }

    private static <T> Flowable<T> never() {
        return Flowable.never();
    }

    // 通知するデータを変換するオペレータ。

    private static Flowable<String> map() {
        return just().map(data -> data.toLowerCase());
    }

    private static Flowable<String> flatMap() {
        return just().flatMap(data -> Flowable.just(data,data));
    }

    private static Flowable<String> flatMap2() {
        return range().flatMap(data ->
                Flowable.interval(1000L, TimeUnit.MILLISECONDS).take(3),
                (source, newData) -> "[" + source + "]" + newData);
    }

    private static Flowable<Integer> flatMap3() {
        // 3つめのデータ0でエラー発生
        return Flowable.just(1,2,0,4,5).map(data -> 10/data).flatMap(
                data -> Flowable.just(data),
                error -> Flowable.just(-1),
                () -> Flowable.just(100));
    }

    private static Flowable<String> concatMap() {
        return range().concatMap(source -> Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(2)
                .map(data -> System.currentTimeMillis() + "ms: [" + source + "]" + data));
    }


}

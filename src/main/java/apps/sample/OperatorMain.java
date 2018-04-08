package apps.sample;

import io.reactivex.Flowable;

public class OperatorMain {

    public static void main(String[] args) throws InterruptedException {
        Flowable<Integer> flowable =
                // 引数のデータを順に通知するFlowableを生成
                Flowable.just(1,2,3,4,5,6,7,8,9,10)
                    // 偶数のみに絞る
                    .filter(data -> data % 2 == 0)
                    // 100倍する
                    .map(data -> data * 100);


        // 値が決まるのはFlowableを生成したタイミング
        Flowable<Long> f1 =
                Flowable.just(System.currentTimeMillis());

        // 値が決まるのは購読したタイミング
        Flowable<Long> f2 =
                Flowable.fromCallable(() -> System.currentTimeMillis());


        // 購読し受け取ったデータを出力する
        flowable.subscribe(System.out::println);

        for (int i = 0; i < 3; i++) {
            f1.subscribe(System.out::println);
            f2.subscribe(System.out::println);
            Thread.sleep(1000);
        }

    }

}

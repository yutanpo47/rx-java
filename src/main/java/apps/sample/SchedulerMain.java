package apps.sample;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

public class SchedulerMain {

    // 計算方法
    private enum State {
        // 加算
        ADD,
        // 乗算
        MULTIPLY
    };

    // 現在の計算方法を保持
    private static State calcMethod;

    public static void main(String[] args) throws InterruptedException {
        // 計算方法を加算に設定
        calcMethod = State.ADD;

        Flowable<Long> flowable =
                // 300ミリ秒毎に0から始まる数値を非同期で通知するFlowableを生成
                Flowable.interval(300L, TimeUnit.MILLISECONDS)
                    // 合計7回まで消費者に通知して終了する
                    .take(7)
                    // 通知するデータを集計する
                    .scan((sum, data) -> {
                        if (calcMethod.equals(State.ADD)) {
                            return sum + data;
                        }
                        return sum * data;
                    });

        flowable.subscribe(System.out::println);

        Thread.sleep(1000);
        // 計算方法を乗算に変更。これは本来やってはいけない。
        calcMethod = State.MULTIPLY;
        Thread.sleep(5000);
    }

}

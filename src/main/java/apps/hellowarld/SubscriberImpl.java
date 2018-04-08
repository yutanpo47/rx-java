package apps.hellowarld;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SubscriberImpl implements Subscriber<SampleItem> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription = s;
        subscription.request(1);
    }

    @Override
    public void onNext(SampleItem t) {
        System.out.println(t.getMessage());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void onComplete() {
        // TODO 自動生成されたメソッド・スタブ

    }

}

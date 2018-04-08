package apps.hellowarld;

import org.reactivestreams.Subscription;

public class SubscriptionImpl implements Subscription {

    @Override
    public void request(long n) {
        // データを生成してPublisherにonNext()させる
    }

    @Override
    public void cancel() {
        // Publisherを止める
    }

}

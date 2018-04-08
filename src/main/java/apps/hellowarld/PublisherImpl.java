package apps.hellowarld;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public final class PublisherImpl implements Publisher<SampleItem> {

    private Subscriber subscriber;

    @Override
    public void subscribe(Subscriber<? super SampleItem> s) {
        subscriber = s;
        subscriber.onSubscribe(new SubscriptionImpl());
    }

    public void execute(String msg) {

    }

}

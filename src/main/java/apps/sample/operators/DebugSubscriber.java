package apps.sample.operators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import io.reactivex.subscribers.DisposableSubscriber;

public final class DebugSubscriber<T> extends DisposableSubscriber<T> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("mm:ss.SSS");

    private Optional<String> label;

    public DebugSubscriber() {
        super();
        this.label = Optional.empty();
    }

    public DebugSubscriber(String label) {
        super();
        this.label = Optional.of(label);
    }

    @Override
    public void onNext(T data) {
        System.out.println(getLabel() + ": " + data);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println(getLabel() + ": エラー = " + t);
    }

    @Override
    public void onComplete() {
        System.out.println(getLabel() + ": 完了");

    }

    private String getLabel() {
        return Thread.currentThread().getName()
                + ": "
                + LocalDateTime.now().format(FORMATTER)
                + label.map(l -> ": " + l).orElse("");
    }

}

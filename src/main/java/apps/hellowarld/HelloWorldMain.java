package apps.hellowarld;

import io.reactivex.Flowable;

public class HelloWorldMain {

    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("Hello", "World!!");

        flowable.subscribe(System.out::println);


        PublisherImpl publisher = new PublisherImpl();
        publisher.execute("hello world");


    }

}

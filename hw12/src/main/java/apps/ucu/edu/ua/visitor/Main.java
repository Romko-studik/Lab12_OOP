package hw12.src.main.java.apps.ucu.edu.ua.visitor;

public class Main {
    public static void main(String[] args) {
        Group<Integer> nestedGroup = new Group<>();
        nestedGroup.addTask(new Signature<>(System.out::println)).addTask(new Signature<>(x -> System.out.println(x * x)));

        Group<Integer> group = new Group<>();
        group.addTask(nestedGroup).addTask(new Signature<>(x -> System.out.println(x * x * x)));

        StampingAPI.stamp(group);
        group.apply(10);
    }
}


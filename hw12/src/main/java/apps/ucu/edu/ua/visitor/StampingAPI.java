package hw12.src.main.java.apps.ucu.edu.ua.visitor;
import java.util.List;

class StampingAPI {
    public static <T> void stamp(Group<T> group) {
        String groupId = group.getGroupUuid();
        List<Task<T>> tasks = group.getTasks();
        for (Task<T> task : tasks) {
            if (task instanceof Signature) {
                Signature<T> signature = (Signature<T>) task;
                signature.setHeader("groupId", groupId);
            }
        }
    }
}

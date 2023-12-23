package hw12.src.main.java.apps.ucu.edu.ua.visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Group<T> extends Task<T> {
    private String groupUuid;
    private List<Task<T>> tasks;

    public Group<T> addTask(Task<T> task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        return this;
    }

    @Override
    public void freeze() {
        super.freeze();
        groupUuid = getId(); 
        for (Task<T> task : tasks) {
            task.freeze();
        }
    }

    public void apply(T arg) {
        this.freeze();
        tasks = Collections.unmodifiableList(tasks);
        for (Task<T> task : tasks) {
            task.apply(arg);
        }
    }
    public List<Task<T>> getTasks() {
        return tasks;
    }

    public String getGroupUuid() {
        return groupUuid;
    }
}


import java.util.ArrayList;
import java.util.List;

public class Tasks {
    List<Task> tasks = new ArrayList<>();
    public void add_task(Task task){
        tasks.add(task);
    };
    public void get_tasks(){
        for (Task task : this.tasks) System.out.println(this.tasks.indexOf(task)+"." +task.task + " - " + task.complete);
    }
    public void delete(int id){
        Task target = tasks.get(id);
        tasks.remove(target);
    }
    public void update(int id, String update){
        Task target = tasks.get(id);
        target.task = update;
    }
}

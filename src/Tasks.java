import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tasks {
    List<Task> tasks = new ArrayList<>();
    public Tasks(){

    }
    public void add_task(Task task){
        tasks.add(task);
    };
    public void get_tasks(){
        for (Task task : this.tasks){
            System.out.println("id:"+this.tasks.indexOf(task)+". " +task.task + " - " + ((task.complete)?"Complete!":"In-Progress"));
            System.out.println("+--------------------------+");
        };
    }
    public String get_string_tasks(){
        String full_string = "";
        for (Task task : this.tasks){
            full_string += "id:"+this.tasks.indexOf(task)+". " +task.task + " - " + ((task.complete)?"Complete!":"In-Progress")+"\n";
        };
        return full_string;
    }
    public String get_formated_tasks(){
        String full_string = "";
        for (Task task : this.tasks){
            full_string += this.tasks.indexOf(task)+"," +task.task + "," + ((task.complete)?"Complete!":"In-Progress")+"\n";
        };
        return full_string;
    };
    public void delete(int id){
        Task target = tasks.get(id);
        tasks.remove(target);
    }
    public void update(int id, String update){
        Task target = tasks.get(id);
        target.task = update;
    }
}

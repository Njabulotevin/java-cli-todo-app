public class Task {
    int id;
    String task;
    Boolean complete;
    public Task(String task, int id){
        this.id = id;
        this.task = task;
        this.complete = false;

    }
    public void set_complete(){
        complete = true;
    }
    public String get_string_task(){return this.id+","+this.task+","+(this.complete?"Complete!":"In-Progress");}
}


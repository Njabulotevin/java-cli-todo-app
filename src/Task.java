public class Task {
    String task;
    Boolean complete;
    public Task(String task){
        this.task = task;
        this.complete = false;

    }
    public void set_complete(){
        complete = true;
    }
}


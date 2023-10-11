import java.util.Scanner;

public class Todo {
    public Todo(){
        System.out.println("Hello Welcome to TODO-APP");
    }
    String commands = """
            -----------------------------
            Possible commands:
            *add - adds a new task
            *get - shows you the tasks
            *complete - change the status of the task to complete
            *delete - deletes the task
            *update - updates a specific task
            *Exit - Closes the program
            ------------------------------""";

    Scanner user_input = new Scanner(System.in);
    Tasks my_tasks = new Tasks();
    public void start(){
        while(true){
            System.out.println(commands);
            System.out.print("Enter command: ");
            String command = user_input.nextLine();

            if(command.equalsIgnoreCase("exit")){break;};
            outerLoop : switch (command.toLowerCase()){
                case "add":
                    String task = prompt_user_for_task("Enter");
                    my_tasks.add_task(new Task(task));
                    System.out.println("Task! added");
                    break;
                case "get":
                    my_tasks.get_tasks();
                    if(my_tasks.tasks.isEmpty()){
                        System.out.println("No tasks found!");}
                    break;
                case "complete":
                   if(my_tasks.tasks.isEmpty()){
                       System.out.println("You have no tasks yet, add a new task");
                   }else{
                            int task_id = prompt_user_for_id();
                       try{
                           Task target = my_tasks.tasks.get(task_id);
                           System.out.printf("Task found! : %s", target.task);
                           target.set_complete();
                           System.out.println("Task changed to completed!");
                       }catch (Exception e)
                       {System.out.printf("Cannot find the task with id of %s\n ", task_id);}
                    }
                   break;
                case "update":
                    if(my_tasks.tasks.isEmpty()){
                        System.out.println("You have no tasks yet, add a new task");
                    } else{
                      int task_id = prompt_user_for_id();
                        if(find_task(task_id) != null){
                            String update_task = prompt_user_for_task("Update");
                            my_tasks.update(task_id, update_task);
                            System.out.println("Task Updated!");
                        }
                    }
                    break;
                case "delete":
                    if(my_tasks.tasks.isEmpty()){
                        System.out.println("You have no tasks yet, add a new task");
                    } else{
                        int task_id = prompt_user_for_id();
                        if(find_task(task_id) != null){
                            my_tasks.delete(task_id);
                        }

                    }
                    break;

                default:
                    System.out.println("Didn't get command or Not recognised");
            }
        }
        user_input.close();
    };
    private Task find_task(int id){
        my_tasks.get_tasks();
        try{
            return my_tasks.tasks.get(id);
        }catch (Exception e)
        {
            return null;
        }

    };
    private Integer prompt_user_for_id(){
        try {
            my_tasks.get_tasks();
            System.out.print("Enter task id: ");
            String task_id = user_input.nextLine();
            return Integer.parseInt(task_id);
        }catch(Exception e){
            System.out.println("Invalid id");
            return prompt_user_for_id();
        }
    }

    private String prompt_user_for_task(String enter_or_update){
        System.out.printf("%s task: ", enter_or_update);
        return user_input.nextLine();
    }

}

public class Todo {
    String[] args;
    String ESC = "\u001B";
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";
    String SET_BOLD_TEXT = "\033[0;1m";
    String title = String.format("""
             %s
             _______ ____  _____   ____             _____  _____ \s
            |__   __/ __ \\|  __ \\ / __ \\      /\\   |  __ \\|  __ \\\s
               | | | |  | | |  | | |  | |    /  \\  | |__) | |__) |
               | | | |  | | |  | | |  | |   / /\\ \\ |  ___/|  ___/\s
               | | | |__| | |__| | |__| |  / ____ \\| |    | |    \s
               |_|  \\____/|_____/ \\____/  /_/    \\_\\_|    |_|  \s
            """, this.RED);

    public Todo(String[] args){
        this.args = args;
    }

    String commands = String.format("""
            -----------------------------
            %s%sPossible commands [options]:%s
            
            *add - adds a new task
            *get - shows you the tasks
            *complete - change the status of the task to complete
            *delete - deletes the task
            *update - updates a specific task
            *Exit - Closes the program
            ------------------------------""",this.SET_BOLD_TEXT ,this.YELLOW,this.RESET);

//    Scanner user_input = new Scanner(System.in);
    Tasks my_tasks = new Tasks();
    FileHandling file = new FileHandling();


    public void start(){
        for(String task : file.read_file()){
            String[] splitTask = task.split(",");
            Task ts = new Task(splitTask[1], my_tasks.tasks.size());
            if(splitTask[2].equals("Complete!")){
                ts.set_complete();
            }
            my_tasks.add_task(ts);
        }
            String[] args = this.args;
            String command = "";
            if(args.length != 0){
                command = args[0];
            }
            if(args.length == 0){
                System.out.println(this.title);
                System.out.println(commands);
            } else if (command.equals("add")) {
                    this.add_task(args);
            } else if (command.equals("get")) {
                    my_tasks.get_tasks();
            } else if (command.equals("update")) {
                    this.update_task(args);
            } else if (command.equals("delete")) {
                this.delete_task(args);
            } else if (command.equals("--help") || args[0].equals("-h")) {
                    System.out.println(this.title);
                    System.out.println(commands);
            }else{
                System.out.println(this.RED + "Error"+ this.RESET +": Unknown Command\n"+ this.commands);
            }
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
    private void add_task(String[] args){
        if(args.length == 1){
                missing_args("task", "add <task>");
        }else{
            Task task = new Task(args[1], my_tasks.tasks.size());
            my_tasks.add_task(task);
            System.out.println("Task added! - use [get] to check");
            file.write_file(task.get_string_task()+"\n", false);
        }
    }

    private void  update_task(String[] args){
            if(args.length == 1){
                missing_args("id , changes", "update <id> <changes>");

            } else if (args.length == 2) {
                missing_args("changes", "update <id> <changes>");
            } else{
                String id = args[1];
                String changes = args[2];
                my_tasks.update(Integer.parseInt(id), changes);
                file.write_file(my_tasks.get_formated_tasks(), true);
                System.out.println("Task updated");
            }
    }
    private void delete_task(String[] args){
        if(args.length == 1){
            missing_args("id , changes", "update <id> <changes>");
        }else{
            my_tasks.delete(Integer.parseInt(args[1]));
            file.write_file(my_tasks.get_formated_tasks(), true);
            System.out.println("Task Deleted!");
        }
    }
    private void missing_args(String missing, String usage){
        System.out.println(this.RED + "Error"+ this.RESET +": missing" + missing +"\n"+ "Usage: Main " + usage);
    }


}

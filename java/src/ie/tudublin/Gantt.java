package ie.tudublin;


import processing.core.PApplet;
import java.util.ArrayList;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> tasks = new ArrayList<Task>(); 
	float border;
	
	float h;
	int lines = 30;

	public void settings()
	{
		size(800, 600);
		
        border = width * 0.1f;

        h = height * 0.1f;
	}

	public void loadTasks()
	{
		Table table = loadTable("tasks.csv", "header");
        for(TableRow row:table.rows())
        {
            Task t = new Task(row);
            tasks.add(t);
        }
	}

	public void printTasks()
	{
		for(Task t:tasks)
        {
            System.out.println(t);
        }
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");

	 
		for (int i =0; i< lines; i++){
			
			Task t = tasks.get(i);

			float start = map(t.getStart(), 1, 30, border , width - border);
			float end = map(t.getEnd(), 1, 30, border, width - border);

			if(mouseX < start && mouseX > start ){
				t.setStart(i);		
			}
			if(mouseX < end && mouseX > end ){	
				t.setEnd(i);
			}
		}

	}
	
	public void mouseDragged()
	{
		println("Mouse dragged");

	}

	public void setup() 
	{
		loadTasks();
        printTasks();
	}

	public void displayTasks(){

		for(int i = 1; i <= lines; i++)
        {
			float x = map(i, 1, lines, border, width - border);
	
			stroke(255);
            line(x + lines, border, x + lines, height - border);

			fill(255);
			textAlign(CENTER);
			text(i, x + lines, (border / 2) );

		}

		for(int i = 0 ; i < tasks.size() ; i ++)
        {
            Task t = tasks.get(i);

            float y = map(i, 0, tasks.size(), border, height - border);

            fill(255);
			text(t.getTask(), border, y + (h/2) );


			float start = map(t.getStart(), 1, 30, border , width - border);

			float end = map(t.getEnd(), 1, 30, border, width - border);

			float co = map(i, 0, tasks.size(),i,255);

			fill(255,co,co);
			
			rect(start+30, y + 14, end-start, 25,4);			
		}		

	}
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}
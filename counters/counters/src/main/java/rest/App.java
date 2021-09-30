package rest;

import com.google.gson.Gson;
import no.hvl.dat110.rest.counters.Counters;

import java.util.HashMap;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App {
	
	static HashMap<Integer, Todo> todos = new HashMap<Integer, Todo>();
	
	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}
		Todo todo = new Todo("hejjj", "På dejjj");
		todos.put(todo.getId(), todo);
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		get("/hello", (req, res) -> "Hello World!");
		
        get("/todo", (req, res) -> todos.get(Integer.parseInt(req.params("id"))).toJson());

		get("/todo/:id", (req, res) -> {
			System.out.println(req.params("id"));
			int id = Integer.parseInt(req.params("id"));
			System.out.println(todos);
			return todos.get(id).toJson();
			//return null;
		});
 
        get("/todo/summary", (req, res) -> todos.get(Integer.parseInt(req.params("id"))).getSummary());

        get("/todo/description", (req, res) -> todos.get(Integer.parseInt(req.params("id"))).getDescription());

        post("/todo", (req,res) -> {
        
        	Gson gson = new Gson();
			Todo todoo = gson.fromJson(req.body(), Todo.class);
			int id = todoo.getId();
        	todos.put(id, todoo);
        
            return todos.get(id).toJson();
        	
        });

		put("/todo", (req, res) -> {

			Gson gson = new Gson();
			Todo todoo = gson.fromJson(req.body(), Todo.class);
			int id = todoo.getId();
			todos.put(id, todoo);

			return todos.get(id).toJson();
		});

		delete("/todo/:id", (req, res) -> {
			int id = todos.get(Integer.parseInt(req.params("id"))).getId();
			return todos.remove(id).toJson();


		});
    }
    
}

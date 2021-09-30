package rest;

import com.google.gson.Gson;

public class Todo {
    private static int nextId = 0;
    private int id;
    private String summary;
    private String description;

    public Todo() {
        this.id = nextId++;
        this.summary = "Nej";
        this.description = "nejjj";
    }

    public Todo (String summary, String description) {
        this.id = nextId++;
        this.summary = summary;
        this.description = description;
    }

    public Todo (int id, String summary, String description){
        this.id = id;
        this.summary = summary;
        this.description = description;
    }

    public int getId(){
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String toJson() {
        Gson gson = new Gson();

        String jsonInString = gson.toJson(this);

        return jsonInString;
    }
}

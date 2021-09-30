package rest;

import no.hvl.dat110.rest.counters.Counters;
import okhttp3.*;

import java.io.IOException;

public class PutRequest {

	public static final MediaType JSON
    = MediaType.parse("application/json; charset=utf-8");
	
	public static void main(String[] args) {

		Todo todo = new Todo("hejj", "påå dejj");
		
		OkHttpClient client = new OkHttpClient();

		RequestBody body = RequestBody.create(JSON, todo.toJson());
		
		Request request = new Request.Builder().url("http://localhost:8080/todo").put(body).build();

		System.out.println(request.toString());

		try (Response response = client.newCall(request).execute()) {
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

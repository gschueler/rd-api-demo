package test.rd;

import org.rundeck.client.RundeckClient;
import org.rundeck.client.api.RundeckApi;
import org.rundeck.client.api.model.JobItem;
import org.rundeck.client.util.Client;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        Client<RundeckApi> build = RundeckClient.builder().baseUrl(args[0]).tokenAuth(args[1]).build();
        Call<List<JobItem>> listCall = build.getService().listJobs(args[2], null);
        Response<List<JobItem>> execute = listCall.execute();
        if(execute.isSuccessful()) {
            List<JobItem> body = execute.body();
            System.out.println(body.size() + " found:");
            body.forEach(jobItem -> System.out.println(jobItem.getId()));
        }else{
            System.err.println("Error: " + execute.code() + " " + execute.message());
        }
    }
}

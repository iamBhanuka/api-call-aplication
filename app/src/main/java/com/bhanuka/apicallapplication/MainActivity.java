package com.bhanuka.apicallapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.bhanuka.apicallapplication.models.APIResponse;
import com.bhanuka.apicallapplication.models.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView rvEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_circular);
        rvEmployees = findViewById(R.id.rv_employees);

        ApiCallTask task = new ApiCallTask();
        task.execute("http://dummy.restapiexample.com/api/v1/employees");
    }

    private APIResponse parseJson(String json){
        APIResponse apiResponse = null;
        try {
            JSONObject response = new JSONObject(json);
            apiResponse = new APIResponse(response.getString("status"));

            JSONArray employees = response.getJSONArray("data");

            for(int i = 0;i < employees.length();i++){
                apiResponse.getEmployees().add(Employee.fromJson(employees.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

    private void attachResponse(APIResponse apiResponse){
        EmployeeAdapter adapter = new EmployeeAdapter(apiResponse);
        rvEmployees.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvEmployees.setAdapter(adapter);
    }

    private class ApiCallTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... urls) {
            String result;
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(150000); //milliseconds
                conn.setConnectTimeout(15000); // milliseconds
                conn.setRequestMethod("GET");

                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            conn.getInputStream(), "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");

                    }
                    result = sb.toString();
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.GONE);
            APIResponse apiResponse = parseJson(result);
            attachResponse(apiResponse);
        }
    }
}

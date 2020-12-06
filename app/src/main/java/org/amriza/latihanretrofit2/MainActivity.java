package org.akhdan.latihanretrofit2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.akhdan.latihanretrofit2.databinding.ActivityMainBinding;
import org.akhdan.latihanretrofit2.models.Repo;
import org.akhdan.latihanretrofit2.services.GitHubService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService services = retrofit.create(GitHubService.class);

        Call<List<Repo>> repos = services.listRepos("hbb-polinema");

        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> repoList) {
                binding.setRepo(repoList.body().get(0));
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void cariUser(View view) {
    }
}
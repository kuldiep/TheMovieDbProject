package com.android_poc.themoviedbproject;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;

import com.android_poc.themoviedbproject.networking.TMDBRetrofitClient;
import com.android_poc.themoviedbproject.networking.TmdbTopMoviesApiService;
import com.android_poc.themoviedbproject.tos.TmdbRootRespTO;
import com.android_poc.themoviedbproject.utils.AppConstants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  TmdbTopMoviesApiService api =
                TMDBRetrofitClient.INSTANCE.buildService(TmdbTopMoviesApiService.class);
        api.getTopMoviesFromApi(AppConstants.API_KEY).enqueue(new Callback<TmdbRootRespTO>() {
            @Override
            public void onResponse(Call<TmdbRootRespTO> call, Response<TmdbRootRespTO> response) {
                Log.d(AppConstants.TAG, "onResponse: in activity is = "+response.body());
                Log.d(AppConstants.TAG, "onResponse: result list is = "+response.body().getResults());
            }

            @Override
            public void onFailure(Call<TmdbRootRespTO> call, Throwable t) {
                Log.e(AppConstants.TAG, "onFailure: exceptino occured",t.getCause());
            }
        });*/
    }
}
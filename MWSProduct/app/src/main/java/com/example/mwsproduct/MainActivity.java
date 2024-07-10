package com.example.mwsproduct;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ListView;

import okhttp3.OkHttpClient;

import com.example.mwsproduct.DataModal;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mwsproduct.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    OkHttpClient client = new OkHttpClient();

    ListView coursesLV;
    ArrayList<DataModal> dataModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        coursesLV = findViewById(R.id.idLVCourses);
        dataModalArrayList = new ArrayList<>();

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        loadDatainListview();
    }




    private void loadDatainListview() {

        OkHttpGet example = new OkHttpGet();
        JSONObject jsonResponse;
        try {
            String response = example.run("https://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline");

            JSONArray jsonArr = new JSONArray(response);

            for (int i = 0; i < jsonArr.length(); i++)
            {
                JSONObject jsonObj = jsonArr.getJSONObject(i);

                System.out.println(jsonObj);
                DataModal dataModal = new DataModal();
                dataModal.setName();
            }




            // after getting data from Firebase we are
            // storing that data in our array list
//                                dataModalArrayList.add(dataModal);

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }



        // below line is use to get data from Firebase
        // firestore using collection in android.

//                                DataModal dataModal = d.toObject(DataModal.class);

                                // after getting data from Firebase we are
                                // storing that data in our array list
//                                dataModalArrayList.add(dataModal);
//                            }
//                            // after that we are passing our array list to our adapter class.
//                            CoursesLVAdapter adapter = new CoursesLVAdapter(MainActivity.this, dataModalArrayList);
//
//                            // after passing this array list to our adapter
//                            // class we are setting our adapter to our list view.
//                            coursesLV.setAdapter(adapter);
//                        } else {
//                            // if the snapshot is empty we are displaying a toast message.
//                            Toast.makeText(MainActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
//                        }
//                    }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
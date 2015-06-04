package com.woowahan.intern.viewtest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.woowahan.intern.viewtest.network.GsonRequest;

import java.util.List;


public class MainActivity extends AppCompatActivity implements Response.Listener<MainMenu>, Response.ErrorListener{

    private Button btn_tmp;
    private Button btn_tmp2;

    private final String URL = "http://internphp.woowahan.com:8005/intern_pro1/storeInfoC/jsonStoreIdView";
    private ListView mPeopleListView;
    private MainMenuListAdapter mPeopleListAdapter;
    private List<MainMenu> mPeopleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_tmp = (Button) findViewById(R.id.btn_tmp);
        btn_tmp2 = (Button) findViewById(R.id.btn_tmp2);
        btn_tmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, SubActivity.class);

                startActivity(it);
            }
        });

        btn_tmp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, SubActivity.class);
                startActivity(it);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    ///////////// Gson
    @Override
    protected void onStart() {
        super.onStart();

        requestPeoples();
    }

    private void requestPeoples(){
        GsonRequest reqeust = new GsonRequest(URL, MainMenu.class, null, this, this);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(reqeust);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(MainMenu response) {
        mPeopleList.clear();
        mPeopleList.addAll(response.getPeopleList());

        mPeopleListAdapter.notifyDataSetChanged();
    }

}

package com.woowahan.intern.viewtest.menuexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.woowahan.intern.viewtest.BaseFragment;
import com.woowahan.intern.viewtest.BusinessActivity;
import com.woowahan.intern.viewtest.R;
import com.woowahan.intern.viewtest.textexample.TestInfoListAdapter;
import com.woowahan.intern.viewtest.textexample.TestText;

/**
 * Created by user on 2015. 6. 4..
 */
public class TestMenuExampleFragment extends BaseFragment{
    private ListView mInfoListView;
    private TestMenuListAdapter mInfoListAdapter;
    private SparseArray<TestText> mInfoList;


    public static TestMenuExampleFragment newInstance(){
        return new TestMenuExampleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mInfoList = new SparseArray<>();

        mInfoListAdapter = new TestMenuListAdapter(getActivity(), mInfoList);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_test2, container, false);

        mInfoListView = (ListView) layout.findViewById(R.id.tmp_listview);
        mInfoListView.setAdapter(mInfoListAdapter);

        mInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // move view
                Intent it = new Intent(getActivity(), BusinessActivity.class);
                startActivity(it);
            }
        });



        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();

        updateInfos();
    }

    private void updateInfos(){

        mInfoList.put(0,new TestText("test"));

        mInfoListAdapter.notifyDataSetChanged();

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
}

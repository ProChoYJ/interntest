package com.woowahan.intern.internproject.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.woowahan.intern.internproject.BaseFragment;
import com.woowahan.intern.internproject.BusinessInfoActivity;
import com.woowahan.intern.internproject.MainActivity;
import com.woowahan.intern.internproject.R;
import com.woowahan.intern.internproject.business.Business;
import com.woowahan.intern.internproject.business.BusinessListAdapter;
import com.woowahan.intern.internproject.business.BusinessListFragment;

/**
 * Created by user on 2015. 6. 7..
 */
public class MainListFragment extends BaseFragment {

    private ListView mInfoListView;
    private MainListAdapter mInfoListAdapter;
    private SparseArray mInfoList;
    private int categoryNum;


    public static MainListFragment newInstance() {
        return new MainListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mInfoList = new SparseArray<>();

        mInfoListAdapter = new MainListAdapter(getActivity(), mInfoList);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_business_list, container, false);

        mInfoListView = (ListView) layout.findViewById(R.id.business_listview);
        mInfoListView.setAdapter(mInfoListAdapter);

        mInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // move view , click -> if instance of Recommend
                if (position == 0) {
                    ((MainActivity) getActivity()).setCurrentItem(1);

                    Log.d("Listclick", "posiiton : " + position);
                }
//                Intent it = new Intent(getActivity(), BusinessInfoActivity.class);
//                it.putExtra("Id",1);
//                startActivity(it);
            }
        });


        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();

        updateInfos();
    }

    private void updateInfos() {
        // category num
        mInfoList.put(0, new Recommend("오늘은 짜장", 1));

        mInfoList.put(1, new Recommend("석촌 교촌치킨 오픈!!!", 1));
        mInfoList.put(2, new Business(0, 1, "교촌치킨", 4.5F, 20, "200M"));

        mInfoListAdapter.notifyDataSetChanged();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //////// id getter setter

    public int getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(int categoryNum) {
        Log.d("testprint", "" + categoryNum);
        this.categoryNum = categoryNum;
    }
}

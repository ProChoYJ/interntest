package com.woowahan.intern.internproject.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.woowahan.intern.internproject.BaseFragment;
import com.woowahan.intern.internproject.R;
import com.woowahan.intern.internproject.network.GsonRequest;
import com.woowahan.intern.internproject.review.Reviews;

import java.util.ArrayList;

/**
 * Created by user on 2015. 6. 7..
 */
public class MenuListFragment extends BaseFragment implements Response.Listener<Menus>, Response.ErrorListener{
    private ArrayList<String> mGroupList = null;
    private ArrayList<ArrayList<Child>> mChildList = null;
    private ArrayList<String> mChildListContent = null;
    private BaseExpandableListAdapter mInfoListAdapter;
    private int businessId;
    private Menus mMenus;
    private menu mMenu;
    private Child mChild;
    private ArrayList<Child> mChildArrayList;

    private String URL = "http://internphp.woowahan.com:8005/intern_pro1/jsonC/storeMenuDetail?id=";


    private ExpandableListView mListView;

    public static MenuListFragment newInstance(){
        return new MenuListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_business_menu, container, false);


        mListView = (ExpandableListView) layout.findViewById(R.id.menu_list);
        mChildArrayList = new ArrayList<>();
        mChildArrayList.add(new Child(1,"양념치킨",20000 ));
        mChildArrayList.add(new Child(1,"간장치킨", 15000));
        mChildArrayList.add(new Child(1,"췌키췌키", 22000));

        mMenu = new menu("양념",mChildArrayList);






        mGroupList = new ArrayList<String>();
        mChildList = new ArrayList<ArrayList<Child>>();
        mChildListContent = new ArrayList<String>();

//        mGroupList.add("양념");
//        mGroupList.add("후라이드");
//        mGroupList.add("샐러드");
//
//        mChildListContent.add("1");
//        mChildListContent.add("2");
//        mChildListContent.add("3");
//
//        mChildList.add(mChildListContent);
//        mChildList.add(mChildListContent);
//        mChildList.add(mChildListContent);

//        mGroupList.add(mMenu.getParent());
//        mChildList.add(mMenu.getChilds());
//        mGroupList.add(mMenu.getParent());
//        mChildList.add(mMenu.getChilds());
//        mGroupList.add(mMenu.getParent());
//        mChildList.add(mMenu.getChilds());
        // for - grouplist add , chilidlist add

//        mListView.setAdapter(new MenuListExpandableAdapter(getActivity(), mGroupList, mChildList));

        // 그룹 클릭 했을 경우 이벤트
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
//                Toast.makeText(getActivity().getApplicationContext(), "g click = " + groupPosition,
//                        Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        // 차일드 클릭 했을 경우 이벤트
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
//                Toast.makeText(getActivity().getApplicationContext(), "c click = " + childPosition,
//                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // 그룹이 닫힐 경우 이벤트
        mListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getActivity().getApplicationContext(), "g Collapse = " + groupPosition,
//                        Toast.LENGTH_SHORT).show();
            }
        });

        // 그룹이 열릴 경우 이벤트
        mListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getActivity().getApplicationContext(), "g Expand = " + groupPosition,
//                        Toast.LENGTH_SHORT).show();
            }
        });



        return layout;
    }

    ///// onstart
    @Override
    public void onStart() {
        super.onStart();
        requestReviews();
//        updateInfos();
    }


    ////////// business id getter setter

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }


    //////// GSON

    private void requestReviews(){
        Log.d("jsontest", "try request");
        GsonRequest reqeust = new GsonRequest(URL+getBusinessId(), Menus.class, null, this, this);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(reqeust);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Log.d("jsontest", "fail");

        Log.e("jsontest", error.getCause().toString());
    }

    @Override
    public void onResponse(Menus response) {
//        mInfoList.addAll(response.getReviewList());
//        reviewCount.setText("" + response.getReviewList().size());
//        reviewAvgScore.setText(response.getAvg_Grade());
//        Log.d("jsontest", "" + response.getMenus().get(0).getParent());

        mGroupList.clear();
        mChildList.clear();

        for(menu menu : response.getMenus()){
            mGroupList.add(menu.getParent());
            mChildList.add(menu.getChilds());
        }

        mListView.setAdapter(new MenuListExpandableAdapter(getActivity(), mGroupList, mChildList));

//        mInfoListAdapter.notifyDataSetChanged();
    }
}

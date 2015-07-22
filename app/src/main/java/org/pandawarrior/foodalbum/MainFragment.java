package org.pandawarrior.foodalbum;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jt.myapplication.backend.foodListApi.model.Food;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by jt on 4/26/15.
 */
public class MainFragment extends Fragment {

    @InjectView(R.id.main_recyclerview)
    RecyclerView mRecyclerView;

    @InjectView(R.id.main_fab)
    ImageButton mFabButton;

    private MainRecyclerAdapter mAdapter;
    private List<Food> foods;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        EventBus.getDefault().register(this);
        initRecycler();
        initButtonClick();
        initNetworkCall();
        return view;
    }

    private void initButtonClick() {
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initNetworkCall();
            }
        });
    }

    private void initNetworkCall() {
        new GetAllFood().execute();
    }

    private void initRecycler() {
        Food food = new Food();
        food.setId(1);
        food.setName("Pizza");
        foods = new ArrayList<>();
        foods.add(food);
        mAdapter = new MainRecyclerAdapter(getActivity(), foods);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onEvent(GetAllFood.Event event){
        foods.clear();
        foods.addAll(event.getFoods());
        mAdapter.notifyDataSetChanged();
    }

    public void onEvent(GetUserGreets.Event event){
        Toast.makeText(getActivity(), event.email, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

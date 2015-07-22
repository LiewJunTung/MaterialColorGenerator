package org.pandawarrior.foodalbum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jt.myapplication.backend.foodListApi.model.Food;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jt on 4/26/15.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    Context context;
    List<Food> foods;

    public MainRecyclerAdapter(Context context, List<Food> foods) {
        this.context = context;
        this.foods = foods;
    }

    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_main_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainRecyclerAdapter.ViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.mFoodName.setText(food.getName());
        PicassoUtils.getPicasso(context)
                .load(food.getPicture())
                .into(holder.mFoodPicture);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.food_name)
        TextView mFoodName;

        @InjectView(R.id.food_picture)
        ImageView mFoodPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        //    mFoodName = (TextView) itemView.findViewById(R.id.food_name);
        }
    }
}

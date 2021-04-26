package com.example.blooddonorapplication.Activities.onBoardScreen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.blooddonorapplication.Activities.MainActivity;
import com.example.blooddonorapplication.R;

public class SlideViewPagerAdapter extends PagerAdapter {

    Context context;

    public SlideViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_screen, container, false);

        ImageView logo = view.findViewById(R.id.logo);

        ImageView ind1 = view.findViewById(R.id.indi1);
        ImageView ind2 = view.findViewById(R.id.indi2);
        ImageView ind3 = view.findViewById(R.id.indi3);

        TextView title=view.findViewById(R.id.boarding_title);
        TextView description=view.findViewById(R.id.boarding_des_one);

        ImageView next=view.findViewById(R.id.onboard_next);
        ImageView back=view.findViewById(R.id.onboard_back);

        Button getstarted=view.findViewById(R.id.getstartedbtn);

        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnBoardingScreen.viewPager.setCurrentItem(position+1);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnBoardingScreen.viewPager.setCurrentItem(position-1);
            }
        });

        switch (position){
            case 0:
                logo.setImageResource(R.drawable.on_board_one_image);
                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);

                title.setText(R.string.donor);
                description.setText(R.string.blood_description);
                back.setVisibility(View.GONE);
                break;

            case 1:
                logo.setImageResource(R.drawable.on_board_two_image);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);

                title.setText(R.string.search_title);
                description.setText(R.string.search_description);
                back.setVisibility(View.VISIBLE);
                break;

            case 2:
                logo.setImageResource(R.drawable.on_board_three_image);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);

                title.setText(R.string.save_life);
                description.setText(R.string.inspiration_description);
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);
                break;

        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

package com.plcoding.instagramui.saveplace.introActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plcoding.instagramui.saveplace.R;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<ScreenItem> mListScreen ;
    public IntroViewPagerAdapter(Context mcontext, List<ScreenItem> mListscreen) {
        mContext = mcontext;
        mListScreen = mListscreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View introScreen  = inflater.inflate(R.layout.intro_screen, null);
        ImageView imgSlide =introScreen.findViewById(R.id.intro_img);
        TextView title = introScreen.findViewById(R.id.intro_title);
        TextView description = introScreen.findViewById(R.id.intro_description);
        title.setText(mListScreen.get(position).Title);
        description.setText(mListScreen.get(position).Description);
        imgSlide.setImageResource(mListScreen.get(position).ScreenImg);

        container.addView(introScreen);

        return introScreen;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }


}

package com.plcoding.instagramui.saveplace.fragment.intro;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.plcoding.instagramui.saveplace.R;
import com.plcoding.instagramui.saveplace.introActivity.ScreenItem;

import java.util.List;

public class CallIntroViewPagerAdapter extends PagerAdapter {

    private  Context mContext;
    private List<ScreenItem> mListScreen;
    public boolean mode ;
    private SwitchCompat Switch ;
    private View  mCurrentView;


    public CallIntroViewPagerAdapter(SwitchCompat mSwitch, Context mcontext, List<ScreenItem> mListscreen){
        mContext = mcontext;
        Switch = mSwitch;
        mode =true;
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

        mode = Switch.isChecked()==true;
        if(mode){
            title.setTextColor(Color.parseColor("#000000"));
            description.setTextColor(Color.parseColor("#000000"));
        }else{
            title.setTextColor(Color.parseColor("#ffffff"));
            description.setTextColor(Color.parseColor("#ffffff"));
        }

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

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentView =(View)object;
    }
}

package ito.daniele.alcoolgasolina.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import ito.daniele.alcoolgasolina.R;
import layout.CarrosFragment;
import layout.MainFragment;
import layout.ViagemFragment;

/**
 * Created by Daniele on 24/11/2016.
 */
public class TabsAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private String[] titles;
    private int heightIcon;

    public TabsAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);

        titles = new String[]{
                context.getString(R.string.fragment_main_title),
//                context.getString(R.string.fragment_viagem_title),
                context.getString(R.string.fragment_carros_title)
        };

        this.context = context;
        double scale = context.getResources().getDisplayMetrics().density;
        heightIcon = (int) (24 * scale + 0.5f);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0) {
            fragment = new MainFragment();
//        } else if (position == 1) {
//            fragment = new ViagemFragment();
        } else if (position == 1) {
            fragment = new CarrosFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
//        Drawable drawable = context.getResources().getDrawable(icons[position]);
//        drawable.setBounds(0, 0, heightIcon, heightIcon);
//        ImageSpan imageSpan = new ImageSpan(drawable);
//        SpannableString spannableString = new SpannableString(" ");
//        spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return (spannableString);
        return (titles[position]);
    }
}
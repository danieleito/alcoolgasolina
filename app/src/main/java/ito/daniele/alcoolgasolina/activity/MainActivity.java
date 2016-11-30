package ito.daniele.alcoolgasolina.activity;

/**
 * Created by Daniele on 24/11/2016.
 */
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ito.daniele.alcoolgasolina.R;
import ito.daniele.alcoolgasolina.adapter.TabsAdapter;
import ito.daniele.alcoolgasolina.tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarAtributos();
        configurarSlidingTab();
    }

    /**
     * Configura Abas.
     */
    private void configurarSlidingTab() {
        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        //mSlidingTabLayout.setBackgroundColor(Tema.getPrimaryDarkColor(this));
//        mSlidingTabLayout.setSelectedIndicatorColors(Tema.getPrimaryColor(this));
        mSlidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tv_tab);
        mSlidingTabLayout.setViewPager(mViewPager);
//        mSlidingTabLayout.setOnPageChangeListener(onPageChangeListener);
    }

    private void iniciarAtributos() {
        mViewPager = (ViewPager) findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), this));
    }

}

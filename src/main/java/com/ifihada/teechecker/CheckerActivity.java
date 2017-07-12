package com.ifihada.teechecker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CheckerActivity extends Activity implements ActionBar.TabListener
{

  /**
   * The {@link android.support.v4.view.PagerAdapter} that will provide
   * fragments for each of the sections. We use a
   * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
   * keep every loaded fragment in memory. If this becomes too memory intensive,
   * it may be best to switch to a
   * {@link android.support.v4.app.FragmentStatePagerAdapter}.
   */
  SectionsPagerAdapter mSectionsPagerAdapter;

  /**
   * The {@link ViewPager} that will host the section contents.
   */
  ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_checker);

    // Set up the action bar.
    final ActionBar actionBar = getActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    // Create the adapter that will return a fragment for each of the three
    // primary sections of the app.
    mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.pager);
    mViewPager.setOffscreenPageLimit(16);
    mViewPager.setAdapter(mSectionsPagerAdapter);

    // When swiping between different sections, select the corresponding
    // tab. We can also use ActionBar.Tab#select() to do this if we have
    // a reference to the Tab.
    mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
    {
      @Override
      public void onPageSelected(int position)
      {
        actionBar.setSelectedNavigationItem(position);
      }
    });

    // For each of the sections in the app, add a tab to the action bar.
    for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++)
    {
      // Create a tab with text corresponding to the page title defined by
      // the adapter. Also specify this Activity object, which implements
      // the TabListener interface, as the callback (listener) for when
      // this tab is selected.
      actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
    }
  }

  @Override
  public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
  {
    // When the given tab is selected, switch to the corresponding page in
    // the ViewPager.
    mViewPager.setCurrentItem(tab.getPosition());
  }

  @Override
  public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
  {
  }

  @Override
  public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
  {
  }

  @Override
  public boolean onCreateOptionsMenu(Menu m)
  {
    super.onCreateOptionsMenu(m);
    getMenuInflater().inflate(R.menu.main, m);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem mi)
  {
    if (mi != null && mi.getItemId() == R.id.menu_send_results)
    {
      CheckerResults.send(this);
      return true;
    }

    return false;
  }

  /**
   * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one
   * of the sections/tabs/pages.
   */
  public class SectionsPagerAdapter extends FragmentPagerAdapter
  {

    public SectionsPagerAdapter(FragmentManager fm)
    {
      super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
      switch (position)
      {
      case 0:
        return new PlatformSectionFragment();
      case 1:
        return new MobicoreSectionFragment();
      case 2:
        return new QSeeSectionFragment();
      case 3:
        return new TFSectionFragment();
      case 4:
        return new TLKSectionFragment();
      default:
        return null;
      }
    }

    @Override
    public int getCount()
    {
      return 5;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
      switch (position)
      {
      case 0:
        return getString(R.string.title_platform);
      case 1:
        return getString(R.string.title_mobicore);
      case 2:
        return getString(R.string.title_qsee);
      case 3:
        return getString(R.string.title_tf);
      case 4:
        return "Trusted Little Kernel";
      }
      return null;
    }
  }

  /**
   * A dummy fragment representing a section of the app, but that simply
   * displays dummy text.
   */
  public static class DummySectionFragment extends Fragment
  {
    /**
     * The fragment argument representing the section number for this fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    public DummySectionFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
      View rootView = inflater.inflate(R.layout.fragment_checker_dummy, container, false);
      TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
      dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
      return rootView;
    }
  }

}

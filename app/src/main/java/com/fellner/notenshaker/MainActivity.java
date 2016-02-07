package com.fellner.notenshaker;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.fellner.fellner.notenshaker.R;

import java.util.ArrayList;


public class MainActivity extends Activity {

    static ArrayList<String> marks;
    static SimpleArrayAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ImageView img = (ImageView) rootView.findViewById(R.id.imageView);
            img.setImageResource(R.drawable.explenation);
            rootView.setBackgroundColor(Color.BLACK);
            marks = new ArrayList<String>();

            itemAdapter = new SimpleArrayAdapter(this.getActivity(), R.layout.listview_item, marks);

            ListView lv = (ListView)rootView.findViewById(R.id.listview);
            lv.setAdapter(itemAdapter);

            return rootView;
        }
    }

    public void notenShaker(View view) {
        final ImageView img = (ImageView) view.findViewById(R.id.imageView);
        int random = (int) Math.round(Math.random() * 4) + 1;

        int diceID = getResources().getIdentifier("dice"+random, "drawable", getPackageName());
        img.setImageResource(diceID);
        marks.add(random + "");

        if(marks.size() == 5){
            float sum = 0;
            for (String mark : marks) {
                sum += Integer.parseInt(mark);
            }
            float average = sum / marks.size();

            marks.add("Average: " + Math.floor(average * 1000) / 1000);
        }else if(marks.size()==7){
            marks.clear();
        }
        itemAdapter.notifyDataSetChanged();
    }
}

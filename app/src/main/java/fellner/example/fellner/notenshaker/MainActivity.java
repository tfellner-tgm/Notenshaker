package fellner.example.fellner.notenshaker;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {
    static ArrayList<Integer> marks;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            img.setImageResource(R.drawable.notenshaker);
            rootView.setBackgroundColor(Color.BLACK);
            marks = new ArrayList<Integer>();
            return rootView;
        }
    }

    public void notenShaker(View view) {
        if(marks.size() == 5){
            float sum = 0;
            for (Integer mark : marks) {
                sum += mark;
            }
            float average = sum / marks.size();

            String [] itemText = new String[marks.size()+1]; //Listentext setzen
            for (int i = 0; i < marks.size(); i++) {
                itemText[i] = "" + marks.get(i);
            }
            itemText[itemText.length-1] = "Average: " + Math.floor(average*100)/100;

            SimpleArrayAdapter itemAdapter;
            itemAdapter = new SimpleArrayAdapter(this, R.layout.listview_item, itemText); //Initialisieren des SimpleArrayAdapters

            final ListView lv = (ListView)this.findViewById(R.id.listview); //ListView bekommen
            lv.setAdapter(itemAdapter); //setzen des adapters

            marks.clear();
        }else{
            final ImageView img = (ImageView) view.findViewById(R.id.imageView);
            int random = (int) Math.round(Math.random() * 4) + 1;

            int diceID = getResources().getIdentifier("dice"+random, "drawable", getPackageName());
            img.setImageResource(diceID);
            marks.add(random);
        }
    }
}

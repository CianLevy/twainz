package com.example.twainz;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Vector;

public class stationList extends Fragment  {
    private ListView mListView;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_list, container, false);

        mListView = rootView.findViewById(R.id.listView);

        final trainFetcher tf = new trainFetcher(getContext());

        ArrayList<String> list = new ArrayList<String>(tf.getStationList());

        StringAdapter adapter = new StringAdapter(rootView.getContext(), list);

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String search = tf.getStationList().get(Integer.valueOf(position));

                android.support.v4.app.FragmentManager childManager = getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = childManager.beginTransaction();   //Begin the fragment change
                Fragment fragment = new stationInformationActivity();   //Initialise the new fragment

                Bundle fragmentData = new Bundle(); //This bundle is used to pass the position of the selected train to the linerun fragment
                fragmentData.putString(((stationInformationActivity) fragment).DATA_RECEIVE, search);
                fragment.setArguments(fragmentData);

                fragmentTransaction.replace(R.id.listConstraintLayout, fragment);   //Replace listConstraintLayout with the new fragment
                fragmentTransaction.addToBackStack(null);   //Add the previous fragment to the stack so the back button works
                fragmentTransaction.commit();   //Complete the fragment transaction
            }
        });

        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && isAdded()) {
            ((MainActivity)getActivity()).setActionBarTitle(getResources().getString(R.string.app_name));
            Log.d("D", "Station list called");
        }
    }


}
//Custom adapter class to ensure new buttons are uniquely tagged so UI callbacks can be processed correctly
class StringAdapter extends ArrayAdapter<String> {

    public StringAdapter(Context context, ArrayList<String> stations) {
        super(context, 0, stations);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String station = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_view, parent, false);
        }

        TextView t = convertView.findViewById(R.id.stationButton);
        t.setText(station);
        t.setTag(position); //Tag the button with it's position in the list

        return convertView;

    }

}
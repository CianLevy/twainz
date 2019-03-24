package com.example.twainz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

public class JourneyPlanner extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View journeyView = inflater.inflate(R.layout.journey_planner, container, false);

        TextView textDest = journeyView.findViewById(R.id.textView_dest);
        TextView textOrig = journeyView.findViewById(R.id.textView_orig);
        ImageButton buttonDir = journeyView.findViewById(R.id.changeDirButton);

        Animation shake = AnimationUtils.loadAnimation(journeyView.getContext(), R.anim.shake);

        // Fragment stuff

//        android.support.v4.app.FragmentManager childManager = getFragmentManager();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = childManager.beginTransaction();   // Begin the fragment change
//        Fragment fragment = new stationInformationActivity();                                               // Initialise the new fragment
//
//        Bundle fragmentData = new Bundle();                                 // Bundle passes position of selected train to Linerun fragment
//        fragmentData.putString(((stationInformationActivity) fragment).DATA_RECEIVE, "Blackrock");
//        fragment.setArguments(fragmentData);
//
//        fragmentTransaction.replace(R.id.listConstraintLayout, fragment);   // Replace listConstraintLayout with the new fragment
//        fragmentTransaction.addToBackStack(null);                           // Add the previous fragment to the stack so the back button works
//        fragmentTransaction.commit();                                       // Complete the fragment transaction

        // End of fragment stuff

        buttonDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);
                CharSequence temp = textDest.getText();
                textDest.setText(textOrig.getText());
                textOrig.setText(temp);
            }
        });

        return journeyView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

}

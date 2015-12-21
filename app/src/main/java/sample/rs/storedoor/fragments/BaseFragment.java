package sample.rs.storedoor.fragments;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sample.rs.storedoor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    public enum FragmentTransactionType {
        ADD, REPLACE, ADD_TO_BACK_STACK_AND_ADD, ADD_TO_BACK_STACK_AND_REPLACE, POP_BACK_STACK_AND_REPLACE, CLEAR_BACK_STACK_AND_REPLACE
    }


    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }


}

package com.example.foodnow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoResultsFoundFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoResultsFoundFragment extends Fragment {

    public NoResultsFoundFragment() {
        // Required empty public constructor
    }

    public static NoResultsFoundFragment newInstance() {
        return new NoResultsFoundFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_results_found, container, false);
    }
}
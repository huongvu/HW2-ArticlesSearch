package com.example.huongvu.nytarticlesearch.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.huongvu.nytarticlesearch.R;
import com.example.huongvu.nytarticlesearch.interfaces.AdvanceSettingListener;
import com.example.huongvu.nytarticlesearch.models.ArticleSearch;

/**
 * Created by HUONGVU on 3/21/2016.
 */
public class AdvanceSearchSettingFragment extends android.support.v4.app.DialogFragment {

    private EditText mEditText;
    private ArticleSearch dataSetting;

    public AdvanceSearchSettingFragment() {

    }

    public static AdvanceSearchSettingFragment newInstance(String title) {

        AdvanceSearchSettingFragment frag = new AdvanceSearchSettingFragment();

        Bundle args = new Bundle();

        args.putString("title", title);

        frag.setArguments(args);

        return frag;

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.advance_search_setting, container);

    }

    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Get field from view

        mEditText = (EditText) view.findViewById(R.id.etDatePicker);

        mEditText.requestFocus();

        getDialog().getWindow().setSoftInputMode(

                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        Button btnSave = (Button) view.findViewById(R.id.btSave);
        final Spinner spSort = (Spinner) view.findViewById(R.id.spSort);

        dataSetting = new ArticleSearch();

        btnSave.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              dataSetting.setSortOder(spSort.getSelectedItem().toString());
                                              AdvanceSettingListener listener = (AdvanceSettingListener) getActivity();
                                              dataSetting.setNewDeskValues("Fashion & Style");
                                              listener.onFinishSetting(dataSetting);
                                              dismiss();

                                          }
                                      }
        );

    }


}

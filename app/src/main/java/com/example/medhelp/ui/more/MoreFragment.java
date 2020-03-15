package com.example.medhelp.ui.more;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.medhelp.R;

import java.util.zip.Inflater;

public class MoreFragment extends Fragment implements View.OnClickListener {
    Button upld, refill, help;
    // private MoreViewModel moreViewModel;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
    /*    moreViewModel =
                ViewModelProviders.of(this).get(MoreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_more, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        moreViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }*/
        View root = inflater.inflate(R.layout.fragment_more, container, false);
        upld = root.findViewById(R.id.upload_bt);
        refill = root.findViewById(R.id.refill);
        help= root.findViewById(R.id.help);
        upld.setOnClickListener((View.OnClickListener) this);
        refill.setOnClickListener((View.OnClickListener) this);
        help.setOnClickListener((View.OnClickListener) this);
    return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload_bt:
                Intent intent = new Intent(getActivity(), Upload.class);
                getActivity().startActivity(intent);
                break;
            case R.id.refill:
                Intent in = new Intent(getActivity(), Refill.class);
                getActivity().startActivity(in);
                break;
          /*  case R.id.help:
                Intent i = new Intent(getActivity(), Upload.class);
                getActivity().startActivity(i);
                break;*/
            default:
                break;
    }
}}

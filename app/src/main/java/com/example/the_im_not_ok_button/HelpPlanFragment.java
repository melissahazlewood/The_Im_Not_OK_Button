package com.example.the_im_not_ok_button;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.the_im_not_ok_button.databinding.FragmentHelpPlanBinding;

public class HelpPlanFragment extends Fragment {

    private FragmentHelpPlanBinding binding;

    private EditText recipientName_txtBox;
    private EditText textMessage_txtBox;
    private EditText recipientPhoneNumber_txtBox;

    private String recipientName;
    private String recipientPhoneNumber; //TODO change to a list for multiple recipients? + TODO: get phone number(s) from help plan/tutorial
    private String textMessage; //TODO get text string from help plan/tutorial

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentHelpPlanBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSaveHelpPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: save the message (if changed)


                NavHostFragment.findNavController(HelpPlanFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
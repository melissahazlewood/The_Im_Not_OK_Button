package com.example.the_im_not_ok_button;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.the_im_not_ok_button.databinding.FragmentMainButtonsBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainButtonsFragment extends Fragment {

    private FragmentMainButtonsBinding binding;
    private String recipientName;
    private String recipientPhoneNumber; //TODO change to a list for multiple recipients? + TODO: get phone number(s) from help plan/tutorial
    private String textMessage; //TODO get text string from help plan/tutorial

    private EditText recipientName_txtBox;
    private EditText textMessage_txtBox;
    private EditText recipientPhoneNumber_txtBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentMainButtonsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonNotOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "(Texting support person(s)...", Snackbar.LENGTH_LONG).show();

                if (ContextCompat.checkSelfPermission(
                        getContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    // You can use the API that requires the permission.
                    //TODO: performAction();


                    recipientName_txtBox = view.findViewById(R.id.name_txtbox);
//                    recipientPhoneNumber_txtBox = view.findViewById(R.id.phone_number_txtbox);
                    textMessage_txtBox = view.findViewById(R.id.message_txtbox);

                    recipientName = (recipientName_txtBox == null)? "Melissa" : recipientName_txtBox.getText().toString();
//                    recipientPhoneNumber = (recipientPhoneNumber_txtBox == null)? "+15628812240" : recipientPhoneNumber_txtBox.getText().toString().trim(); //TODO: write fxn to check for +1 or add it if not there
                    //TODO: make the message signature always active/used even when message is custom
                    textMessage = (textMessage_txtBox == null)? (recipientName + ", " + "I'm not ok. Please reach out to me. \n\n-- Sent via the Not OK Button app") : textMessage_txtBox.getText().toString();
                    System.out.println(recipientName);
//                    System.out.println(recipientPhoneNumber);
                    System.out.println(textMessage);

                    recipientPhoneNumber = "+15628812240";

                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage(recipientPhoneNumber, null, textMessage, null, null);
                    Toast.makeText(view.getContext(), "Sent.", Toast.LENGTH_SHORT).show();
                }
                // TODO:
                else if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.
                    //TODO: showInContextUI(...);
                    Snackbar.make(view, "Cannot send text message without permission.", Snackbar.LENGTH_LONG).show();
                }
                else {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestPermissionLauncher.launch(Manifest.permission.SEND_SMS);
                }
            }
        });

        binding.buttonHelpPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainButtonsFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    // Register the permissions callback, which handles the user's response to the
    // system permissions dialog. Save the return value, an instance of
    // ActivityResultLauncher, as an instance variable.
    ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    //TODO?
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    //TODO
                }
            });
}
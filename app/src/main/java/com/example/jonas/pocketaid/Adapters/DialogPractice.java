package com.example.jonas.pocketaid.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonas.pocketaid.PracticeFragments.InteractivePracticeMaterialsFragment;
import com.example.jonas.pocketaid.R;

/**
 * Created by alec on 2/16/17.
 */

public class DialogPractice extends DialogFragment {

    private TextView dialogTV;
    private ImageView dialogIV;
    private Button dialogButton;
    private Dialog dialog;

    public void showDialog(Activity activity, String correctOrNot) {

        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_dialog);

        dialogTV = (TextView) dialog.findViewById(R.id.dialog_textView);
        dialogIV = (ImageView) dialog.findViewById(R.id.dialog_imageView);
        dialogButton = (Button) dialog.findViewById(R.id.dialog_button);

        if(correctOrNot.toLowerCase().equals("wrong")) {
            dialogIV.setImageResource(R.drawable.ic_xmark);
            dialogTV.setText("Sorry, wrong answer. :(");
        } else if(correctOrNot.toLowerCase().equals("correct")){
            dialogIV.setImageResource(R.drawable.ic_checkmark);
            dialogTV.setText("May Tama ka!!");
        }

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}

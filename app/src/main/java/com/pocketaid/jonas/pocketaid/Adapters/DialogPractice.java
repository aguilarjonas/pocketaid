package com.pocketaid.jonas.pocketaid.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pocketaid.jonas.pocketaid.R;

/**
 * Created by alec on 2/16/17.
 */

public class DialogPractice extends DialogFragment {

    private TextView dialogTV;
    private ImageView dialogIV;
    private Button dialogButton;
    private Dialog dialog;

    public void showDialog(Activity activity, final String correctOrNot) {

        createNewDialog(activity);
        initializeDialogViews();
        setDialogViews(correctOrNot, activity);
        dialogButtonListener();

        dialog.show();
    }

    public void createNewDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_dialog);
    }

    public void initializeDialogViews() {
        dialogTV = (TextView) dialog.findViewById(R.id.dialog_textView);
        dialogIV = (ImageView) dialog.findViewById(R.id.dialog_imageView);
        dialogButton = (Button) dialog.findViewById(R.id.dialog_button);
    }

    public void setDialogViews(String correctOrNot, Activity activity) {
        if(correctOrNot.toLowerCase().equals("wrong")) {
            dialogIV.setImageResource(R.drawable.xmark_2);
            dialogIV.setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.dialogWrongBackground));
            dialogTV.setText("Incorrect answer");
        } else if(correctOrNot.toLowerCase().equals("correct")){
            dialogIV.setImageResource(R.drawable.checkmark_2);
            dialogIV.setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.dialogRightBackground));
            dialogTV.setText("Correct answer");
        } else if(correctOrNot.toLowerCase().equals("tama")){
            dialogIV.setImageResource(R.drawable.checkmark_2);
            dialogIV.setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.dialogRightBackground));
            dialogTV.setText("Tamang sagot");
        } else if(correctOrNot.toLowerCase().equals("mali")){
            dialogIV.setImageResource(R.drawable.xmark_2);
            dialogIV.setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.dialogWrongBackground));
            dialogTV.setText("Maling sagot");
        }
    }

    public void dialogButtonListener() {
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}

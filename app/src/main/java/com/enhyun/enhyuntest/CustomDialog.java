package com.enhyun.enhyuntest;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;



public class CustomDialog {

    private Context context;

    public CustomDialog(Context context) {
        this.context = context;
    }


    public void callFunction(final  Button button ) {


        final Dialog dlg = new Dialog(context);


        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);


        dlg.setContentView(R.layout.custom_dialog);

        Window window = dlg.getWindow();
        window.setLayout(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);

        dlg.show();


        final Button okButton = (Button) dlg.findViewById(R.id.okButton);
        final Button cancelButton = (Button) dlg.findViewById(R.id.cancelButton);
        final RadioGroup rGroup1 = (RadioGroup) dlg.findViewById(R.id.rGroup1);
        final RadioButton regular = (RadioButton) dlg.findViewById(R.id.regular);
        final RadioButton large = (RadioButton) dlg.findViewById(R.id.large);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch(rGroup1.getCheckedRadioButtonId()) {
                    case R.id.regular:
                        button.setText("regular");
                        break;
                    case R.id.large:
                        button.setText("large");
                        break;
                }


                dlg.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dlg.dismiss();
            }
        });

    }


}

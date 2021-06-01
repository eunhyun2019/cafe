package com.enhyun.enhyuntest;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;



public class CupChoiceDialog {

    private Context context;

    public CupChoiceDialog(Context context) {
        this.context = context;
    }


    public void cupChoiceFunction (final  Button button ) {


        final Dialog dlg = new Dialog(context);


        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);


        dlg.setContentView(R.layout.cup_choice_dialog);

        Window window = dlg.getWindow();
        window.setLayout(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);

        dlg.show();


        final Button cup_ok_btn = (Button) dlg.findViewById(R.id.cup_ok_btn);
        final Button cup_cancel_btn = (Button) dlg.findViewById(R.id.cup_cancel_btn);
        final RadioGroup cup_rGroup = (RadioGroup) dlg.findViewById(R.id.cup_rGroup);
        final RadioButton disposal_cup = (RadioButton) dlg.findViewById(R.id.disposal_cup);
        final RadioButton mug_cup = (RadioButton) dlg.findViewById(R.id.mug_cup);


        cup_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch(cup_rGroup.getCheckedRadioButtonId()) {
                    case R.id.disposal_cup:
                        button.setText("일회용컵");
                        break;
                    case R.id.mug_cup:
                        button.setText("매장용컵");
                        break;
                }


                dlg.dismiss();
            }
        });

        cup_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                dlg.dismiss();
            }
        });

    }


}

package com.enhyun.enhyuntest;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class OptionDialog {

    private Context context;

    int count = 0;

    TextView add_short;


    public OptionDialog(Context context) {
        this.context = context;
    }


    public void optionChoiceFunction (final Button button ) {



        final Dialog dlg = new Dialog(context);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);


        dlg.setContentView(R.layout.option_dialog);

        Window window = dlg.getWindow();
        window.setLayout(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);

        dlg.show();


        final Button option_ok_btn = (Button) dlg.findViewById(R.id.option_ok_btn);
        final Button option_cancel_btn = (Button) dlg.findViewById(R.id.option_cancel_btn);
        final TextView shot = (TextView) dlg.findViewById(R.id.shot);
        final TextView add_shot = (TextView) dlg.findViewById(R.id.add_shot);
        final TextView add_whipping = (TextView) dlg.findViewById(R.id.add_whipping);
        final TextView add_caramel = (TextView) dlg.findViewById(R.id.add_caramel);
        final TextView add_hazelnut = (TextView) dlg.findViewById(R.id.add_hazelnut);
        final TextView add_vanilla = (TextView) dlg.findViewById(R.id.add_vanilla);
        final TextView add_bubble = (TextView) dlg.findViewById(R.id.add_bubble);
        final ImageButton increment = (ImageButton) dlg.findViewById(R.id.increment);
        final ImageButton decrement = (ImageButton) dlg.findViewById(R.id.decrement);




        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                add_shot.setText(""+count);
            }
        });


        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count <= 0) count = 0;
                else count--;
                add_shot.setText("" + count);
            }

        });




        option_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dlg.dismiss();
            }
        });


        option_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                dlg.dismiss();
            }
        });

    }






}








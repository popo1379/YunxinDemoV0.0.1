package search;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.palmintelligence.administrator.yunxindemov001.R;

/**
 * Created by Administrator on 2017/1/22 0022.
 */

//暂时不用
public class SearchBar extends LinearLayout {

    public SearchBar(final Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        LayoutInflater.from(context).inflate(R.layout.cpt_search,this);

        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.ll_constact_serach);
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                    //
            }
        });

    }


}

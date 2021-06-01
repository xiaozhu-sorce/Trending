package com.bignerdranch.android.trending.View.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.trending.R;

public class TitleBar extends ConstraintLayout {

    private AppCompatSpinner menu;

    public SpinnerAdapter adapter;

    private ConstraintLayout mConstraintLayout;

    public TitleBar(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.titlebar,this,true);
        menu = findViewById(R.id.menu);
    }

    public void setMenu(Context context){
        adapter = SpinnerAdapter.createFromResource(context,R.array.titlebar_screening,R.layout.spinner);
        adapter.setDropDownViewResource(R.layout.spinner_down);
        menu.setBackgroundColor(0x0);
        menu.setAdapter(adapter);
        menu.setDropDownVerticalOffset(Math.round(getResources().getDisplayMetrics().density * 25));

    }

    public void setOptionSelectListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        menu.setOnItemSelectedListener(onItemSelectedListener);
    }

    public static class SpinnerAdapter<T> extends ArrayAdapter<T> {

        private int selectedPosition;

        public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
        }

        public SpinnerAdapter(@NonNull Context context, int resource, @NonNull T[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            TextView textView = (TextView)view;
            if ( selectedPosition == position ) {
                textView.setTextColor(0xff373741);
                textView.getPaint().setFakeBoldText(true);
            } else{
                textView.setTextColor(0xff6d6d6d);
                textView.getPaint().setFakeBoldText(false);
            }
            return view;
        }

        public static @NonNull SpinnerAdapter<CharSequence> createFromResource(
                @NonNull Context context, @ArrayRes int textArrayResId, @LayoutRes int textViewResId) {
            final CharSequence[] strings = context.getResources().getTextArray(textArrayResId);
            return new SpinnerAdapter<>(context, textViewResId, strings);
        }

    }
}

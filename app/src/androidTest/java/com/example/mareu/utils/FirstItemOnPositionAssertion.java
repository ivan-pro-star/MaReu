package com.example.mareu.utils;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mareu.R;

import org.junit.Assert;

public class FirstItemOnPositionAssertion implements ViewAssertion {
        private final int position ;
        private final String valueItem ;

        public static FirstItemOnPositionAssertion withItemPosition(int position, String valueItem) {
            return new FirstItemOnPositionAssertion(position, valueItem);
        }
        private FirstItemOnPositionAssertion(int position, String valueItem) {
            this.position = position;
            this.valueItem = valueItem;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            TextView firstItemView =  recyclerView.findViewHolderForAdapterPosition(position).
                    itemView.
                    findViewById(R.id.id_item_first);
            String itemFirst = firstItemView.getText().toString();
            Assert.assertEquals(valueItem, itemFirst);
        }
    }
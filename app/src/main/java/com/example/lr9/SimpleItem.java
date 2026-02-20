package com.example.lr9;

import android.view.View;
import android.widget.AdapterView;

public class SimpleItem implements AdapterView.OnItemSelectedListener {
    public interface OnPos { void onSelected(int position); }
    private final OnPos onPos;

    public SimpleItem(OnPos onPos) {
        this.onPos = onPos;
    }

    @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        onPos.onSelected(position);
    }
    @Override public void onNothingSelected(AdapterView<?> parent) { }
}
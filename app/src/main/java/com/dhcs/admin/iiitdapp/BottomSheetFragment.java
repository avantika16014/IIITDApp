package com.dhcs.admin.iiitdapp;

import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;


public class BottomSheetFragment extends BottomSheetDialogFragment {

    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.bottom_sheet, null);
        dialog.setContentView(contentView);
    }
}

package com.david0926.mbit.ui.dialog;

import android.app.AlertDialog;

import androidx.fragment.app.FragmentActivity;

public class WorkingDialog {
    public static void working(FragmentActivity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("준비중인 기능").setMessage("앗, 이 기능은 아직 준비중이에요! 빠른 시일 내에 추가될 예정이니, 많은 기대 부탁드립니다!");
        builder.setPositiveButton("확인", (dialog, which) -> {
        }).show();
    }
}

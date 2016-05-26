package com.ac.anonymousmessenger.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by iconflux-android on 12/4/2015.
 */
public class ShareCopyUtils {

    public static void sharebutton(Context context, StringBuilder builder) {

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |Intent.FLAG_ACTIVITY_NEW_TASK|
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT,
                "Anonymous Messenger-");
        sharingIntent
                .putExtra(Intent.EXTRA_TEXT, builder.toString());
        context.startActivity(Intent.createChooser(sharingIntent, "Share To.."));

    }

    public static void Copyclipbord(Context context, StringBuilder builder) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", builder.toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Copy Text", Toast.LENGTH_SHORT).show();
        Log.d("copy buttn", "clicked copy btn");
    }



}

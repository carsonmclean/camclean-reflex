package com.carsonmclean.camclean_reflex.Utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.Toast;

/**
 * Created by carsonmclean on 3/10/15.
 */

// Create messages in the Utilities and pass them as object to Activities to be seen
// Also allow easy creation of dialogs without having to repeat setup code
public class MessagePasser {
    private Activity activity;

    // Constructor
    // http://stackoverflow.com/questions/3572463/what-is-context-in-android
    // When calling Constructor in an Activity class, pass 'this' as argument to get Context
    public MessagePasser(Activity activity) {
        this.activity = activity;
    }

    // http://stackoverflow.com/questions/2115758/how-to-display-alert-dialog-in-android
    public void createDialog(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    // http://developer.android.com/guide/topics/ui/notifiers/toasts.html
    public void createToast(String message) {
        Toast toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}

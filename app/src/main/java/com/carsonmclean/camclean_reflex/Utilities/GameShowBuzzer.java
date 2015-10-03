package com.carsonmclean.camclean_reflex.Utilities;

import android.widget.Button;

/**
 * Created by carsonmclean on 3/10/15.
 */
public class GameShowBuzzer {
    MessagePasser messagePasser;

    public GameShowBuzzer(MessagePasser messagePasser) {
        this.messagePasser = messagePasser;
    }

    public void onClick(String player) {
        messagePasser.createDialog("",player + " clicked first");
    }

}

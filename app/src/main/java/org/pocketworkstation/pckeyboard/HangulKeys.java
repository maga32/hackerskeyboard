package org.pocketworkstation.pckeyboard;

import android.content.Context;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Map;

public class HangulKeys {
    private static Map<String, Map<String, String>> keys;
    private static Map<String, String> del;

    public static void load(Context context) {
        if (keys != null) return;

        try {
            InputStream is = context.getAssets().open("HangulKeys.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            keys = (Map<String, Map<String, String>>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            keys = null;
        }

        try {
            InputStream is = context.getAssets().open("HangulKeysDelete.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            del = (Map<String, String>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            del = null;
        }
    }

    public static String getNextChar(String prevChar, String addedChar) {
        if (keys == null || keys.get(addedChar) == null) return null;
        return  keys.get(addedChar).get(prevChar);
    }

    public static String deleteChar(String prevChar) {
        if (del == null) return null;
        return del.get(prevChar);
    }
}

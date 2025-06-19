package org.pocketworkstation.pckeyboard;

import android.content.Context;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Map;

public class KoreanKeysFlat {
    private static Map<String, String> flat;
    private static Map<String, String> del;

    public static void load(Context context) {
        if (flat != null && del != null) return;

        try {
            InputStream is = context.getAssets().open("KoreanKeysFlat.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            flat = (Map<String, String>) ois.readObject();
            ois.close();

            is = context.getAssets().open("KoreanKeysDelete.bin");
            ois = new ObjectInputStream(is);
            del = (Map<String, String>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            flat = null;
            del = null;
        }
    }

    public static String getNextChar(String prevChar, String addedChar) {
        if (flat == null) return null;
        return flat.get(prevChar + "^" + addedChar);
    }

    public static String deleteChar(String prevChar) {
        return del.get(prevChar);
    }
}

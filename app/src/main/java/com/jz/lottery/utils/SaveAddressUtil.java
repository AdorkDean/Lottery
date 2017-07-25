package com.jz.lottery.utils;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SaveAddressUtil {
    private Context context;

    public SaveAddressUtil(Context context) {
        this.context = context;
    }

    private String readString() {
        try {
            return new String(InputStreamToByte(getClass().getResourceAsStream("/assets/address")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] InputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        while (true) {
            int ch = is.read();
            if (ch != -1) {
                bytestream.write(ch);
            } else {
                byte[] imgdata = bytestream.toByteArray();
                bytestream.close();
                return imgdata;
            }
        }
    }
}
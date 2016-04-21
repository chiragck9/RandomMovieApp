package com.ck.rscp.domain.utils;

import java.util.Random;

/**
 * Created by ckunder on 20-04-2016.
 */
public class RandomizerUtil {

    public static int randomize(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }
}

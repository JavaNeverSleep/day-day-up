package com.javaneversleep.daydayup.lombok;

import lombok.Data;

import java.util.Arrays;

@Data
@Deprecated
public class Tank {

    @MyGetter private int x, y;

    private boolean enemy;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Tank.class.getAnnotations()));
    }

}

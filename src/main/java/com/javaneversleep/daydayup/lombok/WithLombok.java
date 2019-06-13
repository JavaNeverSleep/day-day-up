package com.javaneversleep.daydayup.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class WithLombok {

    @Getter @Setter private String name;

    @Getter private final double price;

    @Data
    private static class Tank {
        private int x, y;
        private boolean enemy;
        private int speed;
        private int hp;
        private String direction = "South";
    }

    @Value
    private static class Missile {
        private final boolean enemy;
        private final int speed;
        private final String direction;
    }

    public static void main(String[] args) {
        val happy = true;
        var price = 19.99;
        log.info("Lombok Rocks!");
        log.info(new WithLombok(price).toString());
        log.info(new WithLombok("Nathanael", 39.99).toString());
        log.info(new Tank().toString());
    }

}

package com.javaneversleep.daydayup.lombok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class WithoutLombok {
    private static final Logger LOGGER = LoggerFactory.getLogger(WithoutLombok.class);

    private String name;

    private final double price;

    private static class Tank {
        private int x, y;
        private boolean enemy;
        private int speed;
        private int hp;
        private String direction = "South";

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tank tank = (Tank) o;
            return x == tank.x &&
                y == tank.y &&
                enemy == tank.enemy &&
                speed == tank.speed &&
                hp == tank.hp &&
                Objects.equals(direction, tank.direction);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, enemy, speed, hp, direction);
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public boolean isEnemy() {
            return enemy;
        }

        public void setEnemy(boolean enemy) {
            this.enemy = enemy;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public int getHp() {
            return hp;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Tank{" +
                "x=" + x +
                ", y=" + y +
                ", enemy=" + enemy +
                ", speed=" + speed +
                ", hp=" + hp +
                ", direction='" + direction + '\'' +
                '}';
        }
    }

    public WithoutLombok(double price) {
        this.price = price;
    }

    public WithoutLombok(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WithoutLombok that = (WithoutLombok) o;
        return Double.compare(that.price, price) == 0 &&
            Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "WithoutLombok{" +
            "name='" + name + '\'' +
            ", price=" + price +
            '}';
    }

    private static class Missile {
        private final boolean enemy;
        private final int speed;
        private final String direction;

        public Missile(boolean enemy, int speed, String direction) {
            this.enemy = enemy;
            this.speed = speed;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Missile missile = (Missile) o;
            return enemy == missile.enemy &&
                speed == missile.speed &&
                Objects.equals(direction, missile.direction);
        }

        @Override
        public int hashCode() {
            return Objects.hash(enemy, speed, direction);
        }

        @Override
        public String toString() {
            return "Missile{" +
                "enemy=" + enemy +
                ", speed=" + speed +
                ", direction='" + direction + '\'' +
                '}';
        }

        public boolean isEnemy() {
            return enemy;
        }

        public int getSpeed() {
            return speed;
        }

        public String getDirection() {
            return direction;
        }
    }

    public static void main(String[] args) {
        final boolean happy = true;
        var price = 19.99;
        LOGGER.info("It sucks without Lombok!");
        LOGGER.info(new WithoutLombok(price).toString());
        LOGGER.info(new WithoutLombok("Nathanael", 39.99).toString());
        LOGGER.info(new Tank().toString());
    }
}

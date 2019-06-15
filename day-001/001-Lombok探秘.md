# Lombok探秘

## 简介
痛快写Java，就这么简单！

## 用法
- val 等价于`final` boolean isEnemy = true;
- var 自JDK 10开始也已自带，撞车了
- @NonNull 省去`if (xxx != null)`
- @Cleanup 用处相对不大，毕竟已经有了try-with-resources
- @Getter/@Setter 好用！
- @ToString 好用！
- @EqualsAndHashCode 好用！
- @NoArgsConstructor、@RequiredArgsConstructor和@AllArgsConstructor：构造方法全自动
- @Data 组合拳
- @Value 不变类型组合拳
- @Builder 你用Builder这套模式吗？
- @SneakyThrows 玩不转异常者慎用！
- @Synchronized 用处似乎不大
- @Getter(lazy=true)
- @Log 用处相对不大，毕竟Idea的Live Template已经非常给力了

## 原理
- JSR269(Java Specification Requests)：Pluggable Annotation Processing API
- JDK6，野马，脱缰了。JSR269的一个Bug，给了lombok可乘之机
- JSR199：JavaTM Compiler API
- 比如以Tank为例：
    ```
    @Data
    @Deprecated
    public class Tank {
        public static void main(String[] args) {
            Stream.of(Tank.class.getAnnotations()).forEach(System.out::println);
        }
    }
    ```
    无法定位到Data这一注解。查看`Tank.class`反编译结果，@Deprecated还在，但是@Data不见了。通过`javap -v -l -p -c -s -sysinfo -constants Tank.class`可以追溯到更多信息。
- Lombok，“事了拂衣去，深藏身与名”的Style啊
- 我们先把问题简化一下，看看比如一个Getter/Setter如何实现
- [Google Auto Service](https://github.com/google/auto/tree/master/service)
- ant eclipse吧少年，ant idea挂了，人生苦短，暂不折腾了～
- 用途？很多很多，就看你脑洞够不够大了，够不够像脱缰野马了～


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

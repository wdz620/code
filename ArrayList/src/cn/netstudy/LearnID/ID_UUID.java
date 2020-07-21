package cn.netstudy.LearnID;

import java.util.UUID;


/**
 * 1、什么是分布式ID？
 * 拿MySQL数据库举个栗子：
 * 在我们业务数据量不大的时候，单库单表完全可以支撑现有业务，数据再大一点搞个MySQL主从同步读写分离也能对付。
 * 但随着数据日渐增长，主从同步也扛不住了，就需要对数据库进行分库分表，但分库分表后需要有一个唯一ID来标识一条数据，数据库的自增ID显然不能满足需求；
 * 特别一点的如订单、优惠券也都需要有唯一ID做标识。 * 此时一个能够生成全局唯一ID的系统是非常必要的。那么这个全局唯一ID就叫分布式ID。
 *
 * 2、那么分布式ID需要满足那些条件？
 * 全局唯一：必须保证ID是全局性唯一的，基本要求
 * 高性能：高可用低延时，ID生成响应要块，否则反倒会成为业务瓶颈
 * 高可用：100%的可用性是骗人的，但是也要无限接近于100%的可用性
 * 好接入：要秉着拿来即用的设计原则，在系统设计和实现上要尽可能的简单
 * 趋势递增：最好趋势递增，这个要求就得看具体业务场景了，一般不严格要求
 */
public class ID_UUID {
    public static void main(String[] args) {
        /**
         * 在Java的世界里，想要得到一个具有唯一性的ID，首先被想到可能就是UUID，毕竟它有着全球唯一的特性。那么UUID可以做分布式ID吗？
         * 答案是可以的，但是并不推荐！
         *
         * UUID的生成简单到只有一行代码，输出结果 c2b8c2b9e46c47e3b30dca3b0d447718，但UUID却并不适用于实际的业务需求。
         * 像用作订单号UUID这样的字符串没有丝毫的意义，看不出和订单相关的有用信息；
         * 而对于数据库来说用作业务主键ID，它不仅是太长还是字符串，存储性能差查询也很耗时，所以不推荐用作分布式ID。
         *
         * 优点：
         *  生成足够简单，本地生成无网络消耗，具有唯一性
         * 缺点：
         *  1、无序的字符串，不具备趋势自增特性
         *  2、没有具体的业务含义
         *  3、长度过长16 字节128位，36位长度的字符串，存储以及查询对MySQL的性能消耗较大，
         *     MySQL官方明确建议主键要尽量越短越好，作为数据库主键 UUID 的无序性会导致数据位置频繁变动，严重影响性能。
         */
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(s);

        System.out.println("================================");
        String u = UUID.randomUUID().toString();
        System.out.println(u);


    }
}

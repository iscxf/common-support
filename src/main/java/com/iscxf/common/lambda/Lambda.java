package com.iscxf.common.lambda;

import com.google.common.collect.Lists;
import com.iscxf.common.common.model.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 常见的lambda使用
 * @author chenxf
 * Created on 2020/4/3
 */
public class Lambda {

    public static void main(String[] args) {
        Person p1 = new Person("张三",20, 166.0, "forexmple@163.com");
        Person p2 = new Person("李四",60, 167.0, "forexmple@163.com");
        Person p3 = new Person("王五",15, 168.0, "forexmple1@163.com");
        Person p4 = new Person("刘六",50, 168.0, null);
        Person p5 = new Person("白七",30, 175.0, "forexmple2@163.com");
        Person p6 = new Person("黄八",40, 168.0, null);
        Person p7 = new Person("赵九",50, 186.0, "forexmple@163.com");
        Person p8 = new Person("赵九",50, 186.0, "forexmple@163.com");

        /** 使用google的collect.Lists工具类创建List更方便，不嫌麻烦也可以一个一个的 add 添加*/
        List<Person> list = Lists.newArrayList(p1, p2, p3, p4, p5, p6, p7, p8);

        /**
         * 1.forEach()进行遍历集合
         */
        list.forEach(item->{
            //{ }这个就是循环体内的方法区
            item.setAge(item.getAge() + 1);
            System.out.println("名称：" + item.getName() + "年龄：" + item.getAge());
        });

        /**
         * 2.stream()流操作
         * 注：list集合为null时，list.stream()会抛NPE异常，可用 (List)Optional.ofNullable(source).orElse(Lists.newArrayListWithCapacity(0)) 将集合由null转为空集合。
         */
        //2.1. 去重 distinct() 去重；collect(Collectors.toList())。封装成集合
        List<Person> distinctList = list.stream().distinct().collect(Collectors.toList());
        System.out.println("去重:" + distinctList);

        //2.2 过滤 ， filter(item->{})   item为每一项。 按照自己的需求来筛选list中的数据
        List<Person> filterList = list.stream().filter(item->item.getAge()>3).collect(Collectors.toList());
        System.out.println("过滤:" + filterList);

        //2.3.1 map(), 提取对象中的某一元素 推荐使用 :: 实例上的实例方法引用
        List<String> mapList = list.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println("提取对象中的某一元素:" + mapList);
        //2.3.2 提取对象中的元素转map(key:operateId  value:operateName) (x, y) -> x 代表map的key的覆盖    p -> p 代表取整一个对象
        Map<String, Integer> mapList2 = list.stream().collect(Collectors.toMap(Person::getName, Person::getAge, (x, y) -> x));
        Map<String, Person> mapList3 = list.stream().collect(Collectors.toMap(Person::getName, p -> p , (x, y) -> x));
        System.out.println("提取对象中的元素成为map:" + mapList2);

        //2.4 Stream中的高阶函数 sum(),max(),min(),average()
        double sum = list.stream().mapToDouble(Person::getAge).sum();
        System.out.println("统计sum函数:" + sum);

        //2.5.1 分组   Collectors.groupingBy(属性名)
        Map<Integer, List<Person>> map = list.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println("分组:" + map);
        //2.5.2 多重分组 Collectors.groupingBy(属性，Collectors.groupingBy(属性))
        Map<String, Map<Integer, List<Person>>> map2 = list.stream().collect(Collectors.groupingBy(Person::getName,Collectors.groupingBy(Person::getAge)));
        System.out.println("多重分组:" + map2);
        //2.5.3 分组并计算综合  计算最大值、最小值、平均值、求和、计数统计 对应的摘要统计的功能类 IntSummaryStatistics、LongSummaryStatistics 和 DoubleSummaryStatistics
        IntSummaryStatistics intSummaryStatistics = list.stream().mapToInt(Person::getAge).summaryStatistics();
        int max = intSummaryStatistics.getMax();
        System.out.println("计算最大值:" + max);
        Map<String, Map<Integer, IntSummaryStatistics>> map3 = list.stream().collect(Collectors.groupingBy(Person::getName,Collectors.groupingBy(Person::getAge,Collectors.summarizingInt(Person::getAge))));
        System.out.println("分组并计算综合:" + map3);

        //2.6.1 排序  sorted((第一个对象，第二个对象)->返回值)
        List<Person> sortedList = list.stream().sorted((o1,o2)->o1.getAge()-o2.getAge()).collect(Collectors.toList());
        System.out.println("排序:" + sortedList);
        //2.6.2 Comparator排序  可以使用thenComparing多重排序(注：comparing和thenComparing都会抛NPE，如下例子，当age有null值时会抛NPE，当name相邻有null时会抛NPE，不相邻则不会)
        List<Person> sortedList2 = list.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getName)).collect(Collectors.toList());
        System.out.println("Comparator排序:" + sortedList2);
        //2.6.3 Comparator自定义排序  实现Comparable并重写compareTo方法
        list.forEach(item->item.getAddress().setId(item.getAge()));
        List<Person> sortedList3 = list.stream().sorted(Comparator.comparing(Person::getAddress)).collect(Collectors.toList());
        System.out.println("Comparator自定义排序:" + sortedList3);
        //2.6.4 Comparator nullsFirst||nullsLast排序  对字段为null时的排序，将null的排在最前or最后，这里主要thenComparing的原始除了相邻null会抛NPE，email和height同时为null也会抛NPE
        List<Person> sortedList4 = list.stream().sorted(Comparator.comparing(Person::getEmail, Comparator.nullsLast(String::compareTo)).thenComparing(Person::getHeight)).collect(Collectors.toList());
        System.out.println("Comparator nullsLast排序:" + sortedList4);

    }
}

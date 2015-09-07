/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2015 
 * 日    期：15-9-2
 */
package com.willow.platform.utils;

import com.google.common.base.*;
import com.google.common.cache.*;
import com.google.common.collect.*;
import com.google.common.eventbus.*;
import com.google.common.io.Files;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
public class GuavaTest {
    //    多值map
    @Test
    public void multimapTest1() {
        Multimap<String, String> phonebook = ArrayListMultimap.create();
        phonebook.put("a", "43434");
        phonebook.put("a", "22222");
        phonebook.put("b", "3434434");
        for (String a : phonebook.get("a")) {
            System.out.println(a);
        }
    }


    @Test
    public void multimapTest2() {
        Multimap<String, String> myMultimap = ArrayListMultimap.create();

        // Adding some key/value
        myMultimap.put("Fruits", "Bannana");
        myMultimap.put("Fruits", "Apple");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Vegetables", "Carrot");

        // Getting the size
        int size = myMultimap.size();
        System.out.println(size);  // 4


        Collection<String> fruits = myMultimap.get("Fruits");
        System.out.println(fruits); // [Bannana, Apple, Pear]

        Collection<String> vegetables = myMultimap.get("Vegetables");
        System.out.println(vegetables); // [Carrot]

        // 循环输出
        for (String value : myMultimap.values()) {
            System.out.println(value);
        }

        System.out.println("-------key---------");
        for (String s : myMultimap.keys()) {
            System.out.println(s);
        }
        System.out.println("-------key---------");

        System.out.println("-------keySet---------");
        for (String s : myMultimap.keySet()) {
            System.out.println(s);
        }
        System.out.println("-------keySet---------");

        // 移走某个值
        myMultimap.remove("Fruits", "Pear");
        System.out.println(myMultimap.get("Fruits")); // [Bannana, Pear]

        //移走某个KEY的所有对应value
        myMultimap.removeAll("Fruits");
        System.out.println(myMultimap.get("Fruits")); // [] (Empty Collection!)
    }

    //    map的查询
    @Test
    public void filterValuesTest() {
        Map<String, Integer> user = new HashMap<String, Integer>();
        user.put("张三", 20);
        user.put("李四", 22);
        user.put("王五", 25);
        // 所有年龄大于20岁的人员
        Map<String, Integer> filtedMap = Maps.filterValues(user,
                new Predicate<Integer>() {
                    public boolean apply(Integer value) {
                        return value > 20;
                    }
                });
        System.out.println(filtedMap);
    }


    @Test
    public void StringsTest() {
        System.out.println(Strings.emptyToNull(""));
        System.out.println(Strings.nullToEmpty(null));

        System.out.println(Splitter.on(',').split("a,b"));
        System.out.println(Splitter.on(',').trimResults().split("a , b"));
        System.out.println(Splitter.on(',').omitEmptyStrings().split("a,,b"));
        System.out.println(Strings.repeat("java", 3));
        assertEquals("2011-08-04", Joiner.on("-").join("2011", "08", "04"));
    }

    @Test
    public void FilesTest() throws IOException {
//        File from = new File("");
//        File to = new File("");
//        Files.copy(from, to);
//        Files.deleteDirectoryContents(File directory); //删除文件夹下的内容(包括文件与子文件夹)
//        Files.deleteRecursively(File file); //删除文件或者文件夹
//        Files.move(from, to); //移动文件
    }

    @Test
    public void CharMatcherTest() {
        assertEquals("89983", CharMatcher.DIGIT.retainFrom("some text 89983 and more"));
        assertEquals("some text  and more", CharMatcher.DIGIT.removeFrom("some text 89983 and more"));
    }

    @Test
    public void PreconditionsTest() {
//        验证与条件检查
        int count = 1;
        Preconditions.checkArgument(count > 0, "must be positive: %s", count);

        assertEquals("aa", Preconditions.checkNotNull("aa"));
        Preconditions.checkNotNull(null);
    }


    @Test
    public void FunctionsTest() {
//闭包功能
        Function<String, Integer> strlen = new Function<String, Integer>() {
            public Integer apply(String from) {
                Preconditions.checkNotNull(from);
                return from.length();
            }
        };
        List<String> from = Lists.newArrayList("abc", "defg", "hijkl");
        List<Integer> to = Lists.transform(from, strlen);
        for (int i = 0; i < from.size(); i++) {
            System.out.printf("%s has length %d\n", from.get(i), to.get(i));
        }
    }


    @Test
    public void OptionalTest() {
        Optional<Integer> possible = Optional.of(6);
        Optional<Integer> absentOpt = Optional.absent();
        Optional<Integer> NullableOpt = Optional.fromNullable(null);
        Optional<Integer> NoNullableOpt = Optional.fromNullable(10);
        if (possible.isPresent()) {
            System.out.println("possible isPresent:" + possible.isPresent());
            System.out.println("possible value:" + possible.get());
        }
        if (!absentOpt.isPresent()) {
            System.out.println("absentOpt isPresent:" + absentOpt.isPresent());

        }
        if (!NullableOpt.isPresent()) {
            System.out.println("fromNullableOpt isPresent:" + NullableOpt.isPresent());
        }
        if (NoNullableOpt.isPresent()) {
            System.out.println("NoNullableOpt isPresent:" + NoNullableOpt.isPresent());
        }
    }


    @Test
    public void CacheBuilderTest() throws ExecutionException {
        CacheBuilder<String, Book> cahceBuilder = CacheBuilder.newBuilder().expireAfterAccess(18, TimeUnit.SECONDS).maximumSize(1).removalListener(new RemovalListener<String, Book>() {
            @Override
            public void onRemoval(RemovalNotification<String, Book> rn) {
                System.out.println(rn.getKey() + "被移除");
            }
        });
        LoadingCache<String, Book> cahce = cahceBuilder.build(new CacheLoader<String, Book>() {
            @Override
            public Book load(String key) throws Exception {
                Book b = new Book();
                return b;
            }
        });
        Book book_1_0 = cahce.get("book_1");//缓存book_1对象
        System.out.println(book_1_0);
        Book book_2_0 = cahce.get("book_2");//因为maximumSize设为1,故不能同时缓存两个或两个以上的对象,所以必须先移除之前的缓存对象book_1 再缓存book_2对象
        System.out.println(book_2_0);
        Book book_1_1 = cahce.get("book_1");//获取book_1 对象,如果存在则返回,否则创建新的对象,并将之缓存(book_2会被移除).
        System.out.println(book_1_1);


        Book book_2_1 = cahce.get("book_2");
        System.out.println(book_2_1);//<span style="white-space: normal; background-color: #ffffff;">org.zero.resource.Book@133f1d7</span>
        Book book_2_1_ = cahce.get("book_2");
        System.out.println(book_2_1_);//<span style="white-space: normal; background-color: #ffffff;">org.zero.resource.Book@133f1d7</span>
    }


    @Test
    public void EventBusTest() {
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();
        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:" + listener.getLastMessage());


        MultipleListener multiListener = new MultipleListener();

        eventBus.register(multiListener);

        eventBus.post(new Integer(100));
        eventBus.post(new Integer(200));
        eventBus.post(new Integer(300));
        eventBus.post(new Long(800));
        eventBus.post(new Long(800990));
        eventBus.post(new Long(800882934));

        System.out.println("LastInteger:" + multiListener.getLastInteger());
        System.out.println("LastLong:" + multiListener.getLastLong());

    }


    @Test
    public void EventBusTest1() {
        EventBus eventBus = new EventBus("test");
        MultipleListener multiListener = new MultipleListener();
        eventBus.register(multiListener);

        eventBus.post(new Integer(100));
        eventBus.post(new Integer(200));
        eventBus.post(new Integer(300));
        eventBus.post(new Long(800));
        eventBus.post(new Long(800990));
        eventBus.post(new Long(800882934));

        System.out.println("LastInteger:" + multiListener.getLastInteger());
        System.out.println("LastLong:" + multiListener.getLastLong());
    }


    @Test
    public void EventBusTest2() {
        EventBus eventBus = new EventBus("test");
        DeadEventListener deadEventListener = new DeadEventListener();
        eventBus.register(deadEventListener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));

        System.out.println("deadEvent:" + deadEventListener.isNotDelivered());
    }


    public class DeadEventListener {
        boolean notDelivered = false;
        public int lastMessage = 0;

        @Subscribe
        public void listen(DeadEvent event) {

            notDelivered = true;
        }

        @AllowConcurrentEvents
        @Subscribe
        public void listen(TestEvent event) {
            lastMessage = event.getMessage();
            System.out.println("Message:" + lastMessage);
        }

        public boolean isNotDelivered() {
            return notDelivered;
        }
    }


    public class MultipleListener {
        public Integer lastInteger;
        public Long lastLong;

        @Subscribe
        public void listenInteger(Integer event) {
            lastInteger = event;
            System.out.println("event Integer:" + lastInteger);
        }

        @Subscribe
        public void listenLong(Long event) {
            lastLong = event;
            System.out.println("event Long:" + lastLong);
        }

        public Integer getLastInteger() {
            return lastInteger;
        }

        public Long getLastLong() {
            return lastLong;
        }
    }

    public class EventListener {
        public int lastMessage = 0;

        @Subscribe
        public void listen(TestEvent event) {
            lastMessage = event.getMessage();
            System.out.println("Message:" + lastMessage);
        }

        public int getLastMessage() {
            return lastMessage;
        }
    }

    class TestEvent {
        private final int message;

        public TestEvent(int message) {
            this.message = message;
            System.out.println("event message:" + message);
        }

        public int getMessage() {
            return message;
        }
    }

}


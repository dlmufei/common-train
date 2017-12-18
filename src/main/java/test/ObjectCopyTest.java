package test;

import org.junit.Test;

/**
 * Java对象深浅拷贝测试
 * Created by cliffyan on 2017/12/8.
 */
public class ObjectCopyTest {

    public final class person {
        public String name;
        public int age;

        @Override
        public String toString() {
            return "person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Test
    public void test01() {
        System.out.println("test01-----");
        String s = "old";
        change01(s);
        System.out.println(s);

    }

    private void change01(String s) {
        System.out.println("before:" + s);
        s = "changed";
        System.out.println("after:" + s);
    }

    @Test
    public void test02() {
        System.out.println("test02-----");
        person p=new person();
        p.name="old";
        p.age=1;
        change02(p);
        System.out.println(p.toString());

    }

    private void change02(person p) {
        System.out.println("before:" + p.toString());
        p.name = "changed";
        p.age = 2;
        System.out.println("after:" + p.toString());
    }

}

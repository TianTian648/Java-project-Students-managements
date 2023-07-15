import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main() {

        ArrayList<Student> list = new ArrayList<>();
        Student student;
        String id;
        String name;
        int age;
        String address;
        System.out.println("-------------欢迎来到黑马学生管理系统----------------");
        show();
        Scanner sc = new Scanner(System.in);
        String command;
        //insert
        while (true) {
            show();
            System.out.println("请输入您的选择:");
            command = sc.next();
            if (command.equals("1")) {
                System.out.println("现在开始添加");
                insert(list);
            } else if (command.equals("2")) {
                System.out.println("现在开始删除");
                delete(list);
            } else if (command.equals("3")) {
                System.out.println("现在开始修改");
                modify(list);
            } else if (command.equals("4")) {
                System.out.println("现在开始查看");
                view(list);
                } else if(command.equals("5")) {
                System.out.println("结束了");
                break;
            } else {
                System.out.println("没有这个选项");
            }
        }


    }

    public static ArrayList<Student> insert(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入学员ID:");
        String id;
        int age;
        String name;
        String address;
        id = sc.next();
        if (contains(list, id)) {
            System.out.println("输入ID重复，请重新键入");
            return list;
        }
        System.out.println("请输入学员姓名：");
        name = sc.next();
        System.out.println("请输入学员年龄：");
        age = sc.nextInt();
        System.out.println("请输入学员住址");
        address = sc.next();
        Student student = new Student(id,name,age,address);
        list.add(student);
        return list;
    }
    public static ArrayList<Student> delete(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入待删除的学员ID");
        String id = sc.next();
        int index = findIndex(list, id);
        if (index == -1) {
            System.out.println("id不存在");
        } else {
            list.remove(index);
        }
        return  list;
    }
    public static ArrayList<Student> modify(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("首先请输入待修改的学员ID");
        String id = sc.next();
        String name;
        int age;
        String address;
        int index = findIndex(list, id);
        if (index == -1) {
            System.out.println("id不存在");
        } else {
            System.out.println("请输入姓名");
            name = sc.next();
            list.get(index).setName(name);
            System.out.println("请输入年龄");
            age = sc.nextInt();
            list.get(index).setAge(age);
            System.out.println("请输入住址");
            address = sc.next();
            list.get(index).setAddress(address);
        }
        return list;
    }
    public static void view(ArrayList<Student> list) {
        if (list.size() == 0) {
            System.out.println("当前无学生信息，请添加后再查询");
        } else {
            System.out.println("id\t\t\t姓名\t\t年龄\t\t家庭住址");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getId() + "\t" + list.get(i).getName() + "\t"
                        + list.get(i).getAge() + "\t" + list.get(i).getAddress());
            }
        }
    }
    public static boolean contains(ArrayList<Student> list, String id) {
        return findIndex(list, id) >= 0;
    }
    public static int findIndex(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) return  i;
        }
        return -1;
    }
    public static void show() {
        System.out.println("1：添加学生");
        System.out.println("2：删除学生");
        System.out.println("3：修改学生");
        System.out.println("4：查询学生");
        System.out.println("5：退出");
    }

}

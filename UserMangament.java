import java.util.ArrayList;
import java.util.Scanner;

public class UserMangament {
    public static void main(String[] args) {

        ArrayList<User> userlist = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String command;
        while (true) {
            show();
            command = sc.next();
            if (command.equals("2")) {
                userlist = register(userlist);
            } else if (command.equals("1")) {
                login(userlist);
            } else if (command.equals("3")) {
                forget(userlist);
            } else {
                System.out.println("没有这个命令");
            }
        }

    }

    public static ArrayList<User> register(ArrayList<User> userlist) {
        String username;
        String password;
        String idNumber;
        String phoneNumber;
        Scanner sc = new Scanner(System.in);
        User newuser = new User();
        //用户名
        while (true) {
            System.out.println("请输入用户名 用户名长度必须在3~15位之间 \n" +
                    "\n" +
                    "\u200B\t\t\t只能是字母加数字的组合，但是不能是纯数字");
            username = sc.next();
            if (ifillegal(username)) {
                if (contains(userlist, username)) {
                    System.out.println("用户名重复，请重新输入");
                } else {
                    System.out.println("新建用户名成功！");
                    newuser.setUsername(username);
                    break;
                }
            } else {
                System.out.println("用户名不合法 请重新键入");
            }
        }
        //密码
        while (true) {
            System.out.println("请输入密码");
            password = sc.next();
            System.out.println("请确认密码");
            String pwd = sc.next();
            if (password.equals(pwd)) {
                System.out.println("密码设置成功");
                newuser.setPassword(password);
                break;
            } else {
                System.out.println("两次密码不一致，请重新键入");
            }
        }
        //身份证号
        while (true) {
            System.out.println("请输入身份证号");
            idNumber = sc.next();
            if (idillegal(idNumber)) {
                System.out.println("身份证设置成功");
                newuser.setIdNumber(idNumber);
                break;
            } else {
                System.out.println("身份证号格式不对！");
            }
        }
        //手机号
        while (true) {
            System.out.println("请输入手机号");
            phoneNumber = sc.next();
            if (phillegal(phoneNumber)) {
                System.out.println("手机号设置成功");
                newuser.setPhoneNumber(phoneNumber);
                break;
            } else {
                System.out.println("手机号异常！");
            }
        }
        userlist.add(newuser);
        return userlist;
    }

    public static void login(ArrayList<User> userlist) {
        String username;
        String pwd;
        String vcode;
        Scanner sc = new Scanner(System.in);
        int tmpnum = 3;

        while (tmpnum > 0) {
            System.out.println("请输入用户名");
            username = sc.next();
            if (!contains(userlist, username)) {
                System.out.println("用户未注册，请先注册");
                return;
            }
            System.out.println("请输入密码");
            pwd = sc.next();
            while (true) {
                System.out.println("请输入验证码");
                vcode = sc.next();
                if (Verification(vcode)) {
                    break;
                } else {
                    System.out.println("验证码不正确，请重新输入");
                }
            }
            int index = findIndex(userlist, username);
            if (pwd.equals(userlist.get(index).getPassword())) {
                System.out.print("正确，准予登录");
                Main.main();
                return;
            } else {
                System.out.println("用户名或密码错误，请重试，你还剩" + tmpnum + "次机会");
            }

        }

    }

    public static void forget(ArrayList<User> userlist) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.next();
        if (!contains(userlist, username)) {
            System.out.println("该用户未注册");
            return;
        }
        System.out.println("请输入身份证号");
        String idnumber = sc.next();
        int index = findIndex(userlist, username);
        if (!idnumber.equals(userlist.get(index).getIdNumber())) {
            System.out.println("身份证号不匹配,结束咯");
            return;
        }
        System.out.println("请输入手机号");
        String phone = sc.next();
        if (!phone.equals(userlist.get(index).getPhoneNumber())) {
            System.out.println("手机号步匹配，结束咯");
        }
        while (true) {
            System.out.println("请输入新密码");
            String password = sc.next();
            System.out.println("请验证密码");
            String pwd = sc.next();
            if (pwd.equals(password)) {
                System.out.println("修改成功");
                userlist.get(index).setPassword(pwd);
                return;
            } else {
                System.out.println("两次输入密码不一致");
            }
        }
    }

    public static boolean Verification(String vcode) {
        if (vcode.length() != 5) return false;
        int count = 0;
        for (int i = 0; i < vcode.length(); i++) {
            if (isdigit(vcode.charAt(i))) count++;
            if (!isABC(vcode.charAt(i)) && !isdigit(vcode.charAt(i))) return false;
        }
        return count == 1;
    }

    public static boolean isABC(char c) {
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') return true;
        return false;
    }

    public static boolean isdigit(char c) {
        if (c >= '0' && c <= '9') return true;
        return false;
    }

    public static boolean contains(ArrayList<User> list, String id) {
        return findIndex(list, id) >= 0;
    }

    public static int findIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUsername().equals(username)) return i;
        }
        return -1;
    }

    public static boolean ifillegal(String username) {
        if (username.length() >= 16 || username.length() < 3) {
            return false;
        } else {
            for (int i = 0; i < username.length(); i++) {
                if (username.charAt(i) >= 'a' && username.charAt(i) <= 'z'
                        || username.charAt(i) >= 'A' && username.charAt(i) <= 'Z') {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean idillegal(String idnumber) {
        if (idnumber.length() != 18) return false;
        if (idnumber.charAt(0) == '0') return false;
        for (int i = 0; i < idnumber.length() - 1; i++) {
            if (idnumber.charAt(i) < '0' || idnumber.charAt(i) > '9') {
                return false;
            }
        }
        if (idnumber.charAt(17) == 'X' || idnumber.charAt(17) == 'X' ||
                idnumber.charAt(17) >= '0' && idnumber.charAt(17) <= '9') {
            return true;
        } else return false;
    }

    public static boolean phillegal(String phonenumber) {
        if (phonenumber.length() != 11) return false;
        if (phonenumber.charAt(0) == '0') return false;
        for (int i = 0; i < phonenumber.length(); i++) {
            if (phonenumber.charAt(i) > '9' || phonenumber.charAt(i) < '0') return false;
        }
        return true;
    }

    public static void show() {
        System.out.println("欢迎来到学生管理系统");
        System.out.println("请选择操作1登录 2注册 3忘记密码");
    }
}

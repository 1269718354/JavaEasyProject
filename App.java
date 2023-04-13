package com.yfy.test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<User>();
        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作：1登录 2注册 3忘记密码 4退出");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();

            switch (choose) {
                case "1":
                    login(list);
                    break;
                case "2":
                    register(list);
                    break;
                case "3":
                    forgetpw(list);
                    break;
                case "4":
                    System.out.println("谢谢您的使用");
                    System.exit(0);
                default:
                    System.out.println("没有这个选项");
                    break;
            }
        }

    }

    private static void forgetpw(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.next();
        boolean flag=contains(list,username);
        if(!flag){
            System.out.println("当前用户未注册，请先注册");
            return;
        }
        System.out.println("请输入身份证号码");
        String personID=sc.next();
        System.out.println("请输入手机号码");
        String phoneNumber=sc.next();
        int index = findIndex(list,username);
        User user=list.get(index);
        if(!(user.getPersonID().equalsIgnoreCase(personID)&&user.getPhoneNumber().equals(phoneNumber))){
            System.out.println("身份证或手机号有误，不能修改密码");
            return;
        }


        String password;
        while (true) {
            System.out.println("请输入新的密码");
            password=sc.next();
            System.out.println("请再次输入新的密码");
            String againPassword=sc.next();
            if(againPassword.equals(againPassword)){
                System.out.println("两次密码输入一致");
                break;
            }else{
                System.out.println("两次密码输入不一致");
                continue;
            }
        }

        user.setPassword(password);
        System.out.println("密码修改成功");

    }

    private static int findIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user=list.get(i);
            if(user.getUsername().equals(username)){
                return i;
            }
        }
        return -1;
    }

    private static void register(ArrayList<User> list) {
        String username;
        String password;
        String personID;
        String phoneNumber;


        //用户名录入
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入用户名");
            username = sc.next();

            boolean flag1 = checkUsername(username);

            if (!flag1) {
                System.out.println("不满足各式，需要重新输入");
                continue;
            }

            boolean flag2 = contains(list, username);
            if(flag2){
                System.out.println("已存在，请重新输入");
            }else{
                System.out.println("用户名可用");
                break;
            }
        }
        //密码录入
        while (true) {
            System.out.println("请输入要注册的密码");
            password = sc.next();
            System.out.println("请再次输入要注册的密码");
            String againPassword = sc.next();
            if(!password.equals(againPassword)){
                System.out.println("两次密码不一致，请重新输入");
                continue;
            }else{
                System.out.println("两次密码一致，继续录入其他数据");
                break;
            }
        }
        //身份证号码录入
        while (true) {
            System.out.println("请输入身份证号码");
            personID = sc.next();
            boolean flag = checkPersonID(personID);
            if(flag){
                System.out.println("身份证号码满足要求");
                break;
            }else{
                System.out.println("身份证号码格式有误，请重新输入");
                continue;
            }
        }
        //手机号码录入
        while (true) {
            System.out.println("请输入手机号码");
            phoneNumber=sc.next();
            boolean flag=checkPhoneNumber(phoneNumber);
            if(flag){
                System.out.println("手机号码格式正确");
                break;
            }else{
                System.out.println("手机号码格式有误，请重新输入");
                continue;
            }
        }

        User u=new User(username,password,personID,phoneNumber);
        list.add(u);
        System.out.println("注册成功");

        printlist(list);

    }

    private static void printlist(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            System.out.println(user.getUsername()+","+user.getPassword()+","+user.getPersonID()+","+user.getPhoneNumber());
        }
    }

    private static boolean checkPhoneNumber(String phoneNumber) {
        if(phoneNumber.length()!=11){
            return false;
        }
        if(phoneNumber.startsWith("0")){
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if(!(c>='0'&&c<='9')){
                return false;
            }
        }


        return true;
    }


    private static boolean checkPersonID(String personID){

        if(personID.length()!=18){
            return false;
        }
        if(personID.startsWith("0")){
            return false;
        }
        for (int i = 0; i < personID.length()-1; i++) {
            char c = personID.charAt(i);
            if(!(c>='0'&&c<='9')){
                return false;
            }
        }
        char endChar = personID.charAt(personID.length()-1);
        if(endChar>='0'&&endChar<='9'||(endChar=='x')||(endChar=='X')){
            return true;
        }else{
            return false;
        }

    }


    private static boolean contains(ArrayList<User> list,String username){
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String rightUsername = user.getUsername();
            if(rightUsername.equals(username)){
                return true;
            }
        }

        return false;
    }


    private static boolean checkUsername(String username){
        if(username.length()<3||username.length()>15){
            return false;
        }
        int count=0;
        for (int i = 0; i < username.length(); i++) {
            char c=username.charAt(i);
           if((c>='a'&&c<='z')||(c>='A'&&c<='Z')){
               count++;
               break;
           }
        }
        return count > 0;
    }


    private static void login(ArrayList<User> list) {
        Scanner sc=new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名");
            String username = sc.next();
            boolean flag = contains(list,username);
            if(!flag){
                System.out.println("用户名"+username+"未注册，请先注册再登录");
                return;
            }
            System.out.println("请输入密码");
            String password = sc.next();


            while (true) {
                String rightCode=getCode();
                System.out.println("当前正确的验证码为:"+rightCode);
                System.out.println("请输入验证码");
                String code = sc.next();
                if(code.equalsIgnoreCase(rightCode)){
                    System.out.println("验证码正确");
                    break;
                }else{
                    System.out.println("验证码错误");
                    continue;
                }
            }

            User useInfo = new User(username,password,null,null);
            boolean result = checkUserInfo(list,useInfo);
            if(result){
                System.out.println("登陆成功");
                break;
            }else{
                System.out.println("登录失败，用户名或密码错误");
                if(i==2){
                    System.out.println("当前账号已锁定");
                }

            }
        }

    }

    private static boolean checkUserInfo(ArrayList<User> list, User useInfo) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if(user.getUsername().equals(useInfo.getUsername())&&user.getPassword().equals(useInfo.getPassword())){
                return true;
            }
        }
        return false;
    }


    private static String getCode(){
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a'+1));
            list.add((char)('A'+1));
        }
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
           int index = r.nextInt(list.size());
           char c = list.get(index);
           sb.append(c);
        }
        int number = r.nextInt(10);
        sb.append(number);

        char []arr = sb.toString().toCharArray();
        int randomIndex = r.nextInt(arr.length);
        char temp = arr[randomIndex];
        arr[randomIndex] = arr[arr.length-1];
        arr[arr.length-1]=temp;

        return new String(arr);
    }
}

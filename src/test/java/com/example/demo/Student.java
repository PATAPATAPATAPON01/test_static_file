package com.example.demo;

import java.io.*;

public class Student implements Serializable {
    private transient Book book;
    private String name;


    public static  String staticProperty="11111";
    public Student(Book book, String name) {
        super();
        this.book = book;
        this.name = name;
    }

    public Student() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //下面这两个方法就是那组特殊的私有方法，它们会在序列化、反序列化的过程 中被自动调用   
    //我们必须保证方法的签名和下面的两个方法完全相同  

    //这个方法会在序列化的过程中被调用   
//    private void writeObject(ObjectOutputStream out){
//        try {
//            out.defaultWriteObject(); //这个方法会把这当前中非静态变量和非transient变量写到流中
//                                      //在这里我们就把name写到了流中。
//            //因为我们要保存Book的状态，所以我们还要想办法把其状态写入流中
//            out.writeInt(book.getIsbn());//ObjectOutputStream中提供了写基本类型数据的方法
//            //out.close();//注意，这句千万不能有，否刚将直接导致写入失败
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    private void writeObject(ObjectOutputStream outputStream) {
        try {
            System.out.println("会在序列化的时候自动被调用");
            outputStream.defaultWriteObject();
            outputStream.writeInt(book.getIsbn());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //这个方法会在反序列化的过程中被调用  
    private void readObject(ObjectInputStream in) {
        try {

            System.out.println("会在反序列化的时候自动被调用");

            in.defaultReadObject(); //和defaultWriteObject()方法相对应，默认的反序列化方法，会从流中读取  
            //非静态变量和非transient变量
            int isbn = in.readInt(); //用这个方法来读取一个int型值，这里我们是读取书号
            book = new Book(isbn); //这里我们就通过读取的 保存的状态构造 了一个和原来一样的Book对象
            //in.close();同样的这句也不能有  
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Student [book=" + book + ", name=" + name + "]";
    }
    public static void main(String[] args) {
        new Student().go();
    }
    private void go(){
        try {
            ObjectOutputStream out  = new ObjectOutputStream(new FileOutputStream("seria"));
            Student student1 = new Student(new NewBook(2011,"moree"),"kevin");
            out.writeObject(student1); //
            out.reset();
            student1.setName("Jordan");

            out.writeObject(student1);
            out.reset();
            student1.setName("Paul");
            out.writeObject(student1);
            System.out.println("object has been written..");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("seria"));
            Student s1 = (Student)in.readObject();
            Student s2 = (Student)in.readObject();
            Student s3 = (Student)in.readObject();
            System.out.println("Objects read here: ");
            System.out.println("Student1's name: "+s1.getName());
            System.out.println("Student2's name: "+s2.getName());
            System.out.println("Student3's name: "+s3.getName());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
package com.summer.unit;


import org.springframework.jdbc.support.JdbcUtils;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtil {


    public static  String url = "jdbc:mysql://127.0.0.1/server";
    public static  String name = "com.mysql.jdbc.Driver";
    public static  String user = "root";
    public static  String password = "summer";

    public static  String driverName = "";

    public static  String record = "c://Siweisoft/files";


    /*
     * 静态代码块，类初始化时加载数据库驱动
     */
    static {
        try {
            // 加载dbinfo.properties配置文件
            File file = new File(DBUtil.class.getResource("/").toString());
            String s = file.getParent().substring("file:/".length(),file.getParent().length());
            File f = new File(s+"/dbinfo.properties");
            InputStream in = new FileInputStream(f);
            Properties properties = new Properties();
            properties.load(in);

            // 获取驱动名称、url、用户名以及密码
            driverName = properties.getProperty("driverName");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");



            File f2 = new File(s+"/path.properties");
            InputStream in2 = new FileInputStream(f2);
            Properties properties2 = new Properties();
            properties2.load(in2);

            // 获取驱动名称、url、用户名以及密码
            record = properties2.getProperty("files");



            // 加载驱动
            Class.forName(driverName);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


//    public static Connection  getConnection() throws NamingException,SQLException {
//        Connection result=null;
//        InitialContext ctx=new InitialContext();
//        DataSource ds=(DataSource)ctx.lookup("java:/comp/env/jdbc/server");
//        result =ds.getConnection();
//        return result;
//    }
//
//    public static Connection  getConnection() throws SQLException,NamingException {
//        Connection result=null;
//        try {
//            Class.forName(name);//指定连接类型
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        result = DriverManager.getConnection(url, user, password);//获取连接
//        return result;
//    }


    public static Connection  getConnection() throws SQLException,NamingException {
        Connection result=null;
        result = DriverManager.getConnection(url, user, password);//获取连接
        return result;
    }


    /*
     * 将rs结果转换成对象列表
     * @param rs jdbc结果集
     * @param clazz 对象的映射类
     * return 封装了对象的结果列表
     */
    public static List populate(ResultSet rs , Class clazz) throws SQLException, InstantiationException, IllegalAccessException{
        //结果集的元素对象
        ResultSetMetaData rsmd = rs.getMetaData();
        //获取结果集的元素个数
        int colCount = rsmd.getColumnCount();
//         System.out.println("#");
//         for(int i = 1;i<=colCount;i++){
//             System.out.println(rsmd.getColumnName(i));
//             System.out.println(rsmd.getColumnClassName(i));
//             System.out.println("#");
//         }
        //返回结果的列表集合
        List list = new ArrayList();
        //业务对象的属性数组
        Field[] fields = clazz.getDeclaredFields();
        while(rs.next()){//对每一条记录进行操作
            Object obj = clazz.newInstance();//构造业务对象实体
            //将每一个字段取出进行赋值
            for(int i = 1;i<=colCount;i++){
                Object value = rs.getObject(i);
                //寻找该列对应的对象属性
                for(int j=0;j<fields.length;j++){
                    Field f = fields[j];
                    //如果匹配进行赋值
                    if(f.getName().equalsIgnoreCase(rsmd.getColumnName(i))){
                        boolean flag = f.isAccessible();
                        f.setAccessible(true);
                        f.set(obj, value);
                        f.setAccessible(flag);
                        break;
                    }
                }
            }
            list.add(obj);
        }
        return list;
    }


    public static void close(Connection connection , PreparedStatement ps, ResultSet set){
        if(set!=null){
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection , CallableStatement ps, ResultSet set){
        if(set!=null){
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
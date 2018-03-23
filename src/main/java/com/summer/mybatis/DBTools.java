package com.summer.mybatis;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBTools {

    public static SqlSessionFactory sessionFactory;
//
//    static{
//        try {
//            //使用MyBatis提供的Resources类加载mybatis的配置文件
//
//            Reader reader = Resources.getResourceAsReader("mybatis.cfg.xml");
//            //构建sqlSession的工厂
//            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    //创建能执行映射文件中sql的sqlSession
    public static SqlSession getSession(){
        if(sessionFactory!=null){
            return sessionFactory.openSession();
        }
        try {
            //使用MyBatis提供的Resources类加载mybatis的配置文件
            File classpath = Resources.getResourceAsFile("");
            File root = classpath.getParentFile();
            File file = new File(root,"mybatis.cfg.xml");
            Reader reader = new FileReader(file);

            FileUtils.copyFileToDirectory(new File(root,"resources/config.properties"),classpath);


            //构建sqlSession的工厂
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory.openSession();
    }

}
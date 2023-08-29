package common;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * DAO에 들어갈 타입을 결정하기 위해 T의 값은 VO의 값을 결정
 */
public abstract class SERVICE<T> {
    protected CRUD<T> dao;
    protected Scanner sc;
    protected Properties prop;

    public SERVICE(Scanner sc, CRUD<T> dao) {
        System.out.println("SERVICE 클래스 생성!");
        this.sc = sc;
        this.prop = new Properties();
        this.dao = dao;
    }

    public void propSet() {
        prop = new Properties();
        prop.put("severIp", "210.164.24.6");
        prop.put("port", "5000");
        prop.put("db", "oracle");
        prop.put("id", "aaa");
        prop.put("password", "111");

        for(Object key : prop.keySet()) { // keySet(): 키 묶음. properties의 키는 Object 타입
            String k = (String)key; // 원래타입 String으로 다운 캐스팅
            // getProperty(키): 키로 검색, 키로 등록된 값이 반환
            System.out.println(k + " : " + prop.getProperty(k));
        }

        try {
            prop.store(new FileWriter("C:\\Users\\KOSTA\\prop.properties"), "이것은 커멘트");
            // prop.storeToXML(new FileOutputStream("C:\\Users\\KOSTA\\p0811\\prop.xml"), "이것은 코멘트", "UTF-8");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    } 

    /**
     * 로그인 설정
     * @param prop
     * @throws SQLException
     * @throws IOException
     */
    public abstract boolean login(Properties prop) throws SQLException, IOException;

    /**
     * 로그아웃
     * @param prop
     * @throws SQLException
     * @throws IOException
     */
    public abstract boolean logout(Properties prop) throws SQLException, IOException;
}

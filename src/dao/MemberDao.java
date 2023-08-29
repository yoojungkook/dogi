package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import common.CRUD;
import common.DBConnect;
import vo.Member;

public class MemberDao<T extends Member> extends CRUD<Member> {

    public MemberDao() {
        System.out.println("MemberDao 클래스 생성!");
        db = DBConnect.getInstance();
    }

    @Override
    public void insert(Member member) throws SQLException {
        conn = db.conn();

        sql = "INSERT INTO MEMBER(NO, ID, PWD, NAME, EMAIL, LOCATION_NO, FAVORITE_NO) VALUES (?, ?, ?, ?, ?, 5, 1)";

        ps = conn.prepareStatement(sql);
        ps.setInt(1, 10);
        ps.setString(2, member.getId());
        ps.setString(3, member.getPwd());
        ps.setString(4, member.getName());
        ps.setString(5, member.getEmail());

        ps.executeUpdate();
    }

    @Override
    public ArrayList<Member> select(HashMap<String, String> args) throws SQLException {
        System.out.println("select");
        ArrayList<Member> list = new ArrayList<>();

        conn = db.conn();

        if (args == null) {
            sql = "SELECT * FROM MEMBER";

            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Member(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getDate(6), rs.getInt(7), rs.getInt(8)));
            }

            return list;
        }

        sql = "SELECT * FROM MEMBER WHERE ";

        int cnt = args.size() - 1;
        for (Entry<String, String> entry : args.entrySet()) {
            if (cnt > 0)
                sql += entry.getKey() + " = \'" + entry.getValue() + "\' AND ";
            else
                sql += entry.getKey() + " = \'" + entry.getValue() + "\'";
            cnt--;
        }

        ps = conn.prepareStatement(sql);

        rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Member(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getDate(6), rs.getInt(7), rs.getInt(8)));
        }

        return list;
    }

    @Override
    public void update(Member member) throws SQLException {
        conn = db.conn();

        sql = "UPDATE MEMBER SET LOCATION_NO = ? WHERE NO = ? ";

        ps = conn.prepareStatement(sql);
        ps.setInt(1, member.getLocationNo());
        ps.setInt(2, member.getNo());
        ps.executeUpdate();
    }

    @Override
    public void delete(Member member) throws SQLException {

    }

    @Override
    public void close() throws SQLException {
        if (rs != null)
            rs.close();
        ps.close();
        conn.close();
        System.out.println("close() 작동!");
    }
}

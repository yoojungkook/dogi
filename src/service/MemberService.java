package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

import common.SERVICE;
import dao.MemberDao;
import vo.Member;

/**
 * Service 추상 클래스를 상속
 * 타입을 Member로 설정
 */
public class MemberService extends SERVICE<Member> {
    public MemberService(Scanner sc, MemberDao<Member> dao) {
        super(sc, dao);
        this.sc = sc;
        this.dao = dao;
        System.out.println("MemberService 클래스 생성!");
    }

    @Override
    public boolean login(Properties prop) throws SQLException, IOException {
        return false;
    }

    @Override
    public boolean logout(Properties prop) throws SQLException, IOException {
        return false;
    }

    public void running(int num) {
        try (MemberDao<Member> memberDao = (MemberDao<Member>)this.dao;){
            switch (num) {
                case 1:
                    son((MemberDao<Member>)memberDao);
                    break;
                case 2:
                    members((MemberDao<Member>)memberDao);
                    break;
                case 3:
                    memberUpdate((MemberDao<Member>)memberDao);
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void memberUpdate(MemberDao<Member> dao) throws SQLException {
        Member member = new Member();

        System.out.print("바꿀 멤버 번호 입력: ");
        int memberNo = sc.nextInt();
        member.setNo(memberNo);

        System.out.print("새로운 지역번호 입력: ");
        int locationNo = sc.nextInt();
        member.setLocationNo(locationNo);
        
        dao.update(new Member());
    }

    public void son(MemberDao<Member> dao) throws SQLException {
        HashMap<String, String> list = new HashMap<>();
        list.put("NAME", "철수");
        for (Member member : (ArrayList<Member>) dao.select(list)) {
            System.out.println(member.toString());
        }
        list.clear();
    }

    public void members(MemberDao<Member> dao) throws SQLException {
        for (Member member : dao.select(null)) {
            System.out.println(member);
        }
    }
}

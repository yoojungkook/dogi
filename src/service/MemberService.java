package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

    public void running(int num) {
        /*
         * dao는 한 번 사용하면 사라지는 객체가 아닌 try문이 끝나면
         * CRUD의 close() 메소드가 자동적으로 작동하여 sql가 관련된 객체만을 소멸시킴
         */
        try (MemberDao<Member> memberDao = (MemberDao<Member>) this.dao;) {
            switch (num) {
                case 1:
                    son((MemberDao<Member>) memberDao);
                    break;
                case 2:
                    members((MemberDao<Member>) memberDao);
                    break;
                case 3:
                    memberUpdate((MemberDao<Member>) memberDao);
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

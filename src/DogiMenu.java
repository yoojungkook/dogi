import java.util.Scanner;

import common.Info;
import dao.MemberDao;
import service.MemberService;
import vo.Member;

public class DogiMenu {
    private Scanner sc;

    public DogiMenu() {
        sc = new Scanner(System.in);
    }

    // AutoCasable() : 멱등성이란?
    public void menu() {
        MemberDao<Member> memberDao = new MemberDao<>();
        MemberService mService = new MemberService(sc, memberDao);
        while (true) {
            String status = null;
            if(Info.log()){
                status = "로그아웃";
            } else {
                status = "로그인";
            }
            System.out.println("1. " + status +" 2. 멤버서비스");
            System.out.print("-----------------\n선택: ");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    if(status.equals("로그아웃")) Info.logout();
                    if(status.equals("로그인")) Info.login(sc, memberDao);
                    break;
                case 2:
                    mService.running(num);
                    break;
                case 3:
                    break;
            }
        }
    }
}

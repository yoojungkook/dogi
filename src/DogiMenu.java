import java.util.Scanner;

import dao.MemberDao;
import service.MemberService;

public class DogiMenu {
    private Scanner sc;

    public DogiMenu() {
        sc = new Scanner(System.in);
    }

    // AutoCasable() : 멱등성이란?
    public void menu() {
        MemberService mService = new MemberService(new Scanner(System.in), new MemberDao<>());
        while (true) {
            System.out.println("1. 멤버 서비스");
            System.out.print("-----------------\n선택: ");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    mService.running(num);
                    break;
                case 2:
                    mService.running(num);
                    break;
                case 3:
                    mService.running(num);
                    break;
            }
        }
    }
}

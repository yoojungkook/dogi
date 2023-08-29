package vo;

/**
 * DB의 FAVORITE 테이블
 */
public class Favorite {
    private int no;
    private String name;
    private int count;

    public Favorite() {}
    public Favorite(int no, String name, int count) {
        this.no = no;
        this.name = name;
        this.count = count;
    }

    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    
}

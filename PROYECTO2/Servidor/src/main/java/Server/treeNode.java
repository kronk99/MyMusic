package Server;

public class treeNode {
    private int password;
    private String username;
    private treeNode left;
    private treeNode right;

    public treeNode(String Username , String password1){
        this.password = Integer.parseInt(password1);
        this.username = Username;
        this.left = null;
        this.right = null;

    }

    public void setLeft(treeNode left) {
        this.left = left;
    }

    public void setRight(treeNode right) {
        this.right = right;
    }

    public int getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public treeNode getLeft() {
        return left;
    }

    public treeNode getRight() {
        return right;
    }
}

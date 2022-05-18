package Server;

public class binaryTree {
    private treeNode root;
    private int deep;

    public binaryTree() {
        this.root = null;
        this.deep = 0;
    }
    public treeNode getroot(){
        return this.root;
    }

    public void insertnode(String username, String password) {
        if (this.root == null) {
            treeNode Nodo = new treeNode(username, password);
            this.root = Nodo;

        } else {
            int a = 0;
            treeNode current = this.root;
            while (a == 0) {
                if (Integer.parseInt(password) > current.getPassword()) {
                    if (current.getRight() != null) {
                        current = current.getRight();
                    } else {
                        treeNode Nodo = new treeNode(username, password);
                        current.setRight(Nodo);
                        a=1 ;
                    }
                }
                else {
                    if (current.getLeft() != null) {
                        current = current.getLeft();
                    } else {
                        treeNode Nodo = new treeNode(username, password);
                        current.setLeft(Nodo);
                        a=1 ;

                    }

                }
            }


        }

    }
    public int searchdata(String password ,String Username){
        int a = 0;
        treeNode current = this.root;
        while (a == 0){
            if( current.getPassword() > Integer.parseInt(password)){
                current = current.getLeft();
            }
            else if (current.getPassword() < Integer.parseInt(password)){
                current = current.getRight();
            }
            else{
                if(current.getPassword() == Integer.parseInt(password) && current.getUsername() == Username){
                    a = 1;
                    return  a;
                }
                a=2;
            }
        }
        System.out.println(a);
        return a;
    }

    public static void main(String[] args) {
        binaryTree binaryTree = new binaryTree();
        binaryTree.insertnode("pepoiesta" , "1234");
        binaryTree.insertnode("caao" , "2394018");
        binaryTree.insertnode("mae mae" , "10");
        System.out.println(binaryTree.getroot());
        binaryTree.searchdata("2394018" , "caao");
        System.out.println(binaryTree.searchdata("2394018" , "caao"));
    }
}

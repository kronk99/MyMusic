package Server;

import java.util.ArrayList;
import java.util.List;

public class UsersList {

    private List<Users> userList = new ArrayList<Users>();

    public UsersList(){

        this.userList = new ArrayList<Users>();
    }

    public UsersList(List<Users> userList){

        super();
        this.userList = userList;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }
}

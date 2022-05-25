package Server;

public class Users {

    private String username;
    private String password;
    private String name;
    private String lastname;
    private int age;
    private String favoriteMusic;

    // Es necesario para que no mande errores
    public Users (){

    }

    public Users(String username, String password, String name, String lastname, int age, String favoriteMusic){

        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.favoriteMusic = favoriteMusic;
    }

    public String toString(){

        String returnValue = this.username + ", " + this.password + ", " + this.name + ", " + this.lastname + ", " + this.age + " , " + this.favoriteMusic;
        return returnValue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavoriteMusic() {
        return favoriteMusic;
    }

    public void setFavoriteMusic(String favoriteMusic) {
        this.favoriteMusic = favoriteMusic;
    }
}

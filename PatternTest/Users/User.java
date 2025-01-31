package Users;


public class User {

    private final String name;
    private Role role;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

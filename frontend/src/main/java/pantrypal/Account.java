package pantrypal;

class Account {

    private String username;
    private String password;

    Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Boolean equals(Account a) {
        return this.username == a.getUsername() && this.password == a.getPassword();
    }
}
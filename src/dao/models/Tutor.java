package dao.models;

/**
 * * <h1>Tutor</h1>
 * Tutor class is responsible for single entity if tutors table
 * Created by alex on 6/11/15.
 */
public class Tutor {
    private int idtutors;
    private String tName;
    private String tSurname;
    private String tLogin;
    private String tPassword;
    private String email;

    public int getIdtutors() {
        return idtutors;
    }

    public void setIdtutors(int idtutors) {
        this.idtutors = idtutors;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettSurname() {
        return tSurname;
    }

    public void settSurname(String tSurname) {
        this.tSurname = tSurname;
    }

    public String gettLogin() {
        return tLogin;
    }

    public void settLogin(String tLogin) {
        this.tLogin = tLogin;
    }

    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tutor tutor = (Tutor) o;

        if (idtutors != tutor.idtutors) return false;
        if (tName != null ? !tName.equals(tutor.tName) : tutor.tName != null) return false;
        if (tSurname != null ? !tSurname.equals(tutor.tSurname) : tutor.tSurname != null) return false;
        if (tLogin != null ? !tLogin.equals(tutor.tLogin) : tutor.tLogin != null) return false;
        if (tPassword != null ? !tPassword.equals(tutor.tPassword) : tutor.tPassword != null) return false;
        if (email != null ? !email.equals(tutor.email) : tutor.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtutors;
        result = 31 * result + (tName != null ? tName.hashCode() : 0);
        result = 31 * result + (tSurname != null ? tSurname.hashCode() : 0);
        result = 31 * result + (tLogin != null ? tLogin.hashCode() : 0);
        result = 31 * result + (tPassword != null ? tPassword.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}

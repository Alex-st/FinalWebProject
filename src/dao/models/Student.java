package dao.models;


/**
 * Created by alex on 6/11/15.
 */

public class Student {
    private int idstudents;
    private String studName;
    private String studSurname;
    private String studLogin;
    private String studPassword;
    private String email;

    public Student() {}

    public Student(int id, String name, String surname, String login, String pass, String mail) {
        idstudents = id;
        studName = name;
        studSurname = surname;
        studLogin = login;
        studPassword = pass;
        email = mail;
    }

    public int getIdstudents() {
        return idstudents;
    }

    public void setIdstudents(int idstudents) {
        this.idstudents = idstudents;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getStudSurname() {
        return studSurname;
    }

    public void setStudSurname(String studSurname) {
        this.studSurname = studSurname;
    }

    public String getStudLogin() {
        return studLogin;
    }

    public void setStudLogin(String studLogin) {
        this.studLogin = studLogin;
    }

    public String getStudPassword() {
        return studPassword;
    }

    public void setStudPassword(String studPassword) {
        this.studPassword = studPassword;
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

        Student student = (Student) o;

        if (idstudents != student.idstudents) return false;
        if (studName != null ? !studName.equals(student.studName) : student.studName != null) return false;
        if (studSurname != null ? !studSurname.equals(student.studSurname) : student.studSurname != null)
            return false;
        if (studLogin != null ? !studLogin.equals(student.studLogin) : student.studLogin != null) return false;
        if (studPassword != null ? !studPassword.equals(student.studPassword) : student.studPassword != null)
            return false;
        if (email != null ? !email.equals(student.email) : student.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idstudents;
        result = 31 * result + (studName != null ? studName.hashCode() : 0);
        result = 31 * result + (studSurname != null ? studSurname.hashCode() : 0);
        result = 31 * result + (studLogin != null ? studLogin.hashCode() : 0);
        result = 31 * result + (studPassword != null ? studPassword.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}

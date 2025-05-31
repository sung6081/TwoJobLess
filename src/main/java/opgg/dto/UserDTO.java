package opgg.dto;

import java.util.Date;

public class UserDTO {
    private Long id;
    private String name;
    private String password;
    private String cellPhone;
    private String email;
    private Date regDate;

    public UserDTO() {}

    public UserDTO(Long id, String name, String password, String cellPhone, String email, Date regDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.cellPhone = cellPhone;
        this.email = email;
        this.regDate = regDate;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCellPhone() { return cellPhone; }
    public void setCellPhone(String cellPhone) { this.cellPhone = cellPhone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getRegDate() { return regDate; }
    public void setRegDate(Date regDate) { this.regDate = regDate; }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", email='" + email + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}

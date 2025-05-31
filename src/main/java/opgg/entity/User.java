package opgg.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "cell_phone", length = 14, nullable = false)
    private String cellPhone;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "reg_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    public User() {}

    public User(Long id, String name, String password, String cellPhone, String email, Date regDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.cellPhone = cellPhone;
        this.email = email;
        this.regDate = regDate;
    }

    public static class Builder {
        private String name;
        private String password;
        private String cellPhone;
        private String email;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder cellPhone(String cellPhone) {
            this.cellPhone = cellPhone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(null, name, password, cellPhone, email, null);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // Getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getCellPhone() { return cellPhone; }
    public String getEmail() { return email; }
    public Date getRegDate() { return regDate; }
}

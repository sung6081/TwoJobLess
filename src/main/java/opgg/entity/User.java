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

    // --- 이메일 인증용 필드 추가 ---
    @Column(name = "is_verified", nullable = false)
    private boolean isVerified = false;

    @Column(name = "verify_token", length = 64)
    private String verifyToken;

    public User() {}

    public User(Long id, String name, String password, String cellPhone, String email, Date regDate,
                boolean isVerified, String verifyToken) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.cellPhone = cellPhone;
        this.email = email;
        this.regDate = regDate;
        this.isVerified = isVerified;
        this.verifyToken = verifyToken;
    }

    public static class Builder {
        private String name;
        private String password;
        private String cellPhone;
        private String email;
        private boolean isVerified = false;
        private String verifyToken;

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

        public Builder isVerified(boolean isVerified) {
            this.isVerified = isVerified;
            return this;
        }

        public Builder verifyToken(String verifyToken) {
            this.verifyToken = verifyToken;
            return this;
        }

        public User build() {
            return new User(null, name, password, cellPhone, email, null, isVerified, verifyToken);
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
    public boolean isVerified() { return isVerified; }
    public String getVerifyToken() { return verifyToken; }

    // Setter (인증 처리 시 필요)
    public void setVerified(boolean verified) {
        this.isVerified = verified;
    }

    public void setVerifyToken(String verifyToken) {
        this.verifyToken = verifyToken;
    }
}

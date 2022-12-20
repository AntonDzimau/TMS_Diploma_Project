package models;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private String name;
    @EqualsAndHashCode.Exclude
    private int id;
    @SerializedName(value = "email")
    private String email;
    private String password;
    @SerializedName(value = "is_active")
    private boolean isActive;
    @SerializedName(value = "is_admin")
    @EqualsAndHashCode.Exclude
    private boolean isAdmin;
    @SerializedName(value = "role_id")
    private int roleId;
    private String role;
    @EqualsAndHashCode.Exclude
    private String email_notifications;
}

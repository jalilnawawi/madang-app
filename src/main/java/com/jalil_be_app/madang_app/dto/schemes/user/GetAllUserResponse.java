package com.jalil_be_app.madang_app.dto.schemes.user;

import com.jalil_be_app.madang_app.model.entity.account.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

public class GetAllUserResponse {
    @Schema(name = "fullname", examples = "Gibran Rakabuming Raka")
    private String fullname;
    @Schema(name = "gender", examples = "LAKI_LAKI")
    private String gender;
    @Schema(name = "username", examples = "gibranraka")
    private String username;
    @Schema(name = "email", examples = "gibranrk@gmail.com")
    private String email;
    @Schema(name = "role", examples = "[\n" +
            "            {\n" +
            "                \"id\": \"0700a796-ba0a-4590-9a06-33343d5d008d\",\n" +
            "                \"name\": \"ROLE_USER\"\n" +
            "            }\n" +
            "        ]")
    private Set<Role> role;
    @Schema(name = "imageLink", examples = "update_image_user")
    private String imageLink;
}

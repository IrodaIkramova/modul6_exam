package com.example.modul6_exam.oauth2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacebookUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//
//    @ManyToOne
//    private OAuth2User auth2User;

//    public FacebookUser(OAuth2User auth2User) {
//        this.auth2User = auth2User;
//    }
//
//
//    public Map<String, Object> getAttributes() {
//        return auth2User.getAttributes();
//    }
//

//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return auth2User.getAuthorities();
//    }

//    public String getName() {
//        return auth2User.getName();
//    }

}

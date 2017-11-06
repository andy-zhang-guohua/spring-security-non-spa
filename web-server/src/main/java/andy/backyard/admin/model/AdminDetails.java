package andy.backyard.admin.model;


import andy.backyard.admin.enums.AdminAuthority;
import andy.backyard.common.utils.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
public class AdminDetails extends Admin implements UserDetails {
    private List<AdminAuthority> authorities;

    public AdminDetails(Admin user, List<AdminAuthority> authorities) {
        BeanUtils.copyProperties(this, user);
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null || authorities.size() < 1) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        StringBuilder sb = new StringBuilder();
        for (AdminAuthority authority : authorities) {
            sb.append(authority.name()).append(",");
        }
        String authorities = sb.substring(0, sb.length() - 1);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !super.isLocked();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}

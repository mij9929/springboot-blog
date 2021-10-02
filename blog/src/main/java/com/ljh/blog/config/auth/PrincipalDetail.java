package com.ljh.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ljh.blog.model.User;

import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장해준다.
@Getter
public class PrincipalDetail implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 195043114058364006L;
	
	private User user;

	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	// 계정이 만료되지 않았는지 리턴함(true : 만료안됨)
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	// 비밀번호가 만료되었는지 판단(true: 만료안됨)
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	// 계정 활성하가 되어있는지 판단(true 활성화)
	public boolean isEnabled() {
		return true;
	}
	
	
	
	// 계정의 권한을 판단
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{return "ROLE_" + user.getRole();});
		return collectors;
	}
	
	
	
}

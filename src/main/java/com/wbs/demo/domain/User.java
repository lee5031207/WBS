package com.wbs.demo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter @Setter 
@Builder
@NoArgsConstructor @AllArgsConstructor
public class User implements UserDetails{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="longin_id")
	private String loginId;
	
	@Column(name="pwd")
	private String pwd;
	
	@Column(name="user_nm")
	private String userNm;
	
	@Column(name="email")
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
	
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
	private List<ProjectMember> projectMembers = new ArrayList<>();
	
	@Column(name="role")
	private String role;

	
	//UserDetails 구현체
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getUsername() {
		return loginId;
	}
	
	@Override
	public String getPassword() {
		return pwd;
	}
	
	@Override
    public boolean isAccountNonExpired() {
		//계정 만료 여부
		//ex)90일 이상 로그인 안 하면 계정 만료 처리
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
    	//계정 잠김 여부
    	//ex)비밀번호 5회 이상 틀리면 잠금 처리
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
    	//비밀번호(자격 증명) 만료 여부
    	//ex)비밀번호는 3개월마다 변경해야 한다
        return true;
    }

    @Override
    public boolean isEnabled() {
    	//계정 활성화 여부
    	//ex)관리자가 수동으로 계정 끌 수도 있음
        return true;
    }
}

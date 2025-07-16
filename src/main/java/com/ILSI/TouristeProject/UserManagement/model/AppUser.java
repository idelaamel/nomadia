package com.ILSI.TouristeProject.UserManagement.model;

import com.ILSI.TouristeProject.AutreClass.model.Visit;
import com.ILSI.TouristeProject.AvailablePackages.model.AvailablePackage;
import com.ILSI.TouristeProject.RecommanderSystem.Model.*;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Entity
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameUser;
    @Column(nullable = false, unique = true)
    @NaturalId(mutable = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isEnabled = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Visit> visits = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_viewed_packages",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "package_id")
    )
    private Set<AvailablePackage> viewedPackages = new HashSet<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritesAccessibility> favoriAccessibilityList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritesActivity> favoriActivityList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritesAncillaryService> favoriAncillaryServiceList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritesAvailablePackage> favoriAvailablePackageList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritesLocation> favoriLocationList = new ArrayList<>();


    public AppUser() {}
    public AppUser(String nameUser, String email, String password) {
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}

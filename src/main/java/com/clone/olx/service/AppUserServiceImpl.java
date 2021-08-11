package com.clone.olx.service;

import com.clone.olx.model.AppUser;
import com.clone.olx.repository.AppUserRepository;
import com.clone.olx.repository.ProductRepository;
import com.clone.olx.requests.AppUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
//    private final ProductRepository productRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public UUID createAppUser(AppUserRequest request) {
        AppUser newAppUser = new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getPassword(),
                request.getCountry(),
                request.getTown(),
                request.getPhoneNumber(),
                request.getEmail()
        );

        AppUser savedAppUser = appUserRepository.save(newAppUser);
        return savedAppUser.getAppUserId();
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public Optional<AppUser> getAppUser(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }
}

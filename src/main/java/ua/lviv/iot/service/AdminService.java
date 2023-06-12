package ua.lviv.iot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import ua.lviv.iot.repository.AdminRepository;

@Service
@ApplicationScope
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public Boolean isUserAdmin(Integer userId) {
        return adminRepository.existsById(userId);
    }
}
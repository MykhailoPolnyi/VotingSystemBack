package ua.lviv.iot.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import ua.lviv.iot.model.election.ElectionDto;
import ua.lviv.iot.repository.AdminRepository;

import java.util.List;

@Service
@ApplicationScope
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public Boolean isUserAdmin(Integer userId) {
        // TODO Implement class
        return null;
    }

    public List<ElectionDto> getCreatedElectionList(Integer adminId) {
        // TODO Implement method
        return null;
//        return adminRepository.getCreatedElectionList(adminId);
    }
}

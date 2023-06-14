package ua.lviv.iot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import ua.lviv.iot.repository.AdminRepository;
import ua.lviv.iot.repository.ElectionRepository;

@Service
@ApplicationScope
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final ElectionRepository electionRepository;

    public Boolean isUserAdmin(Integer userId) {
        return adminRepository.existsById(userId);
    }

    public Boolean canAdminEditElection(Integer adminId, Integer electionId) {
        if (!isUserAdmin(adminId)) {
            return false;
        }

        var electionList = electionRepository.findEditableElectionList(adminId);
        return electionList.stream().anyMatch(e -> e.getId().equals(electionId));
    }
}
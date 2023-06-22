package property.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import property.application.model.Role;
import property.application.model.User;
import property.application.repo.PropertyRepository;
import property.application.repo.UserRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminStatsService {

    private final UserRepo userRepo;
    private final PropertyRepository propertyRepository;

    public Map<String, Object> getUserStats() {
        List<User> users = userRepo.findAll();

        Map<String, Object> stat = new HashMap<>();

        for (User user : users) {
            List<Role> roles = user.getRoles();

            for (Role role : roles) {
                String roleName = role.getRoleName();
                stat.put(roleName, (Double) stat.getOrDefault(roleName, 0.0) + 1);
            }
        }

        double totalUsers = users.size();
        for (Map.Entry<String, Object> entry : stat.entrySet()) {
            double roleCount = (Double) entry.getValue();
            double percentage = (roleCount / totalUsers) * 100;
            entry.setValue(percentage);
        }

        return stat;
    }

    public Map<String, Object> getPropertyStats() {
        var properties = propertyRepository.findAll();
        Map<String, Object> map = new HashMap<>();

        properties.forEach(item -> {
            map.put(item.getType().toString(), (Double) map.getOrDefault(item.getType().toString(), 0.0) + 1);
        });
        double total = properties.size();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            double count = (Double) entry.getValue();
            double percentage = (count / total) * 100;
            entry.setValue(percentage);
        }
        return map;
    }
}

package property.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import property.application.service.AdminStatsService;

import java.util.Map;

@RestController
@RequestMapping("stats")
public class AdminStatsController {

    @Autowired
    private AdminStatsService adminStatsService;

    @GetMapping("/users")
    public Map<String,Object> getStats(){
        return adminStatsService.getUserStats();
    }

    @GetMapping("/properties")
    public Map<String,Object> getPropertyStat(){
        return adminStatsService.getPropertyStats();
    }


}

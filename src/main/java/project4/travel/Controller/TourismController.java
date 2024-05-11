package project4.travel.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project4.travel.Entity.Tourism;
import project4.travel.Repository.TourismRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class TourismController {

    @Autowired
    private TourismRepository tourismRepository;

    @GetMapping("/")
    public String home() {
        return "main";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "groupName", required = false) String groupName,
                         @RequestParam(name = "seasonName", required = false) String seasonName,
                         @RequestParam(name = "iemName", required = false) String iemName,
                         Model model) {
        List<Tourism> results;

        if (groupName != null && !groupName.isEmpty()) {
            results = tourismRepository.findByGRPNMContaining(groupName);
        } else if (seasonName != null && !seasonName.isEmpty()) {
            results = tourismRepository.findBySEASONNMContaining(seasonName);
        } else if (iemName != null && !iemName.isEmpty()) {
            results = tourismRepository.findByIEMNMContaining(iemName);
        } else {
            // Handle the case when neither groupName nor seasonName is provided
            results = Collections.emptyList();
        }

        model.addAttribute("results", results);
        return "search";
    }
}
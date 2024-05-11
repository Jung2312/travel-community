package project4.travel.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project4.travel.Entity.Tourism;
import project4.travel.Repository.TourismRepository;

import java.util.List;

@Service
public class TourismService {

    private final TourismRepository tourismRepository;

    @Autowired
    public TourismService(TourismRepository tourismRepository) {
        this.tourismRepository = tourismRepository;
    }


}


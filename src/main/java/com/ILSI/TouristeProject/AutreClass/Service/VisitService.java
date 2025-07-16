package com.ILSI.TouristeProject.AutreClass.Service;

import com.ILSI.TouristeProject.AutreClass.Repository.VisitRepository;
import com.ILSI.TouristeProject.AutreClass.dto.VisitDto;
import com.ILSI.TouristeProject.AutreClass.model.Visit;
import com.ILSI.TouristeProject.UserManagement.Exception.UserNotFoundException;
import com.ILSI.TouristeProject.UserManagement.Repository.UserRepository;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final UserRepository userRepository;

    public List<Visit> findAllVisit(){return visitRepository.findAll();}

     public Optional<Visit> findVisitByTitle(String title){return visitRepository.findVisitByTitle(title);}


    public Visit createVisit(VisitDto dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        Visit visit = new Visit();
        visit.setTitle(dto.getTitle());
        visit.setDescription(dto.getDescription());
        visit.setStartDate(dto.getStartDate());
        visit.setEndDate(dto.getEndDate());
        visit.setNumberDays(dto.getNumberDays());
        visit.setUser(user);
        return visitRepository.save(visit);
    }


    public Visit updateVisit(Long id, VisitDto dto) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visit with id: " + id + " not found"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        if (!visit.getUser().getEmail().equals(username)) {
            throw new AccessDeniedException("You do not have permission to modify this visit");
        }
        visit.setTitle(dto.getTitle());
        visit.setDescription(dto.getDescription());
        visit.setStartDate(dto.getStartDate());
        visit.setEndDate(dto.getEndDate());
        visit.setNumberDays(dto.getNumberDays());
        visit.setUser(user);
        return visitRepository.save(visit);
    }

    public void deleteVisit(Long id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visit with id: " + id + " not found"));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!visit.getUser().getEmail().equals(username)) {
            throw new AccessDeniedException("You do not have permission to delete this visit");
        }
        visitRepository.delete(visit);
    }

}

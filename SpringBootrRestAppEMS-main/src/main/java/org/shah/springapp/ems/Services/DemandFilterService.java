package org.shah.springapp.ems.Services;


import org.shah.springapp.ems.Domains.Demand;
import org.shah.springapp.ems.Repository.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import jakarta.persistence.criteria.Predicate;

@Service
public class DemandFilterService {

    @Autowired
    private DemandRepository demandRepository;

    public List<Demand> filterDemands(String projectId, String projectName, String managerName,
                                      String managerId, String level, String city,
                                      Double duration, Date startDate, String status,
                                      String skillKey, Integer skillValue) {
        Specification<Demand> spec = createSpecification(projectId, projectName, managerName,
                managerId, level, city, duration,startDate, status, skillKey, skillValue);
        return demandRepository.findAll(spec);
    }

    private Specification<Demand> createSpecification(String projectId, String projectName, String managerName,
                                                      String managerId, String level, String city,
                                                      Double duration, Date startDate, String status,
                                                      String skillKey, Integer skillValue) {
        return (root, query, cb) -> {
            // Initialize predicate list
            Predicate predicate = cb.conjunction();

            // Add criteria for each attribute
            if (projectId != null && !projectId.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("projectId"), projectId));
            }
            if (projectName != null && !projectName.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("projectName"), projectName));
            }
            if (managerName != null && !managerName.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("managerName"), managerName));
            }
            if (managerId != null && !managerId.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("managerId"), managerId));
            }
            if (level != null && !level.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("level"), level));
            }
            if (city != null && !city.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("city"), city));
            }
            if (duration != null) {
                predicate = cb.and(predicate, cb.equal(root.get("duration"), duration));
            }
            if (startDate != null) {
                predicate = cb.and(predicate, cb.equal(root.get("startDate"), startDate));
            }
            if (status != null && !status.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("status"), status));
            }
            if (skillKey != null && skillValue != null) {
                predicate = cb.and(predicate, cb.equal(root.get("skills").get(skillKey), skillValue));
            }

            return predicate;
        };
    }
}
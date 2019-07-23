package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.ProjectTaxCostInterface;
import com.bccm.projectservices.entity.ProjectTaxCostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProjectTaxCostService {
    @Autowired
    private ProjectTaxCostInterface projectTaxCostInterface;

    public ProjectTaxCostEntity findByProjectid(String projectid) {
        ProjectTaxCostEntity p = projectTaxCostInterface.findByProjectid(projectid);
        if (p == null) {
            p = new ProjectTaxCostEntity();
            p.setGuid(UUID.randomUUID().toString().replace("-", ""));
        }
        return p;
    }

    public void saveProjectTaxCost(ProjectTaxCostEntity p) {
        if (p.getInputdate() == null) {
            p.setInputdate(LocalDateTime.now());
        }
        projectTaxCostInterface.saveAndFlush(p);
    }
}

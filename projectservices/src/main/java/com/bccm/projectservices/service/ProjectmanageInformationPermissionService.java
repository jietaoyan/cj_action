package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.ProjectmanageInformationPermissionInterface;
import com.bccm.projectservices.entity.ProjectmanageInformationPermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectmanageInformationPermissionService {
    @Autowired
    ProjectmanageInformationPermissionInterface Projectmanageinformationpermissioninterface;

    @Autowired
    ProjectmanageInformationPermissionInterface Projectperminssoninterface;



    //保存到项目管理permmison的函数
    public void savePermmison(ProjectmanageInformationPermissionEntity entity){
        Projectperminssoninterface.save(entity);
    }
}

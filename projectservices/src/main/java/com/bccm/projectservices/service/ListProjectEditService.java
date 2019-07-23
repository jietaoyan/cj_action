package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.ListProjectEditInterface;
import com.bccm.projectservices.Interface.ProjectmanageInformationMainInterface;
import com.bccm.projectservices.entity.ListProjectEdit;
import com.bccm.projectservices.entity.ProjectmanageInformationMainEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListProjectEditService {

    @Autowired
    ListProjectEditInterface listProjectEditInterface;

    @Autowired
    ProjectmanageInformationMainInterface projectmanageInformationMainInterface;

    static List<ListProjectEdit> alistProjectEdits=new ArrayList<ListProjectEdit>();

    public String deleteDepentId(String id)
    {

        ListProjectEdit listProjectEdit=listProjectEditInterface.findByGuid(id);
//        ListProjectEdit rootlistProjectEdit=listProjectEditInterface.findByGuid(listProjectEdit.getProjectid());
        listProjectEditInterface.deleteFromId(id);
        while (listProjectEdit != null && !("0").equals(listProjectEdit.getParentid())) {
            BigDecimal sonTotal =  BigDecimal.valueOf(0);
            List<ListProjectEdit> allProject = listProjectEditInterface.findAllByParentid(listProjectEdit.getParentid());
            for (ListProjectEdit listProjectEdit1 : allProject)
            {
                sonTotal = sonTotal.add(listProjectEdit1.getAmount()==null?BigDecimal.valueOf(0):listProjectEdit1.getAmount());
            }
            listProjectEdit = listProjectEditInterface.getOne(listProjectEdit.getParentid());
            if("0".equals(listProjectEdit.getParentid()))
            {
                ProjectmanageInformationMainEntity projectmanageInformationMainEntity=projectmanageInformationMainInterface.findByGuid(listProjectEdit.getProjectid());
                projectmanageInformationMainEntity.setMoney(sonTotal.toString());
                projectmanageInformationMainInterface.save(projectmanageInformationMainEntity);
            }
            listProjectEdit.setAmount(sonTotal);
            listProjectEditInterface.save(listProjectEdit);
        }


        return "SUCCESS";
    }

    public List<ListProjectEdit> getAllTreeProject(String guid)
    {
        List<ListProjectEdit> allTreeProject=listProjectEditInterface.findAll();
        ListProjectEdit listProjectEdit=listProjectEditInterface.findByGuid(guid);
        alistProjectEdits=new ArrayList<ListProjectEdit>();
        alistProjectEdits.add(listProjectEdit);
        return findAllSon(allTreeProject,guid);
    }

    public List<ListProjectEdit> findAllSon(List<ListProjectEdit> listProjectEdits, String id) {
        for (ListProjectEdit listProjectEdit : listProjectEdits) {
            if (listProjectEdit.getParentid() != null && listProjectEdit.getParentid().equals(id)) {
                findAllSon(listProjectEdits, listProjectEdit.getGuid());
                alistProjectEdits.add(listProjectEdit);
            }
        }
        return alistProjectEdits;
    }

}

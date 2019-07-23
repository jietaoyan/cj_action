package com.bccm.projectservices.service;

import com.bccm.projectservices.entity.VO.PageEntity;
import com.bccm.projectservices.sqlEntity.EngineeringGuidanceEntity;
import com.bccm.projectservices.sqlInterface.EngineeringGuidanceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class EngineeringGuidanceService {

    @Autowired
    private EngineeringGuidanceInterface guidanceInterface;

    //按搜索条件和分页数据获取清包总数
    public PageEntity<EngineeringGuidanceEntity> getPrjBagsList(Map<String,String> params){
        String professional = params.get("professional");
        String datasource = params.get("datasource");
        String subcontractingmode = params.get("subcontractingmode");
        String projectsite = "%" + params.get("projectsite") +"%";
        String Namesubcontractproject = "%" +params.get("Namesubcontractproject") +"%";
        String nameproject = "%" +params.get("nameproject") +"%";
        String mterialname = "%" +params.get("mterialname") +"%";
        int page = Integer.parseInt( params.get("page"));
        int pageSize =Integer.parseInt( params.get("size"));
        int offNum = page * pageSize;
        String[] types = params.get("type").split("_");
        PageEntity<EngineeringGuidanceEntity> pageEntity = new PageEntity<>();
        List<EngineeringGuidanceEntity> list = guidanceInterface.findAllPrjBagclears(professional,datasource,subcontractingmode,projectsite
                ,Namesubcontractproject,nameproject,mterialname,offNum,pageSize,types);
        int count = guidanceInterface.findCountPrjBagclears(professional,datasource,subcontractingmode,projectsite
                ,Namesubcontractproject,nameproject,mterialname,types);
        pageEntity.setContent(list);
        pageEntity.setTotalElements(count);
        return pageEntity;
    }

    //按搜索条件和分页数据获取清包总数
    public PageEntity<EngineeringGuidanceEntity> getAllPrjBagsList(Map<String,String> params){
        String professional = "%" + params.get("projectName") +"%";
        String professions="%"+params.get("professional")+"%";
        int page = Integer.parseInt( params.get("page"));
        int pageSize =Integer.parseInt( params.get("size"));
        int offNum = page * pageSize;
        PageEntity<EngineeringGuidanceEntity> pageEntity = new PageEntity<>();
        List<EngineeringGuidanceEntity> list = guidanceInterface.findAllPrjBagclearList(professional,professions,offNum,pageSize);
        int count = guidanceInterface.findCountPrjBagclearList(professional,professions);
        pageEntity.setContent(list);
        pageEntity.setTotalElements(count);
        return pageEntity;
    }

    //按搜索条件和分页数据获取施工分包总数
    public PageEntity<EngineeringGuidanceEntity> getAllDiffPrjBagsList(Map<String,String> params){
        String professional = "%" + params.get("projectName") +"%";
        String professions="%"+params.get("professional")+"%";
        int page = Integer.parseInt( params.get("page"));
        int pageSize =Integer.parseInt( params.get("size"));
        int offNum = page * pageSize;
        PageEntity<EngineeringGuidanceEntity> pageEntity = new PageEntity<>();
        List<EngineeringGuidanceEntity> list = guidanceInterface.finddiffPrjBagclearList(professional,professions,offNum,pageSize);
        int count = guidanceInterface.finddiffCountPrjBagclearList(professional,professions);
        pageEntity.setContent(list);
        pageEntity.setTotalElements(count);
        return pageEntity;
    }


    //专业分类
    public List<String> findAllProfessional(String type){
        String[] types = type.split("_");
        return guidanceInterface.findAllProfessional(types);
    }
    //数据来源
    public List<String> findAllDatasource(String type){
        String[] types = type.split("_");
        return guidanceInterface.findAllDatasource(types);
    }

    //分包模式
    public List<String> findAllSubcontractingmode(String type){
        String[] types = type.split("_");
        return guidanceInterface.findAllSubcontractingmode(types);
    }
}

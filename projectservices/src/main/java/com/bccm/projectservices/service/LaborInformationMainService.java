package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.LaborInformationMainInfoInterface;
import com.bccm.projectservices.entity.LaborInformationMainEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class LaborInformationMainService {
    @Autowired
    LaborInformationMainInfoInterface laborInformationMainInfo;

    //默认显示所有劳务信息
    public Page<LaborInformationMainEntity> getLaborInformationMainInfo(PageRequest pageRequest){
        return laborInformationMainInfo.findAll(new Specification<LaborInformationMainEntity>() {
            @Override
            public Predicate toPredicate(Root<LaborInformationMainEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> deleteFlagPath = root.get("deleteflag");
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.notEqual(deleteFlagPath,"-1"));
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        },pageRequest);
    }

    //String column1, String companyname, String type, String content, String companypath多条件查询
    public Page<LaborInformationMainEntity> queryLaborInformations(String prjname, String companyname, String type, String companypath, String content, PageRequest pageRequest){
        return laborInformationMainInfo.findAll(new Specification<LaborInformationMainEntity>() {
            @Override
            public Predicate toPredicate(Root<LaborInformationMainEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> prjnamePath = root.get("prjname");
                Path<String> companynamePath =root.get("companyname");
                Path<String> typePath =root.get("type");
                Path<String> contentPath =root.get("content");
                Path<String> companypathPath =root.get("companypath");
                Path<String> deleteFlagPath = root.get("deleteflag");
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.notEqual(deleteFlagPath,"-1"));
                if(StringUtils.isNotEmpty(prjname)){
                    list.add(criteriaBuilder.like(prjnamePath,"%" + prjname +"%"));
                }
                if(StringUtils.isNotEmpty(companyname)){
                    list.add(criteriaBuilder.like(companynamePath,"%" + companyname +"%"));
                }
                if(StringUtils.isNotEmpty(type)){
                    list.add(criteriaBuilder.like(typePath,"%" + type +"%"));
                }
                if(StringUtils.isNotEmpty(content)){
                    list.add(criteriaBuilder.like(contentPath,"%" + content +"%"));
                }
                if(StringUtils.isNotEmpty(companypath)){
                    list.add(criteriaBuilder.like(companypathPath,"%" + companypath +"%"));
                }
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        },pageRequest);
    }
    //根据编号删除的方法
    public void deleteByGuids(List<LaborInformationMainEntity> Labors){
        //laborInformationMainInfo.deleteByColumn2(column2);
            try {
               // String[] strings = column2.split("_");
                //判断是否为纯数字 防止sql注入
               // for(String userId : strings){
                //    Integer.parseInt(userId);
                    //laborInformationMainInfo.deleteByGudis(ids);
                    laborInformationMainInfo.deleteAll(Labors);
               // }
            }
            catch (Exception e){
            }
    }
}

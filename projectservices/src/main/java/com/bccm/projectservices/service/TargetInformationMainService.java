package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.LaborInformationMainInfoInterface;
import com.bccm.projectservices.Interface.TargetInformationMainInfoInterface;
import com.bccm.projectservices.entity.TargetInformationMainEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TargetInformationMainService {
    @Autowired
    TargetInformationMainInfoInterface targetInformationMainInfo;

    //默认显示所有劳务信息
    public Page<TargetInformationMainEntity> getTargetInformationMainInfo(PageRequest pageRequest){
        return targetInformationMainInfo.findAll(new Specification<TargetInformationMainEntity>() {
            @Override
            public Predicate toPredicate(Root<TargetInformationMainEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> deleteFlagPath = root.get("deleteflag");
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.notEqual(deleteFlagPath,"-1"));
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        },pageRequest);
    }

    //String column1, String companyname, String type, String content, String companypath多条件查询
    public Page<TargetInformationMainEntity> queryTargetInformations(String prjname, String name, String startdate, String enddate, PageRequest pageRequest){
        return targetInformationMainInfo.findAll(new Specification<TargetInformationMainEntity>() {
            @Override
            public Predicate toPredicate(Root<TargetInformationMainEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> prjname_val = root.get("prjname");
                Path<String> name_val =root.get("name");
                Path<String> inputdate_val =root.get("inputdate");
                Path<String> deleteFlagPath = root.get("deleteflag");
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.notEqual(deleteFlagPath,"-1"));
                if(StringUtils.isNotEmpty(prjname)){
                    list.add(criteriaBuilder.like(prjname_val,"%" + prjname +"%"));
                }
                if(StringUtils.isNotEmpty(name)){
                    list.add(criteriaBuilder.like(name_val,"%" + name +"%"));
                }

                //按照时间查询
                if(StringUtils.isNotEmpty(startdate) && StringUtils.isNotEmpty(enddate) && ! startdate.equals("undefined") && ! enddate.equals("undefined")){
                    list.add(criteriaBuilder.greaterThanOrEqualTo(inputdate_val.as(String.class),startdate));
                }
                if(StringUtils.isNotEmpty(startdate) && StringUtils.isNotEmpty(enddate) && ! startdate.equals("undefined") && ! enddate.equals("undefined")){
                    list.add(criteriaBuilder.lessThanOrEqualTo(inputdate_val.as(String.class),enddate));
                }

                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        },pageRequest);
    }
    //根据编号删除的方法
    public void deleteByGuids(List<TargetInformationMainEntity> Targets){
        //laborInformationMainInfo.deleteByColumn2(column2);
            try {
               // String[] strings = column2.split("_");
                //判断是否为纯数字 防止sql注入
               // for(String userId : strings){
                //    Integer.parseInt(userId);
                //targetInformationMainInfo.deleteByGudis(ids);
                targetInformationMainInfo.deleteAll(Targets);
               // }
            }
            catch (Exception e){
            }
    }

    //多条数据保存

}

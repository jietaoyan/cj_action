package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.CaseDatabaseInterface;
import com.bccm.projectservices.entity.BagClearDatabase;
import com.bccm.projectservices.entity.CaseDatabaseEntity;
import com.bccm.projectservices.entity.MaterialDatabase;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class CaseDatabaseService {

    public static final Logger logger = LoggerFactory.getLogger(CaseDatabaseService.class);
  @Autowired
    CaseDatabaseInterface caseDatabaseInterface;

    public Page<CaseDatabaseEntity> getList(PageRequest pageRequest,String inputerusername,String name,String subjectname,String startdate,String enddate) {
        return caseDatabaseInterface.findAll(new Specification<CaseDatabaseEntity>() {
            @Override
            public Predicate toPredicate(Root<CaseDatabaseEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<String> inputerusernamePath = root.get("inputerusername");
                Path<String> namePath = root.get("name");
                Path<String> subjectnamePath = root.get("subject");
                Path<String> inputdate_val =root.get("inputdate");
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotEmpty(inputerusername)) {
                    predicates.add(criteriaBuilder.like(inputerusernamePath, "%" + inputerusername + "%"));
                }
                if (StringUtils.isNotEmpty(name)) {
                    predicates.add(criteriaBuilder.like(namePath, "%" + name + "%"));
                }
                if (StringUtils.isNotEmpty(subjectname)) {
                    predicates.add(criteriaBuilder.like(subjectnamePath, "%" + subjectname + "%"));
                }
                //按照时间查询
                if(StringUtils.isNotEmpty(startdate)&& ! startdate.equals("undefined")){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(inputdate_val.as(String.class),startdate));
                }
                if(StringUtils.isNotEmpty(enddate)&& ! enddate.equals("undefined")){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(inputdate_val.as(String.class),enddate));
                }
                Predicate[] predicates1 = new Predicate[predicates.size()];
                return criteriaBuilder.and(predicates.toArray(predicates1));
            }
        }, pageRequest);
    }

    public String saveCaseInfo(List<CaseDatabaseEntity> caseDatabaseEntityList)
    {
        for(CaseDatabaseEntity caseDatabaseEntity:caseDatabaseEntityList)
        {

            if(caseDatabaseEntity.getGuid()==null)
            {
                String guid=UUID.randomUUID().toString().replaceAll("-","");
            caseDatabaseEntity.setGuid(guid);
        }
        }
        try {
        caseDatabaseInterface.saveAll(caseDatabaseEntityList);}
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return "SUCCESS";
    }

    @Transactional
    public String deleteAll(List<CaseDatabaseEntity> caseDatabaseEntityList) {
        String flag="SUCCESS";
        try {
            caseDatabaseInterface.deleteAll(caseDatabaseEntityList);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
            return flag;
    }
}

package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.UserPermissionDataInterface;
import com.bccm.projectservices.entity.UserPermissionDataEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserPermissionDataService {

    public static final Logger logger = LoggerFactory.getLogger(ProjectmanageInformationMainService.class);
    @Autowired
    private UserPermissionDataInterface userPermissionDataInterface;

//    根据条件查询所有审批数据
public Page<UserPermissionDataEntity> getList(PageRequest pageRequest, String inputerfullname, String permissionstate, String permissionlevel, String startdate, String enddate) {
    return userPermissionDataInterface.findAll(new Specification<UserPermissionDataEntity>() {
        @Override
        public Predicate toPredicate(Root<UserPermissionDataEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            Path<String> inputerfullnamePath = root.get("inputerfullname");
            Path<String> permissionstatePath = root.get("permissionstate");
            Path<String> permissionlevelPath = root.get("permissionlevel");
            Path<String> inputdatetime_val =root.get("inputdatetime");
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotEmpty(inputerfullname)) {
                predicates.add(criteriaBuilder.like(inputerfullnamePath, "%" + inputerfullname + "%"));
            }
            if (StringUtils.isNotEmpty(permissionstate)) {
                predicates.add(criteriaBuilder.like(permissionstatePath, "%" + permissionstate + "%"));
            }
            if (StringUtils.isNotEmpty(permissionlevel)) {
                predicates.add(criteriaBuilder.like(permissionlevelPath, "%" + permissionlevel + "%"));
            }
            //按照时间查询
            if(StringUtils.isNotEmpty(startdate)&& ! startdate.equals("undefined")){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(inputdatetime_val.as(String.class),startdate));
            }
            if(StringUtils.isNotEmpty(enddate)&& ! enddate.equals("undefined")){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(inputdatetime_val.as(String.class),enddate));
            }
            Predicate[] predicates1 = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(predicates1));
            }
        }, pageRequest);
    }


/*
* 查询个人用户
* */
    public Page<UserPermissionDataEntity> getUseList(PageRequest pageRequest, String inputerusername, String permissionstate, String permissionlevel, String startdate, String enddate) {
        return userPermissionDataInterface.findAll(new Specification<UserPermissionDataEntity>() {
            @Override
            public Predicate toPredicate(Root<UserPermissionDataEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<String> inputerusernamePath = root.get("inputerusername");
                Path<String> permissionstatePath = root.get("permissionstate");
                Path<String> permissionlevelPath = root.get("permissionlevel");
                Path<String> inputdatetime_val =root.get("inputdatetime");
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotEmpty(inputerusername)) {
                    predicates.add(criteriaBuilder.like(inputerusernamePath, "%" + inputerusername + "%"));
                }
                if (StringUtils.isNotEmpty(permissionstate)) {
                    predicates.add(criteriaBuilder.like(permissionstatePath, "%" + permissionstate + "%"));
                }
                if (StringUtils.isNotEmpty(permissionlevel)) {
                    predicates.add(criteriaBuilder.like(permissionlevelPath, "%" + permissionlevel + "%"));
                }
                //按照时间查询
                if(StringUtils.isNotEmpty(startdate)&& ! startdate.equals("undefined")){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(inputdatetime_val.as(String.class),startdate));
                }
                if(StringUtils.isNotEmpty(enddate)&& ! enddate.equals("undefined")){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(inputdatetime_val.as(String.class),enddate));
                }
                Predicate[] predicates1 = new Predicate[predicates.size()];
                return criteriaBuilder.and(predicates.toArray(predicates1));
            }
        }, pageRequest);
    }


    @Transactional
    public String savePermission(List<UserPermissionDataEntity> userPermissionDataEntityList)
    {
        for(UserPermissionDataEntity userPermissionDataEntity:userPermissionDataEntityList)
        {
            if(null==userPermissionDataEntity.getGuid())
            {
                String guid= UUID.randomUUID().toString().replace("-","");
                userPermissionDataEntity.setGuid(guid);
            }
            if(null==userPermissionDataEntity.getInputdatetime())
            {
                userPermissionDataEntity.setInputdatetime(new Timestamp(new Date().getTime()));
            }
            if("同意".equals(userPermissionDataEntity.getPermissionstate())&&"报表导出".equals(userPermissionDataEntity.getPermissionlevel()))
            {
                Date permissionStarttime=new Date();
                Date permissionEndtime= DateUtils.addHours(permissionStarttime,6);
                userPermissionDataEntity.setPermissionstarttime(new Timestamp(permissionStarttime.getTime()));
                userPermissionDataEntity.setPermissionendtime(new Timestamp(permissionEndtime.getTime()));
            }
        }
        try {
            userPermissionDataInterface.saveAll(userPermissionDataEntityList);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error(e.getMessage(),e);
            return "Fail";
        }
        return "SUCCESS";
    }
}

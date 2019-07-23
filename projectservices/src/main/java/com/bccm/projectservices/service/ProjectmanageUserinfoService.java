package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.ProjectUserLoginTimeInterface;
import com.bccm.projectservices.Interface.ProjectUserinforInterface;
import com.bccm.projectservices.entity.ProjectmanageUserinforEntity;
import com.bccm.projectservices.entity.ProjectmanageUserinforLoginEntity;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectmanageUserinfoService {
    @Autowired
    ProjectUserinforInterface projectuserinforinterface;

    @Autowired
    ProjectUserLoginTimeInterface logintimeinterface;
    //默认显示所有人员信息
    public Page<ProjectmanageUserinforEntity> getProjectmanageUserInfo(PageRequest pageRequest,String userself){
        return projectuserinforinterface.findAll(new Specification<ProjectmanageUserinforEntity>() {
            @Override
            public Predicate toPredicate(Root<ProjectmanageUserinforEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> deleteFlagPath = root.get("deleteflag");
                List<Predicate> list = new ArrayList<Predicate>();

                list.add(criteriaBuilder.notEqual(deleteFlagPath,"-1"));

//                CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("guid"));
//                for (String id : ids) {
//                    in.value(id);
//                }
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        },pageRequest);
    }

    //String username, userfullname多条件查询
    public Page<ProjectmanageUserinforEntity> queryProjectUserinfos(String username, String userfullname, PageRequest pageRequest){
        return projectuserinforinterface.findAll(new Specification<ProjectmanageUserinforEntity>() {
            @Override
            public Predicate toPredicate(Root<ProjectmanageUserinforEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> username_val = root.get("username");
                Path<String> userfullname_val =root.get("userfullname");
                Path<String> deleteFlagPath = root.get("deleteflag");
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.notEqual(deleteFlagPath,"-1"));
                if(StringUtils.isNotEmpty(username)){
                    list.add(criteriaBuilder.like(username_val,"%" + username +"%"));
                }
                if(StringUtils.isNotEmpty(userfullname)){
                    list.add(criteriaBuilder.like(userfullname_val,"%" + userfullname +"%"));
                }
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        },pageRequest);
    }

    //String username, userfullname多条件查询
    public Page<ProjectmanageUserinforLoginEntity> queryUserLogininfos(String username, String userfullname, PageRequest pageRequest){
        return logintimeinterface.findAll(new Specification<ProjectmanageUserinforLoginEntity>() {
            @Override
            public Predicate toPredicate(Root<ProjectmanageUserinforLoginEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> username_val = root.get("username");
                Path<String> userfullname_val =root.get("userfullname");
                Path<String> deleteFlagPath = root.get("deleteflag");
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.notEqual(deleteFlagPath,"-1"));
                if(StringUtils.isNotEmpty(username)){
                    list.add(criteriaBuilder.like(username_val,"%" + username +"%"));
                }
                if(StringUtils.isNotEmpty(userfullname)){
                    list.add(criteriaBuilder.like(userfullname_val,"%" + userfullname +"%"));
                }
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        },pageRequest);
    }
}

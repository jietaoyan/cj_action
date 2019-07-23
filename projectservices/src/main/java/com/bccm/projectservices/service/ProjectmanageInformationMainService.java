package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.ProjectmanageInformationMainInterface;
import com.bccm.projectservices.Interface.ProjectmanageInformationPermissionInterface;
import com.bccm.projectservices.entity.ProjectmanageInformationMainEntity;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class ProjectmanageInformationMainService {

    public static final Logger logger = LoggerFactory.getLogger(ProjectmanageInformationMainService.class);

    @Autowired
    ProjectmanageInformationMainInterface projectmanageinterface;

    @Autowired
    ProjectmanageInformationPermissionInterface Projectperminssoninterface;

    //默认显示projectpermisson表FKGUID=projectmanege表GUID的结果，项目管理首页
    public List<ProjectmanageInformationMainEntity> getProjectmanageInformation(String selfname){
        //拼接in查询
        List<String> ids = Projectperminssoninterface.getAllFkguid(selfname);
        if(ids != null && ids.size() > 0){//如果permission有数据才查询
        return projectmanageinterface.findAll(new Specification<ProjectmanageInformationMainEntity>() {
            @Override
            public Predicate toPredicate(Root<ProjectmanageInformationMainEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Path<String> deleteFlagPath = root.get("deleteflag");
                    List<Predicate> list = new ArrayList<Predicate>();
                    list.add(criteriaBuilder.notEqual(deleteFlagPath,"-1"));
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("guid"));
                    for (String id : ids) {
                        in.value(id);
                    }
                    list.add(in);
                    Predicate[] arrayPredicates = new Predicate[list.size()];
                    return criteriaBuilder.and(list.toArray(arrayPredicates));

                }
            });
        }
        else{
            return null;//如果permission里面没有数据就反馈空
        }
    }
    //String prjname, String inputerfullname, String type,多条件查询
    public Map<String,Object> queryProjectInformations(String prjname, String inputerfullname, String type,boolean isproject,String selfname){
        Map<String,Object> result = new HashMap<String, Object>();
        //如果是来自项目管理首页的查询就拼接in查询
        if(isproject){
            List<String> ids = Projectperminssoninterface.getAllFkguid(selfname);
            List<ProjectmanageInformationMainEntity> project = projectmanageinterface.findAll(new Specification<ProjectmanageInformationMainEntity>() {
                @Override
                public Predicate toPredicate(Root<ProjectmanageInformationMainEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Path<String> prjname_val = root.get("prjname");
                    Path<String> inputerfullname_val =root.get("inputerfullname");
                    Path<String> type_val =root.get("type");
                    Path<String> deleteFlagPath = root.get("deleteflag");
                    List<Predicate> list = new ArrayList<Predicate>();
                    list.add(criteriaBuilder.notEqual(deleteFlagPath,"-1"));
                    if(StringUtils.isNotEmpty(prjname)){
                        list.add(criteriaBuilder.like(prjname_val,"%" + prjname +"%"));
                    }
                    if(StringUtils.isNotEmpty(inputerfullname)){
                        list.add(criteriaBuilder.like(inputerfullname_val,"%" + inputerfullname +"%"));
                    }
                    if(StringUtils.isNotEmpty(type)){
                        list.add(criteriaBuilder.like(type_val,"%" + type +"%"));
                    }
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("guid"));
                    if(ids != null && ids.size() > 0){
                        for (String id : ids) {
                            in.value(id);
                        }
                        list.add(in);
                    }else{
                        in.value("");
                        list.add(in);
                    }
                    Predicate[] arrayPredicates = new Predicate[list.size()];
                    return criteriaBuilder.and(list.toArray(arrayPredicates));
                }
            });
            result.put("project",project);
            //return result;
        }else  if(!isproject){//如果不是来自项目管理首页的查询就拼接in查询
            String prjnameparam = "%"+prjname+"%";
            String inputerfullnameparam = "%"+inputerfullname+"%";
            String typeparam = "%"+type+"%";
            List<Map<String,Object>> shareproject = Projectperminssoninterface.getSharelistFkguids(selfname,prjnameparam,inputerfullnameparam,typeparam);
            result.put("shareproject",shareproject);
            //return result;
        }
        return result;
    }

    //根据id删除的方法
    public void deleteById(String id,boolean isAuthor,String userName){

        try {
            // String[] strings = column2.split("_");
            //判断是否为纯数字 防止sql注入
            // for(String userId : strings){
            //    Integer.parseInt(userId);
            //判断是否是作者
            boolean isauthor = isAuthor;
            if(isauthor){//相等说明操作的是作者
                projectmanageinterface.deleteById(id);
                Projectperminssoninterface.deleteById(id);
            }else{//如果不相等，说明不是作者，是分享人,删除shared里面的该记录
                Projectperminssoninterface.deleteBySharedName(id,userName+",");
            }
            // }
        }
        catch (Exception e){
            //e.printStackTrace();
            logger.error(e.getMessage(),e);
        }
    }
}

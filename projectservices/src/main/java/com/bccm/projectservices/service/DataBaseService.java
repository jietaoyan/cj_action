package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.BagClearDatabaseInterface;
import com.bccm.projectservices.Interface.ListDatabaseInterface;
import com.bccm.projectservices.Interface.ListProjectEditInterface;
import com.bccm.projectservices.Interface.MaterialDatabaseInterface;
import com.bccm.projectservices.entity.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseService {

    static List<ListDatabase> listDatabases=new ArrayList<ListDatabase>();

    static List<BagClearDatabase> bagClearDatabases=new ArrayList<BagClearDatabase>();
    @Autowired
    ListDatabaseInterface listDatabaseInterface;

    @Autowired
    BagClearDatabaseInterface bagClearDatabaseInterface;

    @Autowired
    MaterialDatabaseInterface materialDatabaseInterface;

    public Page<ListDatabase> getAllByCond(String flag, String name, String unit, String price, PageRequest pageRequest,String standname,String treename) {

        return listDatabaseInterface.findAll(new Specification<ListDatabase>() {
            @Override
            public Predicate toPredicate(Root<ListDatabase> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> flag_val = root.get("flag");
                Path<String> name_val = root.get("name");
                Path<String> unit_val = root.get("unit");
                Path<String> price_val = root.get("price");
                Path<String> state_val = root.get("state");
                Path<String> standardname_val = root.get("standardName");
                Path<String> parentid_val = root.get("parentid");
                List<Predicate> list = new ArrayList<Predicate>();
                List<String> allSonId=new ArrayList<String>();
                if(StringUtils.isNotEmpty(treename)) {
                   allSonId = getAllBySelected(treename);
                }
                if (StringUtils.isNotEmpty(flag)) {
                    list.add(criteriaBuilder.like(flag_val, "%" + flag + "%"));
                }
                if (StringUtils.isNotEmpty(name)) {
                    list.add(criteriaBuilder.like(name_val, "%" + name + "%"));
                }
                if (StringUtils.isNotEmpty(unit)) {
                    list.add(criteriaBuilder.like(unit_val, "%" + unit + "%"));
                }
                if (StringUtils.isNotEmpty(price)) {
                    list.add(criteriaBuilder.like(price_val, "%" + price + "%"));
                }
                if (allSonId!=null&&allSonId.size()>0) {
                    CriteriaBuilder.In<String> ids=criteriaBuilder.in(parentid_val);
                    for(String id:allSonId)
                    {
                        ids.value(id);
                    }
                    list.add(ids);
                }
                if (StringUtils.isNotEmpty(standname)) {
                    list.add(criteriaBuilder.like(standardname_val, "%" + standname + "%"));
                }
                list.add(criteriaBuilder.equal(state_val, "1"));
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        }, pageRequest);
    }
//展示下拉框中数据
    public Page<ListDatabase> getSelectListInfoInformationMainInfo(PageRequest pageRequest){
        return listDatabaseInterface.findAll(new Specification<ListDatabase>() {
            @Override
            public Predicate toPredicate(Root<ListDatabase> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> statePath = root.get("state");
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.equal(statePath,"0"));
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        },pageRequest);
    }
//展示表格空间的数据
    public Page<ListDatabase> getAllListInfoInformationMainInfo(PageRequest pageRequest){
        return listDatabaseInterface.findAll(new Specification<ListDatabase>() {
            @Override
            public Predicate toPredicate(Root<ListDatabase> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> statePath = root.get("state");
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.equal(statePath,"1"));
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        },pageRequest);
    }
    /**
     * 获取某节点下所有的子节点的清单列表
     *
     */
    public List<String> getAllBySelected(String id)
    {
        List<ListDatabase> priceDetailListEntitieList= new ArrayList<>();
        List<String> allSonId=new ArrayList<String>();
        allSonId.add(id);
        listDatabases=new ArrayList<>();
        List<ListDatabase> listProjectEditList=listDatabaseInterface.findAll();
        List<ListDatabase> allSonProjectEditLists=findAllSon(listProjectEditList,id);
        if(allSonProjectEditLists!=null&&allSonProjectEditLists.size()>0)
        {
        for(ListDatabase allSonDatabase:allSonProjectEditLists)
        {
            allSonId.add(allSonDatabase.getGuid());
        }}
        return allSonId;
    }
    public List<ListDatabase> findAllSon(List<ListDatabase> listProjectEdits,String id)
    {
        for(ListDatabase listProjectEdit:listProjectEdits)
        {
            if(listProjectEdit.getParentid()!=null&&listProjectEdit.getParentid().equals(id)&&"0".equals(listProjectEdit.getState()))
            {
                findAllSon(listProjectEdits,listProjectEdit.getGuid());
                listDatabases.add(listProjectEdit);
            }
        }
        return listDatabases;
    }




    //清包数据库展示
    public Page<BagClearDatabase> getBagInfoByCond(String flag, String name, String unit, String price, PageRequest pageRequest,String projectName,String treename,String relateMaterialName1,String relateMaterialName2,String pricefrom) {

        return bagClearDatabaseInterface.findAll(new Specification<BagClearDatabase>() {
            @Override
            public Predicate toPredicate(Root<BagClearDatabase> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> flag_val = root.get("flag");
                Path<String> name_val = root.get("name");
                Path<String> unit_val = root.get("unit");
                Path<String> price_val = root.get("price");
                Path<String> state_val = root.get("state");
                Path<String> relateMaterialName_val = root.get("relatematerialname");
                Path<String> relateMaterialName2_val = root.get("relatematerialname2");
                Path<String> pricefrom_val = root.get("pricefrom");
                Path<String> parentid_val = root.get("parentid");
                Path<String> projectName_val = root.get("projectname");
                List<Predicate> list = new ArrayList<Predicate>();
                List<String> allSonId=new ArrayList<String>();
                if(StringUtils.isNotEmpty(treename))
                {
                    allSonId=getAllBagBySelected(treename);
                }

                if (StringUtils.isNotEmpty(flag)) {
                    list.add(criteriaBuilder.like(flag_val, "%" + flag + "%"));
                }
                if (StringUtils.isNotEmpty(name)) {
                    list.add(criteriaBuilder.like(name_val, "%" + name + "%"));
                }
                if (StringUtils.isNotEmpty(unit)) {
                    list.add(criteriaBuilder.like(unit_val, "%" + unit + "%"));
                }
                if (StringUtils.isNotEmpty(price)) {
                    list.add(criteriaBuilder.like(price_val, "%" + price + "%"));
                }
                if (StringUtils.isNotEmpty(pricefrom)) {
                    list.add(criteriaBuilder.like(pricefrom_val, "%" + pricefrom + "%"));
                }
                if (StringUtils.isNotEmpty(relateMaterialName1)) {
                    list.add(criteriaBuilder.like(relateMaterialName_val, "%" + relateMaterialName1 + "%"));
                }
                if (StringUtils.isNotEmpty(relateMaterialName2)) {
                    list.add(criteriaBuilder.like(relateMaterialName2_val, "%" + relateMaterialName2 + "%"));
                }
                if (allSonId!=null&&allSonId.size()>0) {
                    CriteriaBuilder.In<String> ids=criteriaBuilder.in(parentid_val);
                    for(String id:allSonId)
                    {
                        ids.value(id);
                    }
                    list.add(ids);
                }
                if (StringUtils.isNotEmpty(projectName)) {
                    list.add(criteriaBuilder.like(projectName_val, "%" + projectName + "%"));
                }
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        }, pageRequest);
    }
    //展示下拉框中数据
    public Page<BagClearDatabase> getSelectBagInfoInformationMainInfo(PageRequest pageRequest){
        return bagClearDatabaseInterface.findAll(new Specification<BagClearDatabase>() {
            @Override
            public Predicate toPredicate(Root<BagClearDatabase> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> statePath = root.get("state");
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.equal(statePath,"0"));
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        },pageRequest);
    }
    //展示表格空间的数据
    public Page<BagClearDatabase> getAllBagInfoInformationMainInfo(PageRequest pageRequest){
        return bagClearDatabaseInterface.findAll(new Specification<BagClearDatabase>() {
            @Override
            public Predicate toPredicate(Root<BagClearDatabase> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> statePath = root.get("state");
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.equal(statePath,"1"));
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        },pageRequest);
    }
    public List<String> getAllBagBySelected(String id)
    {
        List<BagClearDatabase> priceDetailListEntitieList= new ArrayList<>();
        List<String> allSonId=new ArrayList<String>();
        allSonId.add(id);
        bagClearDatabases=new ArrayList<>();
        List<BagClearDatabase> listProjectEditList=bagClearDatabaseInterface.findAll();
        List<BagClearDatabase> allSonProjectEditLists=findBagAllSon(listProjectEditList,id);
        if(allSonProjectEditLists!=null&&allSonProjectEditLists.size()>0)
        {
            for(BagClearDatabase allSonDatabase:allSonProjectEditLists)
            {
                allSonId.add(allSonDatabase.getGuid());
            }
        }

        return allSonId;
    }
    public List<BagClearDatabase> findBagAllSon(List<BagClearDatabase> listProjectEdits,String id)
    {
        for(BagClearDatabase listProjectEdit:listProjectEdits)
        {
            if(listProjectEdit.getParentid()!=null&&listProjectEdit.getParentid().equals(id)&&"0".equals(listProjectEdit.getState()))
            {
                findBagAllSon(listProjectEdits,listProjectEdit.getGuid());
                bagClearDatabases.add(listProjectEdit);
            }
        }
        return bagClearDatabases;
    }

    //清包数据库展示
    public Page<MaterialDatabase> getMaterialInfoByCond(String flag, String name, String unit, String price, PageRequest pageRequest,String pricefrom) {

        return materialDatabaseInterface.findAll(new Specification<MaterialDatabase>() {
            @Override
            public Predicate toPredicate(Root<MaterialDatabase> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> flag_val = root.get("flag");
                Path<String> name_val = root.get("name");
                Path<String> unit_val = root.get("unit");
                Path<String> price_val = root.get("price");
                Path<String> state_val = root.get("state");
                Path<String> pricefrom_val = root.get("pricefrom");
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotEmpty(flag)) {
                    list.add(criteriaBuilder.like(flag_val, "%" + flag + "%"));
                }
                if (StringUtils.isNotEmpty(name)) {
                    list.add(criteriaBuilder.like(name_val, "%" + name + "%"));
                }
                if (StringUtils.isNotEmpty(unit)) {
                    list.add(criteriaBuilder.like(unit_val, "%" + unit + "%"));
                }
                if (StringUtils.isNotEmpty(price)) {
                    list.add(criteriaBuilder.like(price_val, "%" + price + "%"));
                }

                if (StringUtils.isNotEmpty(pricefrom)) {
                    list.add(criteriaBuilder.like(pricefrom_val, "%" + pricefrom + "%"));
                }
                list.add(criteriaBuilder.equal(state_val, "1"));
                Predicate[] arrayPredicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arrayPredicates));
            }
        }, pageRequest);
    }
}

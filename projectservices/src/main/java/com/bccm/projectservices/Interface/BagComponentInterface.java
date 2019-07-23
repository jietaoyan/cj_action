package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.BagComponentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BagComponentInterface extends JpaRepository<BagComponentsEntity,String>,JpaSpecificationExecutor<BagComponentsEntity> {

    List<BagComponentsEntity> findAllByFlag(String flag);

    List<BagComponentsEntity> findAllByRelatepackid(String relatepackid);


}

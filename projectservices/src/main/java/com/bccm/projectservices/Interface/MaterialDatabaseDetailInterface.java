package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.MaterialDatabaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaterialDatabaseDetailInterface extends JpaRepository<MaterialDatabaseDetail,String> {

    List<MaterialDatabaseDetail> findAllByFlag(String flag);

}

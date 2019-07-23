package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.ListProjectEdit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.transaction.Transactional;
import java.util.List;

public interface ListProjectEditInterface extends JpaRepository<ListProjectEdit,String> {
    @Query(value = "delete from ListProjectEdit m where m.guid = ?1")
    @Modifying
    @Transactional
    void deleteFromId(String id);

    List<ListProjectEdit> findAllByParentid(String ParentId);

    ListProjectEdit findByGuid(String guid);

    List<ListProjectEdit> findAllByGuid(String guid);

    List<ListProjectEdit> findAllByProjectid(String projectid);
}

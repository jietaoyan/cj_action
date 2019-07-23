package com.bccm.projectservices.sqlInterface;

import com.bccm.projectservices.sqlEntity.EngineeringGuidanceBridge;
import com.bccm.projectservices.sqlEntity.EngineeringGuidanceTunnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Engineeringbridgeinterface extends JpaRepository<EngineeringGuidanceBridge, Long>,JpaSpecificationExecutor<EngineeringGuidanceBridge>{



    @Query(nativeQuery = true, value = "select * from EngineeringGuidance_bridge a WHERE (?1='' OR a.ProjectName like ?1) AND (?2='' OR a.CONSTRUCTIONSITE like ?2) AND (?3='' OR a.PRICELEVELYEAR like ?3) ORDER BY a.SYSID DESC  OFFSET ?4 ROWS FETCH NEXT ?5 ROWS ONLY")
    List<EngineeringGuidanceBridge> getBridge(String ProjectName, String CONSTRUCTIONSITE, String date, int offNum, int pageSize);


    @Query(nativeQuery = true, value = "select COUNT(GUID) from EngineeringGuidance_bridge a WHERE (?1='' OR a.ProjectName like ?1) AND (?2='' OR a.CONSTRUCTIONSITE like ?2) AND (?3='' OR a.PRICELEVELYEAR like ?3)")
    int getBridgeCount(String ProjectName, String CONSTRUCTIONSITE, String date);

}

package com.bccm.projectservices.sqlInterface;

import com.bccm.projectservices.sqlEntity.EngineeringGuidanceCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EngineeringCompanyinterface extends JpaRepository<EngineeringGuidanceCompany, Long>,JpaSpecificationExecutor<EngineeringGuidanceCompany>{


    //@Query(nativeQuery = true, value = "select * from EngineeringGuidance_tunnel a where 1=1 and ((?1 is not null) or (a.ProjectName like ?1)) and ((?2 is not null) or (a.CONSTRUCTIONSITE like ?2))  and ((?3 is not null) or (CONVERT (VARCHAR ,a.PRICELEVELYEAR)=CONVERT (VARCHAR ,?3)))")
    @Query(nativeQuery = true, value = "select * from EngineeringGuidance_company a WHERE (?1='' OR a.PROJECTNAME like ?1) AND (?2='' OR a.LABORSUBCONTRACTOR like ?2) AND (?3='' OR a.SUBCONTRACTORTYPE like ?3) ORDER BY a.SYSID DESC  OFFSET ?4 ROWS FETCH NEXT ?5 ROWS ONLY")
    List<EngineeringGuidanceCompany> getCompany(String ProjectName, String CONSTRUCTIONSITE, String date, int offNum, int pageSize);


    @Query(nativeQuery = true, value = "select COUNT(GUID) from EngineeringGuidance_company a WHERE (?1='' OR a.PROJECTNAME like ?1) AND (?2='' OR a.LABORSUBCONTRACTOR like ?2) AND (?3='' OR a.SUBCONTRACTORTYPE like ?3)")
    int getCompanyCount(String ProjectName, String LABORSUBCONTRACTOR, String SUBCONTRACTORTYPE);

}

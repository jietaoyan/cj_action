package com.bccm.projectservices.sqlInterface;

import com.bccm.projectservices.sqlEntity.EngineeringGuidanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EngineeringGuidanceInterface extends JpaRepository<EngineeringGuidanceEntity, String>
        , JpaSpecificationExecutor<EngineeringGuidanceEntity> {

    @Query(nativeQuery = true,
            value = "SELECT p.* " +
                    "FROM EngineeringGuidance p " +
                    "WHERE p.type in (?10) AND (?1='' OR p.professional=?1) AND (?2='' OR p.datasource=?2) AND (?3='' OR p.subcontractingmode=?3) AND (?4='%%' OR p.projectsite LIKE ?4) " +
                    "AND (?5='%%' OR p.Namesubcontractproject LIKE ?5) AND (?6='%%' OR p.nameproject LIKE ?6) AND (?7='%%' OR p.mterialname LIKE ?7) " +
                    "ORDER BY p.sysid DESC " +
                    "OFFSET ?8 ROWS " +
                    "FETCH NEXT ?9 ROWS ONLY")
    List<EngineeringGuidanceEntity> findAllPrjBagclears(String professional,String datasource,String subcontractingmode,String projectsite
            ,String Namesubcontractproject,String nameproject,String mterialname, int offNum, int pageSize, String[] types );


    @Query(nativeQuery = true,
            value = "SELECT COUNT(GUID) " +
                    "FROM EngineeringGuidance " +
                    "WHERE type in (?8) AND (?1='' OR professional=?1) AND (?2='' OR datasource=?2) AND (?3='' OR subcontractingmode=?3) AND (?4='%%' OR projectsite LIKE ?4) " +
                    "AND (?5='%%' OR Namesubcontractproject LIKE ?5) AND (?6='%%' OR nameproject LIKE ?6) AND (?7='%%' OR mterialname LIKE ?7) ")
    int findCountPrjBagclears(String professional,String datasource,String subcontractingmode,String projectsite
            ,String Namesubcontractproject,String nameproject,String mterialname, String[] types);


    @Query(nativeQuery = true,
            value = "SELECT p.* " +
                    "FROM EngineeringGuidance p " +
                    "WHERE  ((?1='%%' OR p.Namesubcontractproject LIKE ?1) or (?1='%%' OR p.nameproject LIKE ?1)) and  (?2='%%' OR p.professional LIKE ?2) AND p.type='1'" +
                    "ORDER BY p.sysid DESC " +
                    "OFFSET ?3 ROWS " +
                    "FETCH NEXT ?4 ROWS ONLY")
    List<EngineeringGuidanceEntity> findAllPrjBagclearList(String professional,String professions, int offNum, int pageSize );

    @Query(nativeQuery = true,
            value = "SELECT COUNT(GUID) " +
                    "FROM EngineeringGuidance p " +
                    "WHERE  ((?1='%%' OR p.Namesubcontractproject LIKE ?1) or (?1='%%' OR p.nameproject LIKE ?1)) and  (?2='%%' OR p.professional LIKE ?2)  AND p.type='1'")
    int findCountPrjBagclearList(String professional,String professions);

    @Query(nativeQuery = true,
            value = "SELECT p.* " +
                    "FROM EngineeringGuidance p " +
                    "WHERE  ((?1='%%' OR p.Namesubcontractproject LIKE ?1) or (?1='%%' OR p.nameproject LIKE ?1)) and  (?2='%%' OR p.professional LIKE ?2) AND p.type!='1'" +
                    "ORDER BY p.sysid DESC " +
                    "OFFSET ?3 ROWS " +
                    "FETCH NEXT ?4 ROWS ONLY")
    List<EngineeringGuidanceEntity> finddiffPrjBagclearList(String professional,String professions, int offNum, int pageSize );

    @Query(nativeQuery = true,
            value = "SELECT COUNT(GUID) " +
                    "FROM EngineeringGuidance p " +
                    "WHERE  ((?1='%%' OR p.Namesubcontractproject LIKE ?1) or (?1='%%' OR p.nameproject LIKE ?1)) and  (?2='%%' OR p.professional LIKE ?2)  AND p.type!='1'")
    int finddiffCountPrjBagclearList(String professional,String professions);


    @Query(value = "select professional from EngineeringGuidanceEntity where professional<>'' and type in (?1) group by professional")
    List<String> findAllProfessional(String[] type);

    @Query(value = "select datasource from EngineeringGuidanceEntity where datasource<>'' and type in (?1)  group by datasource")
    List<String> findAllDatasource(String[] type);

    @Query(value = "select subcontractingmode from EngineeringGuidanceEntity where subcontractingmode<>'' and type in (?1)  group by subcontractingmode")
    List<String> findAllSubcontractingmode(String[] type);

}

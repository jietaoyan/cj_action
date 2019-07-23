package com.bccm.projectservices.controller;


import com.bccm.projectservices.Interface.ListProjectEditInterface;
import com.bccm.projectservices.Interface.PriceDetailListInterface;
import com.bccm.projectservices.Interface.ProjectmanageInformationMainInterface;
import com.bccm.projectservices.Interface.ProjectmanageInformationPermissionInterface;
import com.bccm.projectservices.entity.ListProjectEdit;
import com.bccm.projectservices.entity.PriceDetailListEntity;
import com.bccm.projectservices.entity.ProjectmanageInformationMainEntity;
import com.bccm.projectservices.entity.ProjectmanageInformationPermissionEntity;
import com.bccm.projectservices.service.ProjectExcelExport;
import com.bccm.projectservices.service.ProjectmanageInformationMainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

@Api(value = "ProjectmanageInformationMain")
@RestController
public class ProjectmanageInformationMainController {
    @Autowired
    ProjectmanageInformationMainService projectmanageInforService;

    @Autowired
    ProjectmanageInformationMainInterface projectInterface;

    @Autowired
    public ListProjectEditInterface listProjectEditInterface;

    @Autowired
    public ProjectmanageInformationPermissionInterface projectmanegepermissinterface;

    @Autowired
    ProjectExcelExport projectExcelExport;

    @Autowired
    public PriceDetailListInterface priceinterface;
    //默认显示所有项目信息  项目管理首页
    @ApiOperation(value = "默认显示所有项目信息（不分页）")
    @GetMapping(value = "/ProjectmanageInfor/getProjectmanageInfor", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<ProjectmanageInformationMainEntity> ProjectInformationMainInfo()throws Exception{
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        List<ProjectmanageInformationMainEntity> ProjectInformations = projectmanageInforService.getProjectmanageInformation(userName);
        return ProjectInformations;
    }

    //默认显示所有项目信息  打开分享项目列表页面
    @ApiOperation(value = "默认显示所有项目信息（不分页）")
    @GetMapping(value = "/ProjectmanageInfor/getProjectmanageListInfor", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> ProjectListInfo()throws Exception{
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        //List<Map<String,Object>> issharelist = projectInterface.getAllAndShare2(userName);
        List<Map<String,Object>> issharelist = projectInterface.getAllAndShare(userName);
        Map<String,Object> result = new HashMap<String, Object >();
        result.put("project",issharelist);
        //Iterator iter = ProjectInformations.iterator();
        return result;
    }
    //删除
    @ApiOperation(value = "删除数据")
    @PostMapping(value = "/ProjectmanageInfor/deleteProjectmanageInfor", produces = {"application/json;charset=UTF-8"})
    public String deleteproInformation(@RequestBody List<ProjectmanageInformationMainEntity> projectInfos)throws Exception{
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        if(projectInfos!=null){
        Iterator<ProjectmanageInformationMainEntity> iterators = projectInfos.iterator();
        while(iterators.hasNext()) {
            ProjectmanageInformationMainEntity projectInfo = iterators.next();//获取当前遍历的元素
            String author = projectInfo.getInputername();
            String fkguid = projectInfo.getGuid();
            if (userName.equals(author)) {
                //相等，说明执行删除操作的作者
                projectmanageInforService.deleteById(fkguid, true, userName);
//                projectInterface.deleteAll(projectInfos);
            } else {
                //不相等，说明执行删除操作的不是作者
                projectmanageInforService.deleteById(fkguid, false, userName);
            }
            }
        }else{
        return "defeat";
    }
        return "defeat";
    }


    //删除分享项目列表中的数据
    @ApiOperation(value = "删除数据")
    @PostMapping(value = "/ProjectmanageInfor/deleteSharelist", produces = {"application/json;charset=UTF-8"})
    public String deleteSharelist(@RequestBody List<ProjectmanageInformationMainEntity> projectInfos)throws Exception{
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        if(projectInfos!=null){
            Iterator<ProjectmanageInformationMainEntity> iterators = projectInfos.iterator();
            List<ProjectmanageInformationPermissionEntity>  pipelist = new ArrayList<ProjectmanageInformationPermissionEntity>();
            while(iterators.hasNext()){
                ProjectmanageInformationMainEntity projectInfo = iterators.next();//获取当前遍历的元素
                ProjectmanageInformationPermissionEntity pipe = new ProjectmanageInformationPermissionEntity();
                String Fkguid = projectInfo.getGuid();
                projectmanegepermissinterface.deleteShareName(Fkguid,userName+",");
            }
            return "success";
        }
        return "defeat";
    }

  //新增
    @ApiOperation(value = "新建项目")
    @PostMapping(value = "/ProjectmanageInfor/saveProjectmanage",produces = {"application/json;charset=UTF-8"})
    public ProjectmanageInformationMainEntity saveNewProject(@RequestBody ProjectmanageInformationMainEntity projectInfo)throws Exception{

        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        //设置用户删除标记默认0
        projectInfo.setDeleteflag(1);
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        //String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        //projectInfo.setInputerfullname(userName);//这里是错误的，获取不到全名
        projectInfo.setInputername(userName);
        projectInfo.setInputdate(nowTime);
        projectInfo.setUpdatebyuserfullname(userName);
        projectInfo.setUpdatedte(nowTime);
        String newGuid = UUID.randomUUID().toString().replace("-","");
        //projectInfo.setGuid(projectmanageInforService.makeGuid());
        projectInfo.setGuid(newGuid);
        String projectId =  projectInterface.save(projectInfo).getId().toString();

        //保存到perminssion
        ProjectmanageInformationPermissionEntity  pipe = new ProjectmanageInformationPermissionEntity();
        pipe.setFkguid(newGuid);
        pipe.setDeleteflag(1);
        pipe.setInputerfullname(projectInfo.getInputerfullname());//这里是错误的，获取不到全名
        pipe.setInputername(userName);
        pipe.setGuid(UUID.randomUUID().toString().replace("-",""));
        projectmanegepermissinterface.save(pipe);

        //保存到造价清单
        if(projectInfo.getLayerid()!=null) {
            if (projectInfo.getLayerid().toString().equals("2")) {
                ListProjectEdit listProjectEdits = new ListProjectEdit();
                //String new_Guid = UUID.randomUUID().toString().replace("-", "");
                listProjectEdits.setGuid(newGuid);
                listProjectEdits.setParentid("0");
                listProjectEdits.setProjectid(newGuid);
                listProjectEdits.setProjectname(projectInfo.getPrjname());
                listProjectEdits.setAmount(new BigDecimal(0));
                listProjectEditInterface.save(listProjectEdits);
            }
        }
        return projectInfo;

    }

    //修改
    @ApiOperation(value = "修改项目")
    @PostMapping(value = "/ProjectmanageInfor/updateProjectmanage",produces = {"application/json;charset=UTF-8"})
    public String updateNewProject(@RequestBody List<ProjectmanageInformationMainEntity> projectInfos)throws Exception{

        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        //设置用户删除标记默认0
//        projectInfo.setDeleteflag(1);
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
//        projectInfo.setUpdatebyuserfullname(userName);
//        projectInfo.setUpdatedte(nowTime);
//        String projectId =  projectInterface.save(projectInfo).getId().toString();
        projectInterface.saveAll(projectInfos);
        return "success";

    }

    //String prjname, String inputerfullname, String type,多条件查询
    @ApiOperation(value = "按条件查询所有项目信息（不分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prjname", value = "项目名称", required = false),
            @ApiImplicitParam(name = "inputerfullname", value = "作者", required = false),
            @ApiImplicitParam(name = "type", value = "类型", required = false)
    })

    @GetMapping(value = "/ProjectmanageInfor/searchProjectmanageInfor", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String,Object> searchProjectInformationMainInfo(@RequestParam(value = "prjname",required =false)String prjname, @RequestParam(value = "inputerfullname",required =false)String inputerfullname, @RequestParam(value = "type",required =false)String type
            ,@RequestParam(value = "isproject",required =false)boolean isproject)throws Exception{
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        //PageRequest pageable = new PageRequest(page, size, sort);
        Map<String,Object> projectInformations = projectmanageInforService.queryProjectInformations( prjname, inputerfullname, type ,isproject,userName);
        return projectInformations;
    }

    //复制，粘贴
    @ApiOperation(value = "复制粘贴项目")
    @PostMapping(value = "/ProjectmanageInfor/copyProjectmanage",produces = {"application/json;charset=UTF-8"})
    public String copyNewProject(@RequestBody List<ProjectmanageInformationMainEntity> projects)throws Exception{

        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        List<ProjectmanageInformationMainEntity> projectInfos = projects;
        if(projectInfos!=null){
        Iterator<ProjectmanageInformationMainEntity> iterators = projectInfos.iterator();
        List<ProjectmanageInformationPermissionEntity>  pipelist = new ArrayList<ProjectmanageInformationPermissionEntity>();
        while(iterators.hasNext()){
            ProjectmanageInformationPermissionEntity pipe = new ProjectmanageInformationPermissionEntity();
            ProjectmanageInformationMainEntity projectInfo = iterators.next();//获取当前遍历的元素
            //设置用户删除标记默认0
            projectInfo.setDeleteflag(1);
            String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
            projectInfo.setInputerfullname(projectInfo.getInputerfullname());
            projectInfo.setInputername(userName);
            projectInfo.setInputdate(nowTime);
            projectInfo.setUpdatebyuserfullname(userName);
            projectInfo.setUpdatedte(nowTime);
            String newGuid = "";
//                //projectInfo.setGuid(projectmanageInforService.makeGuid());
            if(projectInfo.getLayerid().toString().equals("2")){
                newGuid = projectInfo.getGuid();
            }else{
                newGuid = UUID.randomUUID().toString().replace("-","");
            }
            projectInfo.setGuid(newGuid);


            //保存到projectpermission
            pipe.setFkguid(newGuid);
            pipe.setDeleteflag(1);
            pipe.setInputerfullname(projectInfo.getInputerfullname());
            pipe.setInputername(userName);
            pipe.setGuid(UUID.randomUUID().toString().replace("-",""));
            pipelist.add(pipe);

            //保存到造价清单
            if(projectInfo.getLayerid()!=null) {
                if (projectInfo.getLayerid().toString().equals("2")) {
                    ListProjectEdit listProjectEdits = new ListProjectEdit();
                    listProjectEdits.setGuid(newGuid);
                    listProjectEdits.setParentid("0");
                    listProjectEdits.setProjectid(newGuid);
                    listProjectEdits.setProjectname(projectInfo.getPrjname());
                    listProjectEdits.setAmount(new BigDecimal(0));
                    listProjectEditInterface.save(listProjectEdits);
                }
            }
            }
            projectInterface.saveAll(projectInfos);
            projectmanegepermissinterface.saveAll(pipelist);
        }
        return "success";
    }

    //分享
    @ApiOperation(value = "分享")
    @PostMapping(value = "/ProjectmanageInfor/shareProjectperminsson",produces = {"application/json;charset=UTF-8"})
    public String shareProjectperminsson(@RequestBody Map<String ,Object> infos)throws Exception{

        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsobject = new JSONObject(infos);
        String sharename = "";
        //List<ProjectmanageUserinforEntity> userinfos = new ArrayList<>();
        JSONArray  userinfos_jsonArray = jsobject.getJSONArray("userinfos");
//        for (int i = 0; i < userinfos_jsonArray.length(); i++) {
//            JSONObject role=userinfos_jsonArray.getJSONObject(i);
//            String username = role.getString("username");
//            String userfullname = role.getString("userfullname");
//            sharename = sharename + username+",";
//        }
        if(userinfos_jsonArray!=null){
            List<ProjectmanageInformationPermissionEntity>  pipelist = new ArrayList<ProjectmanageInformationPermissionEntity>();
            JSONArray  projects_jsonArray = jsobject.getJSONArray("projects");
            for (int i = 0; i < projects_jsonArray.length(); i++) {
                JSONObject role=projects_jsonArray.getJSONObject(i);
                String Fkguid = role.getString("guid");
                //ProjectmanageInformationMainEntity project = new ProjectmanageInformationMainEntity();
                //project.setGuid(guid);
                String guid = projectmanegepermissinterface.getGuidByFkguid(Fkguid);

                for (int j = 0; j < userinfos_jsonArray.length(); j++) {
                    JSONObject roleuser=userinfos_jsonArray.getJSONObject(j);
                    String username = roleuser.getString("username");
                    String userfullname = roleuser.getString("userfullname");
                    //sharename = sharename + username+",";
                    projectmanegepermissinterface.addShareName(guid,username+",",username);
                }
            }
            return "success";
        }else {
            return "defeat";
        }
    }

    //添加到项目管理
    @ApiOperation(value = "保存到造价清单")
    @PostMapping(value = "/ProjectmanageInfor/saveProjectperminsson",produces = {"application/json;charset=UTF-8"})
    public String pushProjectperminsson(@RequestBody List<ProjectmanageInformationMainEntity> projects)throws Exception{

        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        List<ProjectmanageInformationMainEntity> projectInfos = projects;
        if(projectInfos!=null){
            Iterator<ProjectmanageInformationMainEntity> iterators = projectInfos.iterator();
            List<ProjectmanageInformationPermissionEntity>  pipelist = new ArrayList<ProjectmanageInformationPermissionEntity>();//权限列表
            List<ProjectmanageInformationMainEntity>  newprojects = new ArrayList<ProjectmanageInformationMainEntity>();//主表列表

            while(iterators.hasNext()){
                ProjectmanageInformationMainEntity projectInfo = iterators.next();//获取当前遍历的元素
                ProjectmanageInformationMainEntity newproject = new ProjectmanageInformationMainEntity();//创建新对象
                ProjectmanageInformationPermissionEntity pipe = new ProjectmanageInformationPermissionEntity();//创建新的权限对象
                String Fkguid = "";
                String newGuid = "";
                if(projectInfo.getLayerid().toString().equals("2")){
                    Fkguid = projectInfo.getColumn1();
                    newGuid = projectInfo.getGuid();
                }else{
                    Fkguid = projectInfo.getGuid();
                    newGuid = UUID.randomUUID().toString().replace("-","");
                }

                projectmanegepermissinterface.addSharedName(Fkguid,userName+",",userName);//权限表修改分享人

                //保存到主表，相当于复制粘贴
                newproject.setDeleteflag(1);
                newproject.setInputerfullname(projectInfo.getInputerfullname());
                newproject.setInputername(userName);
                newproject.setInputdate(nowTime);
                newproject.setUpdatebyuserfullname(userName);
                newproject.setUpdatedte(nowTime);
                newproject.setPrjname(projectInfo.getPrjname());
                newproject.setId(projectInfo.getId());
                newproject.setParentid(projectInfo.getParentid());
                newproject.setMoney(projectInfo.getMoney());
                newproject.setType(projectInfo.getType());
                newproject.setLayerid(projectInfo.getLayerid());
                newproject.setArea(projectInfo.getArea());

                
                newproject.setGuid(newGuid);
                newprojects.add(newproject);

                //保存到projectpermission
                pipe.setFkguid(newGuid);
                pipe.setDeleteflag(1);
                pipe.setInputerfullname(projectInfo.getInputerfullname());
                pipe.setInputername(userName);
                pipe.setGuid(UUID.randomUUID().toString().replace("-",""));
                pipelist.add(pipe);

                //保存到造价清单
//                if(projectInfo.getLayerid()!=null) {
//                    if (projectInfo.getLayerid().toString().equals("2")) {
//                        ListProjectEdit listProjectEdits = new ListProjectEdit();
//                        listProjectEdits.setGuid(newproject.getGuid());
//                        listProjectEdits.setParentid("0");
//                        listProjectEdits.setProjectname(projectInfo.getPrjname());
//                        listProjectEdits.setProjectid(newproject.getGuid());
//                        listProjectEditInterface.save(listProjectEdits);
//                    }
//                }
            }
            projectInterface.saveAll(newprojects);
            projectmanegepermissinterface.saveAll(pipelist);
            return "success";
        }else{
            return "defeat";
        }
    }


    @ApiOperation(value = "获取老舍数据")
    @PostMapping(value = "/ProjectmanageInfor/getSheRRData",produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> getSheRRData(@RequestBody String rootid)throws Exception {
        List<ListProjectEdit> data1 = listProjectEditInterface.findAllByProjectid(rootid);
        List<PriceDetailListEntity> data2 = priceinterface.findAllByRootprojectid(rootid);
        Map<String,Object> res = new HashMap<String, Object >();
        res.put("data1",data1);
        res.put("data2",data2);
        return res;
    }

    //添加到老舍
    @ApiOperation(value = "添加到测算文件")
    @PostMapping(value = "/ProjectmanageInfor/saveListProjectEditAtProject",produces = {"application/json;charset=UTF-8"})
    public String saveListProjectEditAtProject(@RequestBody List<ListProjectEdit> projects)throws Exception{
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        List<ListProjectEdit> projectInfos = projects;
        if(projectInfos!=null){
            Iterator<ListProjectEdit> iterators = projectInfos.iterator();
            List<ListProjectEdit>  list = new ArrayList<ListProjectEdit>();

            while(iterators.hasNext()){
                ListProjectEdit projectInfo = iterators.next();//获取当前遍历的元素
//                ListProjectEdit newproject = new ListProjectEdit();//创建新对象
//                String Fkguid = projectInfo.getGuid();
                projectInfo.setInputerfullname(projectInfo.getInputerfullname());
                projectInfo.setInputername(userName);
//                projectInfo.setProjectid(projectInfo.getGuid());
                list.add(projectInfo);
            }
            listProjectEditInterface.saveAll(list);
            return "success";
        }else{
            return "defeat";
        }
    }


    //添加到老舍2
    @ApiOperation(value = "添加到测算文件2")
    @PostMapping(value = "/ProjectmanageInfor/saveListProjectEditAtProject2",produces = {"application/json;charset=UTF-8"})
    public String saveListProjectEditAtProject2(@RequestBody List<PriceDetailListEntity> projects)throws Exception{

        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        List<PriceDetailListEntity> projectInfos = projects;
        if(projectInfos!=null){
            Iterator<PriceDetailListEntity> iterators = projectInfos.iterator();
            List<PriceDetailListEntity>  list = new ArrayList<PriceDetailListEntity>();

            while(iterators.hasNext()){
                PriceDetailListEntity projectInfo = iterators.next();//获取当前遍历的元素
//                ListProjectEdit newproject = new ListProjectEdit();//创建新对象
//                String Fkguid = projectInfo.getGuid();
                projectInfo.setInputerfullname(projectInfo.getInputerfullname());
                projectInfo.setInputername(userName);
//                projectInfo.setProjectid(projectInfo.getGuid());
                list.add(projectInfo);
            }
            priceinterface.saveAll(list);
            return "success";
        }else{
            return "defeat";
        }
    }
}

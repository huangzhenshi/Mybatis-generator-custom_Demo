# Mybatis-generator-custom_Demo
基于我现在做的项目自定义生成 Mapper配置文件的 修改Mybatis-generator源码后的代码，算是改良的 Generator吧，结果Mapper在 src下面的ReportMapper.xml文件，改动还是蛮多的，主要的配置文件是 generator.xml ，我测试用的数据库是 SQLSERVER。 因为我是把 分页的count 方法和 deleteById方法抽象成公共方法了，所以Mapper里面没生成这两个常用方法

纯干货啊！注意的是java项目跑起来的时候需要依赖一些jar包，反正是web常用的一些jar包，build进去才不报错。
具体使用的话，还是要根据 项目的本身情况。
一般mapper中常用的方法: 根据数据库表结构生成一个 resultMap  还有 findById   findAllPage(SQL SERVER的分页查询) save(根据字段是否nullable来生成insert条件语句） update 这几个常用mapper语句

```bash
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.nielsen.sdf.dao.ReportMapper" >
  <resultMap id="mainResultMap" type="com.nielsen.sdf.domain.Report" >
    <result column="ID" property="id" />
    <result column="REPORTING_FACT_TYPE_NAME" property="reportingFactTypeName" />
    <result column="COMMENT" property="comment" />
    <result column="PROJECT_ID" property="projectId" />
    <result column="IS_PRODUCT_APPLICABLE" property="isProductApplicable" />
    <result column="PRE_BUILD_SCRIPT" property="preBuildScript" />
    <result column="BUILD_SCRIPT" property="buildScript" />
    <result column="POST_BUILD_SCRIPT" property="postBuildScript" />
  </resultMap>

  <sql id="All_Column_List" >
    ID, REPORTING_FACT_TYPE_NAME, COMMENT, PROJECT_ID, IS_PRODUCT_APPLICABLE, PRE_BUILD_SCRIPT, 
    BUILD_SCRIPT, POST_BUILD_SCRIPT
  </sql>

  <select id="findAllPage" resultMap="mainResultMap" >
    select ID, REPORTING_FACT_TYPE_NAME, COMMENT, PROJECT_ID, IS_PRODUCT_APPLICABLE, 
    PRE_BUILD_SCRIPT, BUILD_SCRIPT, POST_BUILD_SCRIPT
    from SFA_REPORTING_FACT_TYPE_MASTER
    where ${column}=#{value} ORDER BY id offset #{startNum}-1   rows fetch next #{pageSize} rows only 
  </select>

  <select id="findById" resultMap="mainResultMap" >
    select ID, REPORTING_FACT_TYPE_NAME, COMMENT, PROJECT_ID, IS_PRODUCT_APPLICABLE, 
    PRE_BUILD_SCRIPT, BUILD_SCRIPT, POST_BUILD_SCRIPT
    from SFA_REPORTING_FACT_TYPE_MASTER
    where ID = #{id}
  </select>

  <insert id="save" parameterType="com.nielsen.sdf.domain.Report" >
    insert into SFA_REPORTING_FACT_TYPE_MASTER
    <trim prefix="(" suffix=")" suffixOverrides="," >
      REPORTING_FACT_TYPE_NAME,
      <if test="comment != null" >
        COMMENT,
      </if>
      <if test="projectId != null" >
        PROJECT_ID,
      </if>
      IS_PRODUCT_APPLICABLE,
      PRE_BUILD_SCRIPT,
      BUILD_SCRIPT,
      POST_BUILD_SCRIPT,
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides="," >
      #{reportingFactTypeName},
      <if test="comment != null" >
        #{comment},
      </if>
      <if test="projectId != null" >
        #{projectId},
      </if>
      #{isProductApplicable},
      #{preBuildScript},
      #{buildScript},
      #{postBuildScript},
    </trim>
  </insert>

  <update id="update" parameterType="com.nielsen.sdf.domain.Report" >
    update SFA_REPORTING_FACT_TYPE_MASTER
    <set >
      REPORTING_FACT_TYPE_NAME = #{reportingFactTypeName},
      <if test="comment != null" >
        COMMENT = #{comment},
      </if>
      <if test="projectId != null" >
        PROJECT_ID = #{projectId},
      </if>
      IS_PRODUCT_APPLICABLE = #{isProductApplicable},
      PRE_BUILD_SCRIPT = #{preBuildScript},
      BUILD_SCRIPT = #{buildScript},
      POST_BUILD_SCRIPT = #{postBuildScript}
    </set>
    where ID = #{id}
  </update>

</mapper>

```





```bash
package com.nielsen.sdf.domain;

public class Report {
    private Integer id;

    private String reportingFactTypeName;

    private String comment;

    private Integer projectId;

    private Boolean isProductApplicable;

    private String preBuildScript;

    private String buildScript;

    private String postBuildScript;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportingFactTypeName() {
        return reportingFactTypeName;
    }

    public void setReportingFactTypeName(String reportingFactTypeName) {
        this.reportingFactTypeName = reportingFactTypeName == null ? null : reportingFactTypeName.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Boolean getIsProductApplicable() {
        return isProductApplicable;
    }

    public void setIsProductApplicable(Boolean isProductApplicable) {
        this.isProductApplicable = isProductApplicable;
    }

    public String getPreBuildScript() {
        return preBuildScript;
    }

    public void setPreBuildScript(String preBuildScript) {
        this.preBuildScript = preBuildScript == null ? null : preBuildScript.trim();
    }

    public String getBuildScript() {
        return buildScript;
    }

    public void setBuildScript(String buildScript) {
        this.buildScript = buildScript == null ? null : buildScript.trim();
    }

    public String getPostBuildScript() {
        return postBuildScript;
    }

    public void setPostBuildScript(String postBuildScript) {
        this.postBuildScript = postBuildScript == null ? null : postBuildScript.trim();
    }
}

```
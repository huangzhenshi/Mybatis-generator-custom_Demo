# Mybatis-generator-custom_Demo
基于我现在做的项目自定义生成 Mapper配置文件的 修改Mybatis-generator源码后的代码，算是改良的 Generator吧，结果Mapper在 src下面的ReportMapper.xml文件，改动还是蛮多的，主要的配置文件是 generator.xml ，我测试用的数据库是 SQLSERVER。 因为我是把 分页的count 方法和 deleteById方法抽象成公共方法了，所以Mapper里面没生成这两个常用方法

纯干货啊！注意的是java项目跑起来的时候需要依赖一些jar包，反正是web常用的一些jar包，build进去才不报错。
具体使用的话，还是要根据 项目的本身情况。
一般mapper中常用的方法: 根据数据库表结构生成一个 resultMap  还有 findById   findAllPage(SQL SERVER的分页查询) save(根据字段是否nullable来生成insert条件语句） update 这几个常用mapper语句


<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.nielsen.sdf.dao.ReportMapper" >
  <resultMap id="mainResultMain" type="com.nielsen.sdf.domain.Report" >
    <result column="ID" property="id" jdbcType="INTEGER" />
    <result column="REPORTING_FACT_TYPE_NAME" property="reportingFactTypeName" jdbcType="VARCHAR" />
    <result column="COMMENT" property="comment" jdbcType="VARCHAR" />
    <result column="PROJECT_ID" property="projectId" jdbcType="INTEGER" />
    <result column="IS_PRODUCT_APPLICABLE" property="isProductApplicable" jdbcType="BIT" />
    <result column="PRE_BUILD_SCRIPT" property="preBuildScript" jdbcType="CLOB" />
    <result column="BUILD_SCRIPT" property="buildScript" jdbcType="CLOB" />
    <result column="POST_BUILD_SCRIPT" property="postBuildScript" jdbcType="CLOB" />
  </resultMap>

  <sql id="All_Column_List" >
    ID, REPORTING_FACT_TYPE_NAME, COMMENT, PROJECT_ID, IS_PRODUCT_APPLICABLE, PRE_BUILD_SCRIPT, 
    BUILD_SCRIPT, POST_BUILD_SCRIPT
  </sql>

  <select id="findAllPage" resultMap="mainResultMap" >
    select ID, REPORTING_FACT_TYPE_NAME, COMMENT, PROJECT_ID, IS_PRODUCT_APPLICABLE, 
    PRE_BUILD_SCRIPT, BUILD_SCRIPT, POST_BUILD_SCRIPT
    from SFA_REPORTING_FACT_TYPE_MASTER
    where ID = #{id,jdbcType=INTEGER}
     ORDER BY id offset #{startNum}-1   rows fetch next #{pageSize} rows only 
  </select>

  <select id="findById" resultMap="mainResultMap" >
    select ID, REPORTING_FACT_TYPE_NAME, COMMENT, PROJECT_ID, IS_PRODUCT_APPLICABLE, 
    PRE_BUILD_SCRIPT, BUILD_SCRIPT, POST_BUILD_SCRIPT
    from SFA_REPORTING_FACT_TYPE_MASTER
    where ID = #{id,jdbcType=INTEGER}
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
      #{reportingFactTypeName,jdbcType=VARCHAR},
      <if test="comment != null" >
        #{comment,jdbcType=VARCHAR},
      </if>
      <if test="projectId != null" >
        #{projectId,jdbcType=INTEGER},
      </if>
      #{isProductApplicable,jdbcType=BIT},
      #{preBuildScript,jdbcType=CLOB},
      #{buildScript,jdbcType=CLOB},
      #{postBuildScript,jdbcType=CLOB},
    </trim>
  </insert>

  <update id="update" parameterType="com.nielsen.sdf.domain.Report" >
    update SFA_REPORTING_FACT_TYPE_MASTER
    <set >
      REPORTING_FACT_TYPE_NAME = #{reportingFactTypeName,jdbcType=VARCHAR},
      <if test="comment != null" >
        COMMENT = #{comment,jdbcType=VARCHAR},
      </if>
      <if test="projectId != null" >
        PROJECT_ID = #{projectId,jdbcType=INTEGER},
      </if>
      IS_PRODUCT_APPLICABLE = #{isProductApplicable,jdbcType=BIT},
      PRE_BUILD_SCRIPT = #{preBuildScript,jdbcType=CLOB},
      BUILD_SCRIPT = #{buildScript,jdbcType=CLOB},
      POST_BUILD_SCRIPT = #{postBuildScript,jdbcType=CLOB}
    </set>
    where ID = #{id,jdbcType=INTEGER}
  </update>

</mapper>

# Mybatis-generator-custom_Demo
基于我现在做的项目自定义生成 Mapper配置文件的 修改Mybatis-generator源码后的代码，算是改良的 Generator吧，结果Mapper在 src下面的ReportMapper.xml文件，改动还是蛮多的，主要的配置文件是 generator.xml ，我测试用的数据库是 SQLSERVER。 因为我是把 分页的count 方法和 deleteById方法抽象成公共方法了，所以Mapper里面没生成这两个常用方法

纯干货啊！注意的是java项目跑起来的时候需要依赖一些jar包，反正是web常用的一些jar包，build进去才不报错。
具体使用的话，还是要根据 项目的本身情况。
一般mapper中常用的方法: 根据数据库表结构生成一个 resultMap  还有 findById   findAllPage(SQL SERVER的分页查询) save(根据字段是否nullable来生成insert条件语句） update 这几个常用mapper语句



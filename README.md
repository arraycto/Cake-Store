# Cake-Store
我的设计工程是一个主题为"蛋糕商店"的任务.

##### 设计思想:MVC
>M:model,模型层又分为mapper分层和service分层,
>>mapper分层主要是对数据池中的数据进行原子化处理,偏于持久层底层.
>>
>>service分层,利用业务逻辑处理mapper分层返还的数据,以实现用户某个功能.
>>
>>上接控制器,下联数据池.
>
>V:view,视图
>>用户界面,用户可在视图进行操作.用户操作完毕
>>
>>将用户输入和动作发送给控制器,并按照模型层的通知做出改变.
>>
>>下联持久层
>
>C:controller,控制器
>>控制器定义业务程序行为;用户动作映射为模型的数据处理;
>>
>>接收和判断视图的请求,将请求分配给相应的模型箱子.
>>
>>或选择响应哪一个视图,偏于处理前后端数据交互这一过程.
>>
>>上接视图,下联模型
>>
>

##### 项目架构
+ 选用框架:SpringBoot 2.1.4
+ 开发环境:Windows10
+ 数据库:MySQL 8.0
+ 开发与测试工具:IntelliJ IDEA IU+Sublime Text+Opera
+ JDK版本:1.8

##### 应用技术
+ 后端:Java+MySQL
+ 前端:HTML+CSS,JavaScript+JQuery


### 功能模块
#### 首页搜索
- 在首页搜索框内输入任一字词可进行关键词查询,例如输入"千",便会显示与"千"字有关联之蛋糕.
- 已登录之用户可在搜索结果页面将搜索得出的蛋糕单件加入购物车,若搜索出多款蛋糕,亦可多件多选加入购物车.
- 未登录的用户若想将某款蛋糕加入购物车,则提示请先行登录;已登录之用户若未选一件而点击进车按钮,提示请至少选中一款蛋糕.
  
#### 用户
* 注册  
* 登录
* 修改密码
* 修改资料(用户名/电话/出生年月/性别/地址)
* 个人购物车:单选/多选删除蛋糕,显示购物车中全部蛋糕的总价.
* 退出登录
  
#### 蛋糕种类
    显示商店内蛋糕的各个类型,如 `巧克力`/`奶油蛋糕` 等,点击类型链接即可查看该类型下所属的所有蛋糕,
    已登录用户可直接将其加入蛋糕篮子(购物车).
   
#### 我的蛋糕篮子
+ 即购物车,用户必须登录才能看到自己已添进的蛋糕.
  用户退出登录,页面立即跳转至登录界面(因拦截器设置).
  
+ 已经售罄的蛋糕无法添进个人蛋糕篮子
    
#### 管理登录
* 管理员账号可以登录,但不对外开放注册.

+ 对蛋糕商品管理
    * 查看所有上架蛋糕
    * 修改已上架蛋糕资料
    * 新增一款蛋糕
    * 修改某款蛋糕的库存量
    * 单选/多选删除单款/多款蛋糕

+ 对注册会员管理
    * 查看所有已注册会员的所有账号信息(用户名/电话/出生年月/性别/地址/密码/注销状态)
    * 单选/多选删除一名/多名用户
    * 单选/多选注销或激活一名/多名用户,已注销之用户无法登录账号,激活的作用则与注销相反.
    

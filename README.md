# 一、散记 #

1. redis 非关系层数据库，可以作为关系型数据库（mysql）的缓存存在
2. 集合的大量运算
3. 适合做大量数据的排行榜

		cd - // 回到上一目录
		[root@localhost ~]# find / -name lpc 查找lpc目录


# 二、linux安装redis流程 #

1. 解压

		tar -zxvf redis-3.0.0.tar.gz

2. 编译

    	cd redis-3.0.0
    	make

3. 安装

      	make PREFIX=/usr/local/redis install

4. 拷贝配置文件

		cd redis-3.0.0
		cp redis.conf /usr/local/redis/
		vim /usr/local/redis/redis.conf
		daemonize yes // 修改no为yes

5. 启动

    	cd /usr/local/redis/
		./bin/redis-server ./redis.conf



6. 打开客户端

    	./bin/redis-cli -h 127.0.0.1 -p 6379
    	./bin/redis-cli -h 192.168.215.137 -p 6379
    	config get bind

注：

**1.** ctrl+C 可退出redis客户端

![avatar](/pic/redis学习1.png)

//会显示系统的ip地址信息

    192.168.215.137

**2.** Redis默认端口6379，通过当前服务进行查看

   	ps -ef | grep -i redis


# 三、连接客户端RedisDesktopManager/RedisStudio #

参考链接：[https://www.cnblogs.com/PatrickLiu/p/8360057.html](https://www.cnblogs.com/PatrickLiu/p/8360057.html)

**1.**确定Redis的配置文件Redis.conf里面的bind项目的值是我们Linux系统的IP地址，不是默认的127.0.0.1

![avatar](/pic/redis学习2.png)

命令如下：

    [root@localhost redis]# ./bin/redis-cli -h 192.168.215.137  -p 6379
    192.168.215.137:6379> config get bind
    1) "bind"
    2) ""
    192.168.215.137:6379> 


**2.**在windows命令行中，测试6379端口是否开启
	
    telnet 192.168.215.137 6379

如果未连接成功，则执行步骤3开启端口，重新连接

**3.**开启端口

    [root@localhost redis]# firewall-cmd --query-port=6379/tcp
    no
    [root@localhost redis]# firewall-cmd --add-port=6379/tcp
    success
    [root@localhost redis]# firewall-cmd --query-port=6379/tcp
    yes
    
# 四、问题 #

1. 添加数据时报错 

    	(error) MISCONF Redis is configured to save RDB snapshots, but is currently not able to persist on disk. Commands that may modify the data set are disabled. Please check Redis logs for details about the error.


解决方法： [https://blog.csdn.net/song19890528/article/details/38536871](https://blog.csdn.net/song19890528/article/details/38536871)

运行　config set stop-writes-on-bgsave-error no　命令

    127.0.0.1:6379> config set stop-writes-on-bgsave-error no
    OK

关闭配置项stop-writes-on-bgsave-error解决该问题。


# 五、demo练习 #

运行测试

**1.**StringRedisTemplate

http://localhost:3331/redis/setStr?key=aa&val=name

http://localhost:3331/redis/getStr?key=aa

http://localhost:3331/redis/delStr?key=aa

**2.**RedisTemplate

http://localhost:3331/redis/setObj?key=aa&id=1&name=test

http://localhost:3331/redis/getObj?key=aa

http://localhost:3331/redis/delObj?key=aa


问题：

1.忘添加@Service注解

@Service注解：

2.用代码在1号库中存入了数据后，keys *查询的是0号库，得先进入到1号库，再操作
    
    192.168.215.137:6379> keys *
    1) "bb"
    192.168.215.137:6379> select 1
    OK
    192.168.215.137:6379[1]> keys *
    1) "bb"
    2) "aa"
    3) "\xac\xed\x00\x05t\x00\x02aa"
    192.168.215.137:6379[1]> flushdb
    OK
    192.168.215.137:6379[1]> keys *
    (empty list or set)





项目路径： E:\GitProject\SpringbootRedis\demoRedis

参考链接：[https://www.cnblogs.com/floay/p/6485742.html](https://www.cnblogs.com/floay/p/6485742.html)

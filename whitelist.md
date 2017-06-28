# 白名单功能接口

#### 接口版本

| 版本   | 内容            |
| ---- | ------------- |
| v1   | 2017.6.24 初始化 |
|      |               |

对于所有的请求，响应格式都是一个 JSON 对象。请求是否成功是由 HTTP 状态码标明，2XX 的状态码表示成功，而一个 4XX 表示请求失败。请求失败时响应的主体仍然是一个 JSON 对象，但是总是会包含 `code` ,`result` ,`reason`这三个字段。

😄 成功消息

```json
{
  "code": 1,
  "obj": {
    "result": true,
    "reason": "已完成更新"
  }
}
```

😣 失败消息

```json
{
  "code": 0,
  "obj": {
    "result": false,
    "reason": "删除失败：ID不存在"
  }
}
```
* code:消息码.成功返回1，失败返回0.
* obj：验证信息
* result：验证结果.验证成功返回true，失败返回false.
* reason：验证结果详细说明


API
------

#### 白名单验证:`/admin/v1/whitelist/verify`

对白名单进行验证

请求

```shell

curl -X GET \

http://${admin_api_url}/admin/v1/whitelist/verify/?areaId=&accountId=&clientIp=


```
* areaId：区服ID
* accountId:账户ID
* clientIp:客户端IP

响应

```shell
{
  "code": 1,
  "obj": {
    "result": true,
    "reason": "白名单IP验证成功"
  }
}
```

#### 创建白名单分组:`/admin/v1/whitelist/group/create`

创建一个新的白名单分组

请求

```shell

curl -X POST \

http://${admin_api_url}/admin/v1/whitelist/group/create?name=&status=


```

* name:分组名
* status:当前白名单分组的启用状态： 默认1为启用,0为未启用

响应

```shell
{
  "code": 1,
  "obj": {
    "result": true,
    "reason": "已完成创建"
  }
}
```
#### 查询单个白名单分组:`/admin/v1/whitelist/group/{id}/status`

查询单个白名单分组

请求

```shell

curl -X GET \

  http://${admin_api_url}/admin/v1/whitelist/group/{id}/status
  

```

响应

```shell
{
    "id":"9",
	"name":"九游",
	"status":"1",
	"createAt":"2017-6-24 08:00:00",
	"updateAt":"2017-6-24 09:00:00"
}
```


#### 查询所有白名单分组:`/admin/v1/whitelist/group/`

查询所有白名单分组

请求

```shell

curl -X GET \

  http://${admin_api_url}/admin/v1/whitelist/group/


```

响应

```shell
[
	{"id":"8","name":"testgroup1","status":"1"},
	{"id":"9","name":"test1","status":"1"},
	{"id":"10","name":"test","status":"0"}
]
```

#### 删除白名单分组:`/admin/v1/whitelist/group/{id}`

删除指定ID的白名单分组

请求
```shell

curl -X DELETE \

  http://${admin_api_url}/admin/v1/whitelist/group/${id}  method
 

```

响应

```shell
{
  "code": 1,
  "obj": {
    "result": true,
    "reason": "已完成删除"
  }
}
```

#### 更新白名单分组:`/admin/v1/whitelist/group/{id}`

更新指定ID的白名单分组信息及启动状态

请求
```shell

curl -X PUT \

  http://${admin_api_url}/admin/v1/whitelist/group/?id=&name=&status=
 

```

响应

```shell
{
  "code": 1,
  "obj": {
    "result": true,
    "reason": "已完成更新"
  }
}
```

#### 查询指定分组下的白名单:`/admin/v1/whitelist/group/{id}/ip_list`

查询指定分组下所有白名单IP

请求
```shell

curl -X GET \

  http://${admin_api_url}/admin/v1/whitelist/group/{id}/ip_list
  
 
```

响应

```shell
[
  {
    "id": "25",
    "Group": "24",
    "startIp": "127.0.0.1",
    "createAt": "2017-06-26 16:44:40.0",
    "endIp": "1",
    "updateAt": "2017-06-26 16:44:40.0",
    "status": "1",
    "remark": "test"
  },
  {
    "id": "26",
    "Group": "24",
    "startIp": "127.0.0.1",
    "createAt": "2017-06-26 16:45:23.0",
    "endIp": "0",
    "updateAt": "2017-06-26 16:45:23.0",
    "status": "0",
    "remark": "test"
  }
]
```

#### 创建白名单:`/admin/v1/whitelist/ip/`

创建一个新的白名单IP

请求
```shell

curl -X POST \

  http://${admin_api_url}/admin/v1/whitelist/ip/?groupId=&startIp=&status=&endIp=&remark=
  
  
```

* startIp：起始IP
* endIp：结束IP(可为空)
* status: 当前白名单的启用状态： 默认1为启用,0为未启用
* remark：备注
* groupId:白名单分组(可为空)

响应

```shell
{
  "code": 1,
  "obj": {
    "result": true,
    "reason": "已完成创建"
  }
}
```

#### 删除白名单:`/admin/v1/whitelist/ip/{id}`

删除指定的白名单IP

请求
```shell

curl -X DELETE \

  http://${admin_api_url}/admin/v1/whitelist/ip/${id}


```

响应

```shell
{
  "code": 1,
  "obj": {
    "result": true,
    "reason": "已完成删除"
  }
}
```

#### 更新白名单:`/admin/v1/whitelist/ip/`

更新指定的白名单IP信息及状态

请求
```shell

curl -X PUT \

http://${admin_api_url}/admin/v1/whitelist/ip/?id=&startIp=&status=&endIp=&groupId=&remark=
 

```

响应

```shell
{
  "code": 1,
  "obj": {
    "result": true,
    "reason": "已完成更新"
  }
}
```

#### 查询所有白名单:`/admin/v1/whitelist/ip/all`

更新指定的白名单IP信息及状态

请求
```shell

curl -X GET \

http://${admin_api_url}/admin/v1/whitelist/ip/all
 

```

响应

```shell

  [
  {"id":"25","Group":"1","startIp":"127.0.0.1","endIp":"1","updateAt":"2017-06-26 16:44:40.0","status":"1","remark":"test"}，
  {"id":"26","Group":"0","startIp":"127.0.0.1","endIp":"0","updateAt":"2017-06-26 16:45:23.0","status":"0","remark":"test"},
  {"id":"27","Group":"0","startIp":"127.0.0.2","endIp":"0","updateAt":"2017-06-26 16:45:53.0","status":"0","remark":"test"}
  ]

```

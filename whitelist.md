# 白名单功能接口

#### 接口版本

| 版本   | 内容            |
| ---- | ------------- |
| v1   | 2017.6.24 初始化 |
|      |               |

对于所有的请求，响应格式都是一个 JSON 对象。请求是否成功是由 HTTP 状态码标明，2XX 的状态码表示成功，而一个 4XX 表示请求失败。请求失败时响应的主体仍然是一个 JSON 对象，但是总是会包含 `code` ,`message` ,`state`这三个字段。

😄 成功消息

```json
{
  "message": "已完成创建",
  "code": "10000",
  "obj": []
}
```

😣 失败消息

```json
{
  "message": "ID不存在",
  "code": "10001"
}
```

* code - 消息码，10,000 表示没有错误
* message - 可选的，错误消息
* state - 状态码，0表示正常
* obj - 业务数据，可以是 `{}` 或 `[]`

API
------

#### 创建白名单分组:`/admin/v1/whitelist/group/

创建一个新的白名单分组

请求

```shell

curl -X POST \

http://${admin_api_url}/admin/v1/whitelist/group/?name=&stauts=


```

* name:分组名
* status:当前白名单分组的启用状态： 默认1为启用,0为未启用

响应

```shell
{
  "message": "已完成创建",
  "code": "10000",
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


#### 查询所有白名单分组:`/admin/v1/whitelist/group/{id}`

查询所有白名单分组

请求

```shell

curl -X GET \

  http://${admin_api_url}/admin/v1/whitelist/group/{id}/  


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
  "message": "已完成删除",
  "code": "10000",
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
  "message": "已完成更新",
  "code": "10000"
}
```

#### 查询指定分组下的白名单:`/admin/v1/whitelist/group/{id}/ip_list`

查询指定分组下所有白名单IP

请求
```shell

curl -X GET \

  http://${admin_api_url}/admin/v1/whitelist/group/${group_id}
  
 
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

curl -X DELETE \

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
  "message": "已完成创建",
  "code": "10000"
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
  "message": "已完成删除",
  "code": "10000"
}
```

#### 更新白名单:`/admin/v1/whitelist/ip/`

更新指定的白名单IP信息及状态

请求
```shell

curl -X DELETE \

http://${admin_api_url}/admin/v1/whitelist/ip/?id=&startIp=&stauts=&endIp=&groupId=&remark=
 

```

响应

```shell
{
  "message": "已完成更新",
  "code": "10000"
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

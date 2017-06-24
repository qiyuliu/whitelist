# 白名单功能接口

#### 接口版本

| 版本   | 内容           |
| ---- | ------------ |
| v1   | 2017.6.24 初始化 |
|      |              |

### 白名单接口：`/admin/v1/whitelist/ip`

### 白名单分组接口：`/admin/v1/whitelist/group/`

对于所有的请求，响应格式都是一个 JSON 对象。请求是否成功是由 HTTP 状态码标明，2XX 的状态码表示成功，而一个 4XX 表示请求失败。请求失败时响应的主体仍然是一个 JSON 对象，但是总是会包含 `code` ,`message` ,`state`这三个字段。

😄 成功消息

```json
{
  "message": "已完成创建",
  "code": "10000",
  "state": 0,
  "obj": null
}
```

😣 失败消息

```json
{
  "message": "ID不存在",
  "code": "10001",
  "state": 1,
  "obj": null
}
```

* code - 消息码，10,000 表示没有错误
* message - 可选的，错误消息
* state - 状态码，0表示正常
* obj - 业务数据，可以是 `{}` 或 `[]`

API
------

### 创建白名单分组:`/admin/v1/whitelist/group/`

创建一个新的白名单分组

请求

```shell
http://${charge_api_url}/admin/v1/whitelist/group/ method POST

{
  "name":"testgroup",
  "stauts":"1"
}
```

* name:分组名
* status:白名单启用状态： 默认0为未启用，1为启用

响应

```shell
{
  "message": "已完成创建",
  "code": "10000",
  "state": 0,
  "obj": null
}
```

### 查询白名单分组列表:`/admin/v1/whitelist/group/list`

查询所有白名单分组

请求

```shell
http://${charge_api_url}/admin/v1/whitelist/group/list  method GET

```

响应

```shell
[
	{"id":"8","name":"testgroup1","status":"1"},
	{"id":"9","name":"test1","status":"1"},
	{"id":"10","name":"test","status":"0"}
]
```

### 删除白名单分组:`/admin/v1/whitelist/group/{id}`

删除指定ID的白名单分组

请求
```shell
http://${charge_api_url}/admin/v1/whitelist/group/${id}  method DELETE
```

响应

```shell
{
  "message": "已完成删除",
  "code": "10000",
  "state": 0,
  "obj": null
}
```

### 更新白名单分组:`/admin/v1/whitelist/group/{id}`

更新指定ID的白名单分组信息及启动状态

请求
```shell
http://${charge_api_url}/admin/v1/whitelist/group/${id}  method PUT
 
{
        "id":10,
        "name":"testgroup",
        "stauts":"0"
}

```

响应

```shell
{
  "message": "已完成更新",
  "code": "10000",
  "state": 0,
  "obj": null
}
```

### 查询分组下白名单:`/admin/v1/whitelist/group/{group_id}`

查询指定分组下所有白名单IP

请求
```shell
http://${charge_api_url}/admin/v1/whitelist/group/${group_id}  method GET
```

响应

```shell
[
     {
      "id":"14",
      "Group":"10",
      "startIp":"123.4.5.6",
      "endIp":"0",
      "updateAt":"2017-06-23 13:44:16.0",
      "status":"0",
      "remark":"备注"},
      {"id":"16",
      "Group":"10",
      "startIp":"123.4.5.6",
      "endIp":"1",
      "updateAt":"2017-06-23 14:03:28.0",
      "status":"1",
      "remark":"test"
      }
]
```

### 创建白名单:`/admin/v1/whitelist/ip/`

创建一个新的白名单IP

请求
```shell
http://${charge_api_url}/admin/v1/whitelist/ip/ method POST

{
        "startIp":"123.456.7",
        "endIp":"765.432.1",
        "stauts":"1",
        "remark":"凯英",
        "groupId":"16"
}
```



* startIp：起始IP
* endIp：结束IP 保留字段
* status: 状态
* remark：备注
* groupId:白名单分组

响应

```shell
{
  "message": "已完成创建",
  "code": "10000",
  "state": 0,
  "obj": null
}
```

### 删除白名单:`/admin/v1/whitelist/ip/{id}`

删除指定的白名单IP

请求
```shell
http://${charge_api_url}/admin/v1/whitelist/ip/${id} method DELETE
```

响应

```shell
{
  "message": "已完成删除",
  "code": "10000",
  "state": 0,
  "obj": null
}
```

### 更新白名单:`/admin/v1/whitelist/ip/`

更新指定的白名单IP信息及状态

请求
```shell
http://${charge_api_url}/admin/v1/whitelist/ip/ method PUT
 
{
        "id":14,
        "startIp":"123.456.7",
        "endIp":"765.432.1",
        "stauts":"1",
        "remark":"凯英",
        "groupId":"12"
}
    
```

响应

```shell
{
  "message": "已完成更新",
  "code": "10000",
  "state": 0,
  "obj": null
}
```

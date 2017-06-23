# 白名单功能接口


### 白名单分组接口：`/admin_v1/white_ip_group`

### 创建白名单分组

请求
```shell
 / method POST

{
        "name":"testgroup",
        "status":"0",
        "WhitelistIps":{
             "startIp":"",
             "endIp":"",
             "updateAt":"",
             "status":null,
             "remark":""
        }
}
```

* name:分组名
* status:白名单启用状态： 默认0为未启用，1为启用
* WhitelistIps：关联白名单属性，填空值

响应

```shell
{
  "message": null,
  "code": "10000",
  "state": 0,
  "obj": null
}
```
---
### 查询白名单分组列表

请求
```shell
 /list  method GET

```

响应

```shell
[
	{"id":"8","name":"testgroup1","status":"1"},
	{"id":"9","name":"test1","status":"1"},
	{"id":"10","name":"test","status":"0"}
]
```

### 删除白名单分组

请求
```shell
 /{id}  method DELETE

```

响应

```shell
{
  "message": null,
  "code": "10000",
  "state": 0,
  "obj": null
}
```
### 更新白名单分组信息及启动状态

请求
```shell
 /{id}  method PUT
 
{
        "id":1,
        "name":"testgroup",
        "status":"1",
        "WhitelistIps":{
             "startIp":"",
             "endIp":"",
             "updateAt":"",
             "status":null,
             "remark":""
        }
    }

```

响应

```shell
{
  "message": null,
  "code": "10000",
  "state": 0,
  "obj": null
}
```
### 查询分组下白名单

请求
```shell
 /{group_id}  method GET
 
{
        "id":1,
        "name":"testgroup",
        "status":"1",
        "WhitelistIps":{
             "startIp":"",
             "endIp":"",
             "updateAt":"",
             "status":null,
             "remark":""
        }
    }

```

响应

```shell
[     {"id":"14",
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
      "remark":"test"}
]
```

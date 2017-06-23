#白名单功能接口


###白名单接口：`/admin_v1/white_ip`

###创建白名单

请求
```shell
 / method POST

{
        "whitelistIpGroup":{
            "id":1,
            "name":"test",
            "status":0
        },
        "startIp":"123.4.5.6",
        "endIp":"00000",
        "updateAt":"",
        "status":0,
        "remark":"备注"
    }
```


* whitelistIpGroup:所属分组
* whitelistIpGroup.id:所属分组ID
* whitelistIpGroup.name：所属分组名
* whitelistIpGroup.status：分组状态
* startIp：起始IP
* endIp：结束IP 保留字段
* updateAt: 更新时间
* status: 状态
* remark：备注

响应

```shell
{
  "message": null,
  "code": "10000",
  "state": 0,
  "obj": null
}
```

###删除白名单

请求
```shell
 /{id} method DELETE
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

###更新白名单信息及状态

请求
```shell
 / method PUT
 
 {
        "id":1,
        "whitelistIpGroup":{
            "id":1,
            "name":"test",
            "status":0
        },
        "startIp":"123.4.5.6",
        "endIp":"",
        "updateAt":"",
        "status":1,
        "remark":"白名单启用"
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
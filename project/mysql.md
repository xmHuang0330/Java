### 板信息

```
芯片号：lane varchar(20)
板号：tablet varchar(200)
项目号：project varchar(100)
板实验员：experimenters varchar(30)
板上机数量：start_count int(20)
板下机数量：end_count int(20)
板合格男性：man int(5)
板合格女性：woman int(5)
板不合格：failure int(5)
板成功率：tablet_success_rate varchar(10)
```

### 芯片信息

```
芯片号：lane varchar(20)
实验人员：members varchar(20)
上机时间：start_time vatchar(50)
上机地点：location vatchar(100)
下机时间：end_time vatchar(50)
有关项目：project varchar(50)
合格男性：man int(5)
合格女性：women int(5)
不合格：failure int(5)
芯片成功率：lane_succes_rate varchar(10)
```

### 实验信息

```
芯片号：lane varchar(20)
环化方式：cyclizing varchar(50)
文库浓度：library_density double(10)
环化反应投入量：cyclizing_input double(10)
环化产物浓度：cyclizing_product_density doouble(10)
环化效率：cyclizing_success_rate varchar(10)
DNB方式：DNB varchar(50)
DNB制备体积：DNB_volume double(10)
DNB制备投入量：DNB_input double(10)
DNB产物浓度：DNB_product_density varchar(10)
备注：remarks longtext
```


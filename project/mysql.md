### tablet_information

```
芯片号：chip varchar(20)
板号：tablet varchar(200)
项目号：project varchar(100)
板实验员：experimenters varchar(30)
板文库浓度：tablet_library_density double
板上机数量：start_count int(20)
板下机数量：end_count int(20)
板成功率：tablet_success_rate varchar(10)
```

### chip_information

```
芯片号：chip varchar(20)
芯片上机数量：start_count int(20)
芯片下机数量：end_count int(20)
样本号重复数量：repeat_count int(20)
合格男性：man int(5)
合格女性：women int(5)
不合格：failure int(5)
芯片成功率：lane_success_rate varchar(10)
```

### chemical_information

```
芯片号：chip varchar(20)
有关项目：project varchar(50)
实验人员：members varchar(20)
上机时间：start_time vatchar(50)
上机地点：location vatchar(100)
下机时间：end_time vatchar(50)
环化方式：cyclizing varchar(50)
文库浓度：library_density double
环化反应投入量：cyclizing_input double
环化产物浓度：cyclizing_product_density doouble
环化效率：cyclizing_success_rate varchar(10)
DNB方式：DNB varchar(50)
DNB制备体积：DNB_volume double
DNB制备投入量：DNB_input double
DNB产物浓度：DNB_product_density varchar(10)
备注：remarks longtext
```

### sample_information

```
芯片号：chip varchar(20)
lane号：lane varchar(10)
index：index int(6)
板号：tablet varchar(20)
原始编号：name varchar(50)
项目号：project varchar(50)
板位：well varchar(5)
```

### area

```
name
```

### area_batch

```
name
batch
batch_count
```

```
1.判断类型(全部,男性,女性,不合格)
	男性:>= 27,35
	全部:>=0
	女性:=0,>27
	失败:<27
2.判断条件满足情况---->
	(有板号,样本号)
	(无板号,无样本号)
	(有板号,无样本号)
	(有样本号,无板号)
```


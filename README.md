# dynamic protobuf
### 实现目标
1.  不用使用```.proto```文件
2.  使用```Annotation``` 直接进行编码与解析
3.  性能不那么糟糕

### 使用方法
##### 序列化(encode)
```
......
Object src = ...
byte[] data = DynaPb.encode(src);
......
```

##### 反序列化(decode)

```
......
class Foo {

    @PbSerializedField(1)
    private Long lv;
    
    public Long getLv() {
        return lv;
    }
    
    public void setLv(Long lv) {
        this.lv = lv;
    }
    
    public Foo(){
    }
}
byte[] data = ...
Foo foo = DynaPb.decode(data, 0, data.length, Foo.class);
......

```

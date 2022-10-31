package com.salk.uicoder.persist.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

import java.util.List;
import java.util.Map;

/**
 * @author salkli
 * @since 2022/10/30
 **/
public interface MyBaseMapper<T> extends BaseMapper<T> {
    int save(T data);
    @Insert("<script>insert into xxxx</script>")
    int saveScript(T data);
    int batchSave(List<T> datas);
}

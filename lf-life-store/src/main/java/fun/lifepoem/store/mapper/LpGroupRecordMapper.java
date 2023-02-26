package fun.lifepoem.store.mapper;

import fun.lifepoem.store.domain.LpGroupRecord;
import org.springframework.stereotype.Repository;

/**
* @author 55971
* @description 针对表【lp_group_record】的数据库操作Mapper
* @createDate 2023-02-26 18:40:39
* @Entity fun.lifepoem.store.domain.LpGroupRecord
*/
@Repository
public interface LpGroupRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(LpGroupRecord record);

    int insertSelective(LpGroupRecord record);

    LpGroupRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LpGroupRecord record);

    int updateByPrimaryKey(LpGroupRecord record);

}

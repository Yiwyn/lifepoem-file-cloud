package fun.lifepoem.manager.mapper;

import fun.lifepoem.manager.domain.LpGroupRecord;
import org.springframework.stereotype.Repository;

/**
 * @author 55971
 * @description 针对表【lp_group_record】的数据库操作Mapper
 * @createDate 2023-02-26 17:45:48
 * @Entity fun.lifepoem.manager.domain.LpGroupRecord
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

package fun.lifepoem.manager.mapper;

import fun.lifepoem.manager.domain.LpShareRecord;

/**
* @author 55971
* @description 针对表【lp_share_record】的数据库操作Mapper
* @createDate 2023-02-13 23:03:15
* @Entity fun.lifepoem.manager.domain.LpShareRecord
*/
public interface LpShareRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(LpShareRecord record);

    int insertSelective(LpShareRecord record);

    LpShareRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LpShareRecord record);

    int updateByPrimaryKey(LpShareRecord record);

}

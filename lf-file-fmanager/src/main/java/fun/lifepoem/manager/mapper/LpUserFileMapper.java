package fun.lifepoem.manager.mapper;

import fun.lifepoem.manager.domain.LpUserFile;

/**
* @author 55971
* @description 针对表【lp_user_file】的数据库操作Mapper
* @createDate 2023-02-26 13:35:06
* @Entity fun.lifepoem.manager.domain.LpUserFile
*/
public interface LpUserFileMapper {

    int deleteByPrimaryKey(Long id);

    int insert(LpUserFile record);

    int insertSelective(LpUserFile record);

    LpUserFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LpUserFile record);

    int updateByPrimaryKey(LpUserFile record);

}

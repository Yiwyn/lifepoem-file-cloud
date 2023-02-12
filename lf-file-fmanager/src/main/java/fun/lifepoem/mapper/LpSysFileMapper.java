package fun.lifepoem.mapper;

import fun.lifepoem.domain.LpSysFile;

/**
* @author 55971
* @description 针对表【lp_sys_file】的数据库操作Mapper
* @createDate 2023-02-12 15:02:08
* @Entity fun.lifepoem.domain.LpSysFile
*/
public interface LpSysFileMapper {

    int deleteByPrimaryKey(Long id);

    int insert(LpSysFile record);

    int insertSelective(LpSysFile record);

    LpSysFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LpSysFile record);

    int updateByPrimaryKey(LpSysFile record);

}

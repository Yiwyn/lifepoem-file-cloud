package fun.lifepoem.manager.mapper;

import fun.lifepoem.manager.domain.LpSysFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 55971
 * @description 针对表【lp_sys_file】的数据库操作Mapper
 * @createDate 2023-02-12 15:08:10
 * @Entity fun.lifepoem.manager.domain.LpSysFile
 */
@Repository
public interface LpSysFileMapper {

    int deleteByPrimaryKey(String id);

    int insert(LpSysFile record);

    int insertSelective(LpSysFile record);

    LpSysFile selectByPrimaryKey(String id);

    LpSysFile selectByMD5(@Param("md5") String md5);

    int updateByPrimaryKeySelective(LpSysFile record);

    int updateByPrimaryKey(LpSysFile record);

}

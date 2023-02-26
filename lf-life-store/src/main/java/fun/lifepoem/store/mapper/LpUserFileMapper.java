package fun.lifepoem.store.mapper;

import fun.lifepoem.store.domain.LpUserFile;
import org.springframework.stereotype.Repository;

/**
 * @author 55971
 * @description 针对表【lp_user_file】的数据库操作Mapper
 * @createDate 2023-02-26 18:38:47
 * @Entity fun.lifepoem.store.domain.LpUserFile
 */
@Repository
public interface LpUserFileMapper {

    int deleteByPrimaryKey(Long id);

    int insert(LpUserFile record);

    int insertSelective(LpUserFile record);

    LpUserFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LpUserFile record);

    int updateByPrimaryKey(LpUserFile record);

}

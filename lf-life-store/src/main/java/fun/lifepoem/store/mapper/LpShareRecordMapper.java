package fun.lifepoem.store.mapper;

import fun.lifepoem.store.domain.LpShareRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 55971
* @description 针对表【lp_share_record】的数据库操作Mapper
* @createDate 2023-02-28 20:44:39
* @Entity fun.lifepoem.store.domain.LpShareRecord
*/
@Repository
public interface LpShareRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(LpShareRecord record);

    int insertSelective(LpShareRecord record);

    LpShareRecord selectByPrimaryKey(Long id);

    LpShareRecord selectByShortKeyAndShareKey(@Param("shortKey") String shortKey, @Param("shareKey")String shareKey);

    LpShareRecord selectByShortKey(@Param("shortKey")String shortKey);

    int updateByPrimaryKeySelective(LpShareRecord record);

    int updateByPrimaryKey(LpShareRecord record);

    List<LpShareRecord> selectByUserAndFile(@Param("userId") Integer userId, @Param("fileId") String fileId);


}

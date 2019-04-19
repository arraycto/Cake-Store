package com.cake.shop.service.imp;

import com.cake.shop.entity.AngelCake;
import com.cake.shop.mappers.AngelCakeMappers;
import com.cake.shop.service.IAngelCakeService;
import com.cake.shop.service.except.KeyWordNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AngelServiceImpl implements IAngelCakeService {
    @Autowired
    private AngelCakeMappers acm;

    /**
     *
     * @return
     */
    @Override
    public List<AngelCake> showAllCake() {
        List<AngelCake> angelCakes = acm.selectAll();
        return angelCakes;
    }

    @Override
    public AngelCake findByAcid(Integer acid) {
        AngelCake angelCake = acm.selectByAcid(acid);
        return angelCake;
    }

    @Override
    public List<AngelCake> searchByKeyWord(String termChar) throws KeyWordNullException {
        //检查条件词是否为空,空便抛
        if (termChar == null || "".equals(termChar)) {
            throw new KeyWordNullException("请输入检索条件");
        }
        //否,执行查询
        List<AngelCake> angelCakes = acm.selectBlurry(termChar);

        return angelCakes;
    }

    /**
     * @return
     */
    @Override
    public List<AngelCake> getDistinctCakeType() {
        return acm.selectDistinctCakeType();
    }

    @Override
    public List<AngelCake> getByCakeType(String cakeType) {
        System.err.println("cakes type: " + cakeType);
        return acm.foundByCakeType(cakeType);
    }

    @Override
    public Integer changeAmount(Integer amount, Integer acid) {
        Integer integer = acm.modifyAmountByAcid(amount, acid);
        return integer;
    }

}

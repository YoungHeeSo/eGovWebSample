/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.shop.service.impl;

import java.util.List;

import egovframework.example.sample.service.SampleVO;
import egovframework.example.shop.service.EgovBuyService;
import egovframework.example.shop.service.ShopDefaultVO;
import egovframework.example.shop.service.ShopVO;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Service("buyService")
public class EgovBuyServiceImpl extends EgovAbstractServiceImpl implements EgovBuyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBuyServiceImpl.class);

	/** BuyDAO */
	@Resource(name="buyMapper")
	private BuyMapper buyDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	@Override
	public ShopVO selectBuy(ShopVO vo) throws Exception {
		ShopVO resultVO = buyDAO.selectBuy(vo);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}
	
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	@Override
	public int insertBuy(ShopVO vo) throws Exception {
		LOGGER.debug("insert: "+vo.toString());

		/** ID Generation Service */
		int num = egovIdGnrService.getNextIntegerId();
		LOGGER.debug("num: "+num);
		vo.setNum(num);
		
		LOGGER.debug("after: "+vo.toString());

		buyDAO.insertBuy(vo);
		
		return num;
	}

	/**
	 * 글 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	@Override
	public List<?> selectBuyList(ShopDefaultVO searchVO) throws Exception {
		return buyDAO.selectBuyList(searchVO);
	}

	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	
	 @Override 
	 public int selectBuyListTotCnt(ShopDefaultVO searchVO) {
		 return buyDAO.selectBuyListTotCnt(searchVO); }
	 

}

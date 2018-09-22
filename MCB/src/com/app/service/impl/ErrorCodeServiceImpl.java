package com.app.service.impl;

import java.util.List;

import com.app.entity.ErrorCode;
import com.app.mapper.ErrorCodeMapper;
import com.app.service.ErrorCodeService;

public class ErrorCodeServiceImpl implements ErrorCodeService {
		private ErrorCodeMapper errorCodeMapper;

		public ErrorCodeMapper getErrorCodeMapper() {
			return errorCodeMapper;
		}

		public void setErrorCodeMapper(ErrorCodeMapper errorCodeMapper) {
			this.errorCodeMapper = errorCodeMapper;
		}

		@Override
		public List<ErrorCode> listPageErrorCode(ErrorCode errorCode) {
			 
			List<ErrorCode> list=errorCodeMapper.listPageErrorCode(errorCode);
			return list;
		}

		@Override
		public boolean insertErrorCode(ErrorCode errorCode) {
			 
			errorCodeMapper.insertErrorCode(errorCode);
			return true;
		}

		@Override
		public void deleteErrorCode(int errorCodeId) {
			 
			errorCodeMapper.deleteErrorCode(errorCodeId);
		}

		@Override
		public ErrorCode getErrorCodeById(int errorCodeId) {
			 
			return errorCodeMapper.getErrorCodeById(errorCodeId);
		}

		@Override
		public void updaterrorCodeInfo(ErrorCode errorCode) {
			// TODO Auto-generated method stub
			errorCodeMapper.updaterrorCodeInfo(errorCode);
		}
		
		
}

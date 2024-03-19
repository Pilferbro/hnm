package com.nantian.hnm;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nantian.mfp.codegen.service.CodeGenService;
import com.nantian.mfp.test.base.CodegenTests;

public class CodeGenServiceTests extends CodegenTests {

	@Autowired
	CodeGenService codeGenService;

	/**生成dao**/
	@Test
	public void genDao(){
		codeGenService.genDao("T_MER_DATA_HIS");
	}

	/**生成model**/
	@Test
	public void genModel(){
		codeGenService.genModel("t_device");
	}
}

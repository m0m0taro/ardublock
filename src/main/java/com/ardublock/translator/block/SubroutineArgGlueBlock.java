package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class SubroutineArgGlueBlock extends TranslatorBlock
{
	public SubroutineArgGlueBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String ret = "";
		TranslatorBlock translatorBlock = this.getTranslatorBlockAtSocket(0, codePrefix, codeSuffix);
		if (translatorBlock != null)
		{
			ret =  ArgDeclair(translatorBlock) + translatorBlock.toCode();
		}
		return ret;
	}

	private String ArgDeclair( TranslatorBlock translatorBlock ) {

		String argDeclair;
		if (translatorBlock instanceof VariableDigitalBlock) {
			argDeclair = "boolean& ";
		} else if (translatorBlock instanceof VariablePolyBlock) {
			argDeclair = "char& ";
		} else if (translatorBlock instanceof VariableStringBlock) {
			argDeclair = "char* ";
		} else if (translatorBlock instanceof variable_String) {
			argDeclair = "String& ";
		} else {
			argDeclair = "";
		}

		return argDeclair;
	}
}
